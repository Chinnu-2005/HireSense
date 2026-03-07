package com.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="job")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="recruiter_id")
    private Recruiter recruiter;

    @OneToMany(mappedBy = "job")
    private List<Application> applications;

    private String role;

    private String description;

    @ElementCollection
    private List<String> skills;

    private int experience;

    @CreationTimestamp
    @Column(name="posted_at",nullable = false)
    private LocalDateTime postedAt;

}
