package com.example.Test.mapper;

import com.example.Test.dto.vacancyDto.VacancyDto;
import com.example.Test.entity.Vacancy;
import org.mapstruct.Mapper;

@Mapper
public interface VacancyMapper {
    VacancyDto toVacancyDto(Vacancy vacancy);
}