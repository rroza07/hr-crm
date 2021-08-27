package com.example.Test.mapper;

import com.example.Test.dto.candidateDto.CandidateDto;
import com.example.Test.entity.Candidate;
import org.mapstruct.Mapper;

@Mapper
public interface CandidateMapper {
    CandidateDto toCandidateDto(Candidate candidate);
}