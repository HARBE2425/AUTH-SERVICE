package com.harbe.authservice.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.harbe.authservice.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByUserId(Long userId);
}