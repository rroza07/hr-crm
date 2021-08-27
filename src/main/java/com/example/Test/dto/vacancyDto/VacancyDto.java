package com.example.Test.dto.vacancyDto;

import com.example.Test.dto.userDto.UserDto;
import com.example.Test.enums.Requirement;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class VacancyDto {
    private Long id;
    private String position;
    private String title;
    private String description;
    private boolean isDelete;
    private Date createdAt;
    private Date modifiedAt;
    private List<Requirement> requirement;
    private UserDto userDto;
}