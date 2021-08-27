package com.example.Test.service;

import com.example.Test.entity.Candidate;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;

@Service
public interface CandidateService {
    Candidate save(Candidate candidate);
    Candidate getById(Long id);
    List<Candidate> getAll();
    Candidate getFile(Long file_id) throws FileNotFoundException;
}