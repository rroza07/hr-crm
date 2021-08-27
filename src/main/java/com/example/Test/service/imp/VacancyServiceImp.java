package com.example.Test.service.imp;

import com.example.Test.entity.Vacancy;
import com.example.Test.repository.VacancyRepository;
import com.example.Test.service.VacancyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacancyServiceImp implements VacancyService {

    private final VacancyRepository vacancyRepository;

    public VacancyServiceImp(VacancyRepository vacancyRepository) {
        this.vacancyRepository = vacancyRepository;
    }

    @Override
    public Vacancy save(Vacancy vacancy) {
        return vacancyRepository.save(vacancy);
    }

    @Override
    public Vacancy getById(Long id) {
        return vacancyRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Vacancy> getAll() {
        return vacancyRepository.findAllByIsDeletedFalse();
    }
}