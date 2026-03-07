package com.backend.services;

import com.backend.entities.Job;
import com.backend.entities.Recruiter;
import com.backend.repositories.JobRepository;
import com.backend.repositories.RecruiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecruiterService {

    @Autowired
    private RecruiterRepository recruiterRepository;

    @Autowired
    private JobRepository jobRepository;

    public Recruiter saveRecruiter(Recruiter recruiter){
        return recruiterRepository.save(recruiter);
    }

    public Job createJob(Job job){
        return jobRepository.save(job);
    }

}
