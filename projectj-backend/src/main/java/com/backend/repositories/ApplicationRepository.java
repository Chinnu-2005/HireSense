package com.backend.repositories;

import com.backend.entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {

}
