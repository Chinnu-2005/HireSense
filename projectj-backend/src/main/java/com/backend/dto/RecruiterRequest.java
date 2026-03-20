package com.backend.dto;

import lombok.Data;

@Data
public class RecruiterRequest {
    private String email;
    private String name;
    private String password;
    private String companyName;
    private String designation;
}
