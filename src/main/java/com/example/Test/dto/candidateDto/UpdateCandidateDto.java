package com.example.Test.dto.candidateDto;

import com.example.Test.enums.Department;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class UpdateCandidateDto {
    @NotNull
    private Long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String skype;
    @NotNull
    private Department department;
    @NotNull
    private String degree;
    @NotNull
    private String notes;
}