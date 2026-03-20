package com.backend.dto;

import lombok.Data;
import java.util.List;

@Data
public class CandidateRequest {

    private String email;
    private String password;

    private String name;
    private List<String> skills;
    private int experience;

}