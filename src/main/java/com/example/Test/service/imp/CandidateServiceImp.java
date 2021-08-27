package com.example.Test.service.imp;

import com.example.Test.constants.FileErrors;
import com.example.Test.entity.Candidate;
import com.example.Test.repository.CandidateRepository;
import com.example.Test.service.CandidateService;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;

@Service
public class CandidateServiceImp implements CandidateService {
    private final CandidateRepository candidateRepository;

    public CandidateServiceImp(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public Candidate save(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    @Override
    public Candidate getById(Long id) {
        return candidateRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Candidate> getAll() {
        return candidateRepository.findAllByIsDeletedFalse();
    }

    @Override
    public Candidate getFile(Long file_id) throws FileNotFoundException {
        return candidateRepository.findById(file_id).orElseThrow(() -> new FileNotFoundException(FileErrors.FILE_NOT_FOUND + file_id));
    }
}