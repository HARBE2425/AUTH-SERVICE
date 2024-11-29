package com.harbe.authservice.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.harbe.authservice.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameOrEmail(String username, String email);

    User findByEmail(String email);

    User findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    // Query để tìm User theo username và load luôn roles (eager loading)
    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.username = :username")
    Optional<User> findOneByUsernameWithRoles(String username);

    // Native SQL query để tìm kiếm Users theo tên với phân trang
    @Query(value = "SELECT * FROM Users u WHERE u.name LIKE CONCAT('%', :name, '%')", nativeQuery = true)
    Page<User> searchUserByName(
            @Param("name") String name, // Tham số tìm kiếm tên
            Pageable pageable // Đối tượng phân trang
    );
}
