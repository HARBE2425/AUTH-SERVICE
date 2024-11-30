package com.harbe.authservice.controller;

import com.harbe.authservice.dto.model.UserDto;
import com.harbe.authservice.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import com.harbe.authservice.dto.response.ObjectResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.harbe.authservice.utils.AppConstants;
import com.harbe.authservice.utils.CustomHeaders;

@Tag(name = "User", description = "REST APIs for User")
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    // @RequestBody: dữ liệu JSON từ request body sẽ được map vào đối tượng UserDto
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto) {
        // đóng gói kết quả: userDto và HTTP status code 201
        return new ResponseEntity<>(this.userService.createUser(userDto), HttpStatus.CREATED);
    }

    // @RequestParam: lấy giá trị từ url query string
    // AppConstants lấy từ file AppConstants.java
    @GetMapping
    public ResponseEntity<ObjectResponse<UserDto>> getAllUser(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        return new ResponseEntity<>(this.userService.getAllUser(pageSize, pageNo, sortBy, sortDir), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(value = "userId") Long id) { // lấy giá trị userId từ url
                                                                                          // map vào biến id
        return new ResponseEntity<>(this.userService.getUserById(id), HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable(value = "userId") Long id,
            @RequestBody UserDto userDto) {
        return new ResponseEntity<>(this.userService.updateUser(userDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "userId") Long id) {
        this.userService.deleteUser(id);

        return new ResponseEntity<>("Delete user successfully!", HttpStatus.OK);
    }

    // @RequestHeader: lấy giá trị từ header của request
    // CustomHeaders lấy từ file CustomHeaders.java
    // id truyền qua header bảo mật hơn so với truyền qua url
    // custom header: GET /api/users/profile = X-Auth-User-Id:123
    @GetMapping("/profile")
    public ResponseEntity<UserDto> getUserProfile(@RequestHeader(CustomHeaders.X_AUTH_USER_ID) Long id) {
        return new ResponseEntity<>(this.userService.getUserProfile(id), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<ObjectResponse<UserDto>> searchUser(
            @RequestParam("name") String name, // tìm kiếm theo tên
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        // trả về danh sách user tìm được, thông tin phân trang, http status code 200
        return new ResponseEntity<>(this.userService.searchUser(name, pageNo, pageSize, sortBy, sortDir),
                HttpStatus.OK);
    }

}