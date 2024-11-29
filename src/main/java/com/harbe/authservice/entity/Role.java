package com.harbe.authservice.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Ghi đè phương thức equals từ lớp Object để so sánh 2 đối tượng Role
    @Override
    public boolean equals(Object o) {
        // Kiểm tra nếu đối tượng so sánh chính là nó
        if (this == o)
            return true;

        // Kiểm tra nếu đối tượng so sánh không phải kiểu Role
        if (!(o instanceof Role role))
            return false;

        // So sánh thuộc tính name của 2 đối tượng Role
        return name.equals(role.name);
    }

    // Ghi đè phương thức hashCode từ lớp Object
    @Override
    public int hashCode() {
        // Tạo mã băm dựa trên thuộc tính name
        // Sử dụng Objects.hash() để đảm bảo tính nhất quán
        return Objects.hash(name);
    }
}
