package com.surve_Food.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping
	public ResponseEntity<String> HomeController() {

		return new ResponseEntity<>("Welcome to foood Delivery Project", HttpStatus.OK);

	}
}
