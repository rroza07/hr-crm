package com.example.Test.mapper.imp;

import com.example.Test.dto.vacancyDto.VacancyDto;
import com.example.Test.entity.Vacancy;
import com.example.Test.enums.Requirement;
import com.example.Test.mapper.UserMapper;
import com.example.Test.mapper.VacancyMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VacancyMapperImp implements VacancyMapper {
    private final UserMapper userMapper;

    public VacancyMapperImp(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public VacancyDto toVacancyDto(Vacancy vacancy) {
        if (vacancy == null) {
            return null;
        }

        VacancyDto vacancyDto = new VacancyDto();

        vacancyDto.setId(vacancy.getId());
        vacancyDto.setPosition(vacancy.getPosition());
        vacancyDto.setTitle(vacancy.getTitle());
        vacancyDto.setDescription(vacancy.getDescription());
        vacancyDto.setCreatedAt(vacancy.getCreatedAt());
        vacancyDto.setModifiedAt(vacancy.getModifiedAt());

        List<Requirement> list = vacancy.getRequirement();
        if (list != null) {
            vacancyDto.setRequirement(new ArrayList<>(list));
        }

        if (vacancy.getUser() != null) {
            vacancyDto.setUserDto(userMapper.toUserDto(vacancy.getUser()));
        }

        return vacancyDto;
    }
}