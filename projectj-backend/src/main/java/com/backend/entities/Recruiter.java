package com.backend.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="recruiter")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recruiter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(unique = true)
    private String email;

    private String companyName;

    private String designation;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
