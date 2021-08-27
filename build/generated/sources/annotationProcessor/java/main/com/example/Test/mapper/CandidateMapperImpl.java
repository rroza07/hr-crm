package com.example.Test.mapper;

import com.example.Test.dto.candidateDto.CandidateDto;
import com.example.Test.entity.Candidate;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-08-28T00:22:19+0600",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.1.1.jar, environment: Java 11.0.11 (Ubuntu)"
)
public class CandidateMapperImpl implements CandidateMapper {

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
        candidateDto.setCv( candidate.getCv() );
        candidateDto.setFileName( candidate.getFileName() );
        candidateDto.setDepartment( candidate.getDepartment() );
        candidateDto.setDegree( candidate.getDegree() );
        candidateDto.setDeleted( candidate.isDeleted() );
        candidateDto.setNotes( candidate.getNotes() );

        return candidateDto;
    }
}
