package com.example.Test.mapper.imp;

import com.example.Test.dto.candidateDto.CandidateDto;
import com.example.Test.entity.Candidate;
import com.example.Test.mapper.CandidateMapper;
import com.example.Test.mapper.UserMapper;
import com.example.Test.mapper.VacancyMapper;
import org.springframework.stereotype.Service;

@Service
public class CandidateMapperImp implements CandidateMapper{
    private final UserMapper userMapper;
    private final VacancyMapper vacancyMapper;

    public CandidateMapperImp(UserMapper userMapper, VacancyMapper vacancyMapper) {
        this.userMapper = userMapper;
        this.vacancyMapper = vacancyMapper;
    }

    @Override
    public CandidateDto toCandidateDto(Candidate candidate) {
        if ( candidate == null ) {
            return null;
        }

        CandidateDto candidateDto = new CandidateDto();

        candidateDto.setId( candidate.getId() );
        candidateDto.setFirstName( candidate.getFirstName() );
        candidateDto.setLastName( candidate.getLastName() );
        candidateDto.setEmail( candidate.getEmail() );
        candidateDto.setPhoneNumber( candidate.getPhoneNumber() );
        candidateDto.setSkype( candidate.getSkype() );
        candidateDto.setCv(candidate.getCv());
        candidateDto.setFileName(candidate.getFileName());
        candidateDto.setDepartment( candidate.getDepartment() );
        candidateDto.setDegree( candidate.getDegree() );
        candidateDto.setNotes( candidate.getNotes() );

        if (candidate.getVacancy() != null) {
            candidateDto.setVacancyDto(vacancyMapper.toVacancyDto(candidate.getVacancy()));
        }

        if (candidate.getUser() != null) {
            candidateDto.setUserDto(userMapper.toUserDto(candidate.getUser()));
        }

        return candidateDto;
    }
}