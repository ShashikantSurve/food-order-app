package com.surve_Food.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.surve_Food.Model.Cart;
import com.surve_Food.Model.USER_ROLE;
import com.surve_Food.Model.User;
import com.surve_Food.config.JwtProvider;
import com.surve_Food.repository.CartRepository;
import com.surve_Food.repository.UserRepository;
import com.surve_Food.request.LoginRequest;
import com.surve_Food.response.AuthResponse;
import com.surve_Food.service.CustomerUserDetailService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtProvider jwtProvider;

	@Autowired
	private CustomerUserDetailService customerUserDetailService;

	@Autowired
	private CartRepository cartRepository;

	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception {

		User isEmailExist = userRepository.findByEmail(user.getEmail());

		if (isEmailExist != null) {
			throw new Exception("Email  is Already used with another Account");
		}
		User createdUser = new User();

		createdUser.setEmail(user.getEmail());
		createdUser.setFullName(user.getFullName());
		createdUser.setRole(user.getRole());
		createdUser.setPassword(passwordEncoder.encode(user.getPassword()));

		User savedUser = userRepository.save(createdUser);

		Cart cart = new Cart();
		cart.setCustomer(savedUser);
		cartRepository.save(cart);

		Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateToken(authentication);
		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(jwt);

		authResponse.setMessage("Register Success...");
		authResponse.setRoles(savedUser.getRole());
		return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);

	}

	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest req) {

		String username = req.getEmail();
		String password = req.getPassword();

		Authentication authentication = authenticate(username, password);
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		String role = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();

		String jwt = jwtProvider.generateToken(authentication);
		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(jwt);

		authResponse.setMessage("Login Success...");

		authResponse.setRoles(USER_ROLE.valueOf(role));
		return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);

	}

	private Authentication authenticate(String username, String password) {
		UserDetails userDetails = customerUserDetailService.loadUserByUsername(username);
		if (userDetails == null) {
			throw new BadCredentialsException("Invalid Username....");

		}
		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid Password");
		}

		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}

}
