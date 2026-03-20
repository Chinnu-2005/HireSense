package com.backend.services;

import com.backend.dto.CandidateRequest;
import com.backend.dto.RecruiterRequest;
import com.backend.dto.UserRequest;
import com.backend.entities.Candidate;
import com.backend.entities.Recruiter;
import com.backend.entities.Role;
import com.backend.entities.User;
import com.backend.repositories.CandidateRepository;
import com.backend.repositories.RecruiterRepository;
import com.backend.repositories.UserRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private RecruiterRepository recruiterRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private Cloudinary cloudinary;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public Candidate registerCandidate(CandidateRequest candidateRequest, MultipartFile resumeFile) throws Exception {

        Map uploadResult=cloudinary.uploader().upload(
                resumeFile.getBytes(),
                ObjectUtils.asMap(
                        "resource_type","raw"
                )
        );

        String resumeUrl=uploadResult.get("secure_url").toString();

        User user = new User();
        user.setEmail(candidateRequest.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(candidateRequest.getPassword()));
        user.setRole(Role.CANDIDATE);
        userRepository.save(user);

        Candidate candidate = new Candidate();
        candidate.setEmail(candidateRequest.getEmail());
        candidate.setUser(user);
        candidate.setSkills(candidateRequest.getSkills());
        candidate.setExperience(candidateRequest.getExperience());
        candidate.setName(candidateRequest.getName());
        candidate.setResumeUrl(resumeUrl);
        return candidateRepository.save(candidate);

    }

    public Recruiter registerRecruiter(RecruiterRequest recruiterRequest){

        User user = new User();
        user.setEmail(recruiterRequest.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(recruiterRequest.getPassword()));
        user.setRole(Role.RECRUITER);
        userRepository.save(user);


        Recruiter recruiter = new Recruiter();
        recruiter.setEmail(recruiterRequest.getEmail());
        recruiter.setUser(user);
        recruiter.setCompanyName(recruiterRequest.getCompanyName());
        recruiter.setDesignation(recruiterRequest.getDesignation());
        recruiter.setName(recruiterRequest.getName());
        return recruiterRepository.save(recruiter);
    }

    public String login(UserRequest userRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userRequest.getEmail(),
                        userRequest.getPassword()
                )
        );
        if(authentication.isAuthenticated()) {
            return "Success";
        }
        return null;
    }

}
