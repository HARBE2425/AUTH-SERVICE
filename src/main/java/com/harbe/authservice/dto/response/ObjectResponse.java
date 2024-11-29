package com.harbe.authservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

// geniric class tham số T, dùng để đóng gói kết quả phân trang (pagination)
public class ObjectResponse<T> {
    private List<T> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private long totalPages;
    private boolean isLast;
}