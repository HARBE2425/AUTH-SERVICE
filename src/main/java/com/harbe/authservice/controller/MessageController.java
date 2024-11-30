package com.harbe.authservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Cho phép cập nhật lại các giá trị cấu hình khi runtime mà không cần restart
@RefreshScope
@RestController
public class MessageController {
    @Value("spring.boot.message")
    private String message; //inject giá trị từ file cấu hình

    @GetMapping("/auth/message")
    public String getMessage() {
        return message;
    }
}
