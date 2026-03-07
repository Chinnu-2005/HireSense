package com.backend.controllers;

import com.backend.entities.Job;
import com.backend.entities.Recruiter;
import com.backend.entities.Response;
import com.backend.repositories.ApplicationRepository;
import com.backend.repositories.RecruiterRepository;
import com.backend.services.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Stream.builder;

//create-profile
//create-job
//view-applications-for-each-job
//update-status-of-appication
//view-jobs-posted-by-recruiter

@RestController
@RequestMapping("/recruiter")
public class RecruiterController {

    @Autowired
    private RecruiterService recruiterService;

    @PostMapping("/create-profile")
    public Response<Recruiter> createRecruiter(@RequestBody Recruiter recruiter) throws Exception {
        Recruiter savedRecruiter = null;
        try{
            savedRecruiter=recruiterService.saveRecruiter(recruiter);
        }catch(Exception e){
            return Response.<Recruiter>builder().message("Internal Server error" + e.getMessage()).statusCode(500).data(savedRecruiter).build();
        }
        return Response.<Recruiter>builder().message("Recruiter profile created Successfully").statusCode(200).data(savedRecruiter).build();
    }


    @PostMapping("/post-job")
    public Response<Job> postJob(@RequestBody Job job) throws Exception {
        Job savedJob=null;
        try{
            savedJob=recruiterService.createJob(job);
        }catch(Exception e){
            return Response.<Job>builder().message("Internal Server error" + e.getMessage()).statusCode(500).data(savedJob).build();
        }
        return Response.<Job>builder().message("Job posted Successfully").statusCode(200).data(savedJob).build();
    }

    @GetMapping("/get-jobs-posted/{recruiter_id}")
    public Response<List<Job>> getJobsPosted(@PathVariable("recruiter_id") int recruiter_id){
        Job jobs=null;



    }



}
