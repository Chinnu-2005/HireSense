package com.backend.services;

import com.backend.entities.Application;
import com.backend.entities.ApplicationStatus;
import com.backend.entities.Candidate;
import com.backend.entities.Job;
import com.backend.repositories.ApplicationRepository;
import com.backend.repositories.CandidateRepository;
import com.backend.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

//    public Candidate registerCandicate(){
//
//    }

    public Application applyForJob(int candidate_id, int job_id){
        Candidate candidate = candidateRepository.findById(candidate_id).orElse(null);
        if(null == candidate){
            return null;
        }
        Job job=jobRepository.findById(job_id).orElse(null);
        if(null == job){
            return null;
        }

        Application application = new Application();
        application.setCandidate(candidate);
        application.setJob(job);
        application.setApplicationStatus(ApplicationStatus.APPLIED);
        application.setResumeUrl(candidate.getResumeUrl());

        return applicationRepository.save(application);
    }

    public List<Job> getAllJobs(){
        return jobRepository.findAll();
    }


    public List<Application> getAllApplications(int candidate_id){
        return applicationRepository.findByCandidateId(candidate_id);
    }

}
