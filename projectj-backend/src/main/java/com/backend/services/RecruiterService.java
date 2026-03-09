package com.backend.services;

import com.backend.entities.Application;
import com.backend.entities.ApplicationStatus;
import com.backend.entities.Job;
import com.backend.entities.Recruiter;
import com.backend.repositories.ApplicationRepository;
import com.backend.repositories.JobRepository;
import com.backend.repositories.RecruiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruiterService {

    @Autowired
    private RecruiterRepository recruiterRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

//    public Recruiter registerRecruiter(Recruiter recruiter){
//
//        return recruiterRepository.save(recruiter);
//    }

    public Job createJob(Job job){
        return jobRepository.save(job);
    }

    public List<Job> getAllJobs(Integer recruiter_id){
        return jobRepository.findByRecruiterId(recruiter_id);
    }

    public Application updateApplication(int application_id, ApplicationStatus applicationStatus){
        Application application=applicationRepository.findById(application_id).orElseThrow(()->new RuntimeException("Application not found"));

        application.setApplicationStatus(applicationStatus);

        return applicationRepository.save(application);
    }

}
