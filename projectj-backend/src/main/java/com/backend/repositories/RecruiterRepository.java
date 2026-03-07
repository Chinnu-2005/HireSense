package com.backend.repositories;

import com.backend.entities.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruiterRepository extends JpaRepository<Recruiter,Integer> {
}
