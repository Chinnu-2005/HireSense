package com.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="candidate")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candidate {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(unique = true)
    private String email;

    @ElementCollection
    private List<String> skills;

    private int experience;

    private String resumeUrl;

    @OneToMany(mappedBy = "candidate")
    private List<Application> applications;
}
