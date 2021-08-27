package com.example.Test.controller;

import com.example.Test.auth.imp.CurrentPrincipalName;
import com.example.Test.dto.vacancyDto.CreateVacancyDto;
import com.example.Test.dto.vacancyDto.UpdateVacancyDto;
import com.example.Test.dto.vacancyDto.VacancyDto;
import com.example.Test.dtoService.VacancyDtoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vacancies")
public class VacancyController {

    private final VacancyDtoService vacancyDtoService;
    private final CurrentPrincipalName currentPrincipalName;

    public VacancyController(VacancyDtoService vacancyDtoService, CurrentPrincipalName currentPrincipalName) {
        this.vacancyDtoService = vacancyDtoService;
        this.currentPrincipalName = currentPrincipalName;
    }

    @PostMapping
    public VacancyDto create(@RequestBody CreateVacancyDto createVacancyDto) {
        return vacancyDtoService.create(createVacancyDto, currentPrincipalName.currentPrincipalName());
    }

    @PutMapping
    public VacancyDto update(@RequestBody UpdateVacancyDto updateVacancyDto) {
        return vacancyDtoService.update(updateVacancyDto, currentPrincipalName.currentPrincipalName());
    }

    @GetMapping("/{id}")
    public VacancyDto getById(@PathVariable("id") Long id) {
        return vacancyDtoService.getById(id);
    }

    @GetMapping
    public List<VacancyDto> getAll() {
        return vacancyDtoService.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        vacancyDtoService.delete(id);
    }
}