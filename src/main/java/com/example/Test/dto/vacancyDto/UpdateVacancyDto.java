package com.example.Test.dto.vacancyDto;

import com.example.Test.enums.Requirement;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Data
public class UpdateVacancyDto {
    @NotNull
    private Long id;
    @NotNull
    private String position;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private List<Requirement> requirement;
}