package com.backend.repositories;

import com.backend.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job,Integer> {
    List<Job> findByRecruiterId(Integer recruiter_id);
}
