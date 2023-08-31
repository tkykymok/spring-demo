package com.example.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorResponse {
    private LocalDateTime timestamp;  // エラーの発生時刻
    private int status;               // HTTPステータスコード
    private String error;             // HTTPステータスの説明
    private String detail;            // 具体的なエラーメッセージ
    private List<String> messages;    // カスタムメッセージのリスト
}
