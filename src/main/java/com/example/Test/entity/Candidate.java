package com.example.Test.entity;

import com.example.Test.enums.Department;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Data
@Table(name = "candidates")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String phoneNumber;
    private String skype;
    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    private byte[] fileData;
    private String cv;
    private String fileName;
    @Enumerated(EnumType.STRING)
    private Department department;

    private String degree;
    private boolean isDeleted;
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vacancy_id", nullable=false)
    private Vacancy vacancy;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable=false)
    private User user;
}