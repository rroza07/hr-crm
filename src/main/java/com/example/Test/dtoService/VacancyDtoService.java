package com.example.Test.dtoService;

import com.example.Test.dto.vacancyDto.CreateVacancyDto;
import com.example.Test.dto.vacancyDto.UpdateVacancyDto;
import com.example.Test.dto.vacancyDto.VacancyDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VacancyDtoService {
    VacancyDto create(CreateVacancyDto createVacancyDto, String currentPrincipalName);
    VacancyDto getById(Long id);
    List<VacancyDto> getAll();
    VacancyDto update(UpdateVacancyDto updateVacancyDto, String currentPrincipalName);
    void delete(Long id);
}
