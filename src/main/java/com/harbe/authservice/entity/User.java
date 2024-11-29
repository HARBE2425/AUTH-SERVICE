package com.harbe.authservice.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String phone;
    private String gender;
    private Date dateOfBirth;

    @ManyToMany(fetch = FetchType.EAGER, // Tải dữ liệu roles ngay khi load User
            cascade = {
                    CascadeType.PERSIST, // Khi lưu User sẽ tự động lưu các Role liên quan
                    CascadeType.MERGE // Khi cập nhật User sẽ cập nhật các Role liên quan
            })
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "user", // Thuộc tính user trong entity Address
            cascade = CascadeType.ALL, // Các thao tác CRUD trên User ảnh hưởng đến Address
            orphanRemoval = true // Xóa Address khi không còn liên kết với User
    )
    private Set<Address> addresses = new HashSet<>();
}
