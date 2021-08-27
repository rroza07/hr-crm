package com.example.Test.service;

import com.example.Test.entity.Vacancy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VacancyService {
    Vacancy save(Vacancy vacancy);
    Vacancy getById(Long id);
    List<Vacancy> getAll();
}