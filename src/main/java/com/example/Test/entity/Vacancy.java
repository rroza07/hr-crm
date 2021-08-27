package com.example.Test.entity;

import com.example.Test.enums.Requirement;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "vacancies")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String position;
    private String title;
    private String description;
    private boolean isDeleted;
    private Date createdAt;
    private Date modifiedAt;

    @JoinTable(name = "vacancy_requirement",
            joinColumns = @JoinColumn(name = "vacancy_req_FK")
    )
    @Column(name = "title", nullable = false)
    @LazyCollection(LazyCollectionOption.FALSE)
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Requirement.class)
    private List<Requirement> requirement;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable=false)
    private User user;

    @OneToMany(targetEntity = Vacancy.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id")
    private List<Candidate> candidates;
}