package com.harbe.authservice.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.harbe.authservice.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}