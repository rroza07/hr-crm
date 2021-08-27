package com.example.Test.dtoService.imp;

import com.example.Test.dto.vacancyDto.CreateVacancyDto;
import com.example.Test.dto.vacancyDto.UpdateVacancyDto;
import com.example.Test.dto.vacancyDto.VacancyDto;
import com.example.Test.dtoService.VacancyDtoService;
import com.example.Test.entity.User;
import com.example.Test.entity.Vacancy;
import com.example.Test.mapper.VacancyMapper;
import com.example.Test.service.UserService;
import com.example.Test.service.VacancyService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VacancyDtoServiceImp implements VacancyDtoService {

    private final UserService userService;
    private final VacancyService vacancyService;
    private final VacancyMapper vacancyMapper;

    public VacancyDtoServiceImp(UserService userService, VacancyService vacancyService,
                                VacancyMapper vacancyMapper) {
        this.userService = userService;
        this.vacancyService = vacancyService;
        this.vacancyMapper = vacancyMapper;
    }

    @Override
    public VacancyDto create(CreateVacancyDto createVacancyDto, String currentPrincipalName) {
        Vacancy vacancy = new Vacancy();

        User user = userService.findByUsername(currentPrincipalName);

        vacancy.setPosition(createVacancyDto.getPosition());
        vacancy.setRequirement(createVacancyDto.getRequirement());
        vacancy.setCreatedAt(new Date());
        vacancy.setUser(user);

        return vacancyMapper.toVacancyDto(vacancyService.save(vacancy));
    }

    @Override
    public VacancyDto update(UpdateVacancyDto updateVacancyDto, String currentPrincipalName) {
        Vacancy vacancy = vacancyService.getById(updateVacancyDto.getId());

        User user = userService.findByUsername(currentPrincipalName);

        vacancy.setPosition(updateVacancyDto.getPosition());
        vacancy.setTitle(updateVacancyDto.getTitle());
        vacancy.setDescription(updateVacancyDto.getDescription());
        vacancy.setModifiedAt(new Date());
        vacancy.setRequirement(updateVacancyDto.getRequirement());
        vacancy.setUser(user);

        return vacancyMapper.toVacancyDto(vacancyService.save(vacancy));
    }


    @Override
    public VacancyDto getById(Long id) {
        return vacancyMapper.toVacancyDto(vacancyService.getById(id));
    }

    @Override
    public List<VacancyDto> getAll() {
        return vacancyService.getAll()
                .stream()
                .map(vacancyMapper::toVacancyDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        Vacancy vacancy = vacancyService.getById(id);
        vacancy.setDeleted(true);
        vacancyService.save(vacancy);
    }
}