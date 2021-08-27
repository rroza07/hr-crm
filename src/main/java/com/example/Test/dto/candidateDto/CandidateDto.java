package com.example.Test.dto.candidateDto;

import com.example.Test.dto.userDto.UserDto;
import com.example.Test.dto.vacancyDto.VacancyDto;
import com.example.Test.enums.Department;
import lombok.Data;

@Data
public class CandidateDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String skype;
    private String cv;
    private String fileName;
    private Department department;
    private String degree;
    private boolean isDeleted;
    private String notes;
    private VacancyDto vacancyDto;
    private UserDto userDto;
}