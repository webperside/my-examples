package com.example.springbootplsql.repository;

import com.example.springbootplsql.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
