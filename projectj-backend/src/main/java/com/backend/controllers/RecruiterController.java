package com.backend.controllers;

import com.backend.entities.*;
import com.backend.repositories.ApplicationRepository;
import com.backend.repositories.RecruiterRepository;
import com.backend.services.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

//    @PostMapping("/create-profile")
//    public Response<Recruiter> createRecruiter(@RequestBody Recruiter recruiter) throws Exception {
//        Recruiter savedRecruiter = null;
//        try{
//            savedRecruiter=recruiterService.saveRecruiter(recruiter);
//        }catch(Exception e){
//            return Response.<Recruiter>builder().message("Internal Server error" + e.getMessage()).statusCode(500).data(savedRecruiter).build();
//        }
//        return Response.<Recruiter>builder().message("Recruiter profile created Successfully").statusCode(200).data(savedRecruiter).build();
//    }


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
    public Response<List<Job>> getJobsPosted(@PathVariable("recruiter_id") int recruiter_id) throws Exception {
        List<Job> jobs=new ArrayList<>();

        try {
            jobs=recruiterService.getAllJobs(recruiter_id);
            if(jobs.isEmpty()){
                return Response.<List<Job>>builder().message("No jobs posted by this recruiter").statusCode(400).data(jobs).build();
            }

        }catch(Exception e){
            return Response.<List<Job>>builder().message("Internal Server error" + e.getMessage()).statusCode(500).data(jobs).build();
        }
        return Response.<List<Job>>builder().message("Jobs retrived Successfully").statusCode(200).data(jobs).build();

    }

    @PatchMapping("/update-status/{application_id}")
    public Response<Application> updateStatus(@PathVariable("application_id") int application_id, @RequestParam ApplicationStatus applicationStatus) throws Exception {
        Application updatedApplication=null;
        try{
            updatedApplication=recruiterService.updateApplication(application_id,applicationStatus);
        }catch(Exception e){
            return Response.<Application>builder().message("Internal Server error" + e.getMessage()).statusCode(500).data(updatedApplication).build();
        }
        return Response.<Application>builder().message("Application updated successfully").statusCode(200).data(updatedApplication).build();
    }



}
