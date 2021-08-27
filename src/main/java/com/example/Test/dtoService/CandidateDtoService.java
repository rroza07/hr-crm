package com.example.Test.dtoService;

import com.example.Test.dto.candidateDto.CandidateDto;
import com.example.Test.dto.candidateDto.CreateCandidateDto;
import com.example.Test.dto.candidateDto.UpdateCandidateDto;
import com.example.Test.entity.Candidate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public interface CandidateDtoService {
    CandidateDto create(CreateCandidateDto createCandidateDto,
                        MultipartFile file,
                        String currentPrincipalName,
                        Long vacancyId) throws IOException;

    CandidateDto update(UpdateCandidateDto updateCandidateDto,
                        MultipartFile file,
                        String currentPrincipalName,
                        Long vacancyId) throws IOException;

    CandidateDto getById(Long id);

    List<CandidateDto> getAll();

    void delete(Long id, String currentPrincipalName, Long vacancyId);

    Candidate getFile(CandidateDto candidateDto, String fileName) throws FileNotFoundException;
}