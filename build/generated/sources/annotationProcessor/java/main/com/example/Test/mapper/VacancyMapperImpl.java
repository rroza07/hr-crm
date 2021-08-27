package com.example.Test.mapper;

import com.example.Test.dto.vacancyDto.VacancyDto;
import com.example.Test.entity.Vacancy;
import com.example.Test.enums.Requirement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-08-28T00:22:19+0600",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.1.1.jar, environment: Java 11.0.11 (Ubuntu)"
)
public class VacancyMapperImpl implements VacancyMapper {

    @Override
    public VacancyDto toVacancyDto(Vacancy vacancy) {
        if ( vacancy == null ) {
            return null;
        }

        VacancyDto vacancyDto = new VacancyDto();

        vacancyDto.setId( vacancy.getId() );
        vacancyDto.setPosition( vacancy.getPosition() );
        vacancyDto.setTitle( vacancy.getTitle() );
        vacancyDto.setDescription( vacancy.getDescription() );
        vacancyDto.setCreatedAt( vacancy.getCreatedAt() );
        vacancyDto.setModifiedAt( vacancy.getModifiedAt() );
        List<Requirement> list = vacancy.getRequirement();
        if ( list != null ) {
            vacancyDto.setRequirement( new ArrayList<Requirement>( list ) );
        }

        return vacancyDto;
    }
}
