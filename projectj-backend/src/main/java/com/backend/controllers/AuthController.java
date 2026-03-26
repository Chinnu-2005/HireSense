package com.backend.controllers;

import com.backend.dto.CandidateRequest;
import com.backend.dto.RecruiterRequest;
import com.backend.dto.UserRequest;
import com.backend.entities.Candidate;
import com.backend.entities.Recruiter;
import com.backend.entities.Response;
import com.backend.entities.User;
import com.backend.repositories.CandidateRepository;
import com.backend.repositories.RecruiterRepository;
import com.backend.repositories.UserRepository;
import com.backend.services.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private RecruiterRepository recruiterRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/register/candidate", consumes = "multipart/form-data")
    public Response<Candidate> registerCandidate(@RequestPart("candidate") String candidateJson, @RequestPart("resume") MultipartFile resumeUrl) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        CandidateRequest candidateRequest = mapper.readValue(candidateJson, CandidateRequest.class);
        if(candidateRepository.findByEmail(candidateRequest.getEmail())!=null){
            Candidate candicateInDb=candidateRepository.findByEmail(candidateRequest.getEmail());
            return Response.<Candidate>builder().message("Candicate with this email already exists").statusCode(400).data(candicateInDb).build();
        }
        Candidate candidate = authService.registerCandidate(candidateRequest,resumeUrl);

        return Response.<Candidate>builder().message("Candidate registered successfully").statusCode(200).data(candidate).build();
    }

    @PostMapping("/register/recruiter")
    public Response<Recruiter> registerRecruiter(@RequestBody RecruiterRequest recruiterRequest) {
        if(recruiterRepository.findByEmail(recruiterRequest.getEmail())!=null){
            Recruiter recruiterInDb=recruiterRepository.findByEmail(recruiterRequest.getEmail());
            return Response.<Recruiter>builder().message("Recruiter with this email exists").statusCode(400).data(recruiterInDb).build();
        }
        Recruiter recruiter = authService.registerRecruiter(recruiterRequest);
        return Response.<Recruiter>builder().message("Recruiter registered successfully").statusCode(200).data(recruiter).build();
    }

    @PostMapping("/login")
    public Response<User> loginUser(@RequestBody UserRequest userRequest) {
        String response= authService.login(userRequest);
        if(response==null){
            return Response.<User>builder().message("Login Failed").statusCode(400).data(null).build();
        }
        User userInDb=userRepository.findByEmail(userRequest.getEmail());
        return Response.<User>builder().message("Login Success").statusCode(200).data(userInDb).build();
    }

}
