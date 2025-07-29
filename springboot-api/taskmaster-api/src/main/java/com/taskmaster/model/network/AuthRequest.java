package com.taskmaster.model.network;

import lombok.Data;
import lombok.Getter;


@Getter
@Data
public class AuthRequest {
    private String email;
    private String password;
}