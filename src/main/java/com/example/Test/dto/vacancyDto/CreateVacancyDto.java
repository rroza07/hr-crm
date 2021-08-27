package com.example.Test.dto.vacancyDto;

import com.example.Test.enums.Requirement;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Data
public class CreateVacancyDto {
    @NotNull
    private String position;
    @NotNull
    private List<Requirement> requirement;
}