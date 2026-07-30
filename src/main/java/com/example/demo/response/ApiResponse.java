package com.example.demo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
 // Time: 02 : 06 : 38
@Data
@AllArgsConstructor
public class ApiResponse {
    private String message ;
    private Object data ;
}
