package com.taskmaster.model.network;


import lombok.Data;
import lombok.Getter;


@Getter
@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
}