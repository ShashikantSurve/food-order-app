package com.surve_Food.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.surve_Food.Model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
