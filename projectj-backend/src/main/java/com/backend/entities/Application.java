package com.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Entity
@Table(name = "application",uniqueConstraints = @UniqueConstraint(columnNames = {"candidate_id","column_id"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    private String resumeUrl;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @CreationTimestamp
    @Column(name="applied_at",nullable = false)
    private LocalDateTime appliedAt;

}
