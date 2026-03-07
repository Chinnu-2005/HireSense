package com.backend.repositories;

import com.backend.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,Integer> {
}
