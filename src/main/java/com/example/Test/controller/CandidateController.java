package com.example.Test.controller;

import com.example.Test.auth.imp.CurrentPrincipalName;
import com.example.Test.dto.candidateDto.CandidateDto;
import com.example.Test.dto.candidateDto.CreateCandidateDto;
import com.example.Test.dto.candidateDto.UpdateCandidateDto;
import com.example.Test.dtoService.CandidateDtoService;
import com.example.Test.entity.Candidate;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/{vacancyId}/candidates")
public class CandidateController {

    private final CandidateDtoService candidateDtoService;
    private final CurrentPrincipalName currentPrincipalName;

    public CandidateController(CandidateDtoService candidateDtoService,
                               CurrentPrincipalName currentPrincipalName) {
        this.candidateDtoService = candidateDtoService;
        this.currentPrincipalName = currentPrincipalName;
    }

    @PostMapping
    public CandidateDto create(@RequestPart(value = "candidate", required = false) CreateCandidateDto createCandidateDto,
                               @RequestPart(value = "file", required = false) MultipartFile file,
                               @PathVariable Long vacancyId) throws IOException {

        return candidateDtoService.create(createCandidateDto, file, currentPrincipalName.currentPrincipalName(), vacancyId);
    }

    @PutMapping
    public CandidateDto update(@RequestPart(value = "candidate", required = false) UpdateCandidateDto updateCandidateDto,
                               @RequestPart(value = "file", required = false) MultipartFile file,
                               @PathVariable Long vacancyId) throws IOException {

        return candidateDtoService.update(updateCandidateDto, file, currentPrincipalName.currentPrincipalName(), vacancyId);
    }

    @GetMapping("/{candidateId}/{fileName}/")
    public ResponseEntity<Object> getFile(@PathVariable("candidateId") Long id,
                                          @PathVariable("fileName") String fileName) throws FileNotFoundException {

        CandidateDto candidateDto = candidateDtoService.getById(id);
        Candidate candidate = candidateDtoService.getFile(candidateDto, fileName);
        return ResponseEntity.ok().
                header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + candidate.getFileName() + "\"").
                body(new ByteArrayResource(candidate.getFileData()));
    }

    @GetMapping("/{id}")
    public CandidateDto getById(@PathVariable("id") Long id) {
        return candidateDtoService.getById(id);
    }

    @GetMapping
    public List<CandidateDto> getAll() {
        return candidateDtoService.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id,
                       @PathVariable Long vacancyId) throws IOException {

        candidateDtoService.delete(id, currentPrincipalName.currentPrincipalName(), vacancyId);
    }
}