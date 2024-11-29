package com.harbe.authservice.dto.model;

import java.util.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;

    @NotEmpty(message = "Tên không được để trống")
    @Size(min = 3, message = "Tên phải có độ dài tối thiểu 3 ký tự")
    private String name;

    @Email
    @NotEmpty(message = "Email không được để trống")
    private String email;

    @NotEmpty(message = "Username không được để trống")
    @Size(min = 3, message = "Username phải có tối thiểu 3 ký tự")
    private String username;

    @NotEmpty(message = "Mật khẩu không được để trống")
    @Size(min = 6, message = "Mật khẩu phải có tối thiểu 6 ký tự")
    private String password;

    @NotEmpty(message = "Số điện thoại không được để trống")
    @Size(min = 9, message = "Số điện thoại phải có tối thiểu 9 ký tự")
    private String phone;

    private String gender;
    private Date dateOfBirth;
}
