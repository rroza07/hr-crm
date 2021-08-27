package com.example.Test.dtoService.imp;

import com.example.Test.dto.candidateDto.CandidateDto;
import com.example.Test.dto.candidateDto.CreateCandidateDto;
import com.example.Test.dto.candidateDto.UpdateCandidateDto;
import com.example.Test.dtoService.CandidateDtoService;
import com.example.Test.entity.Candidate;
import com.example.Test.entity.User;
import com.example.Test.mapper.CandidateMapper;
import com.example.Test.service.CandidateService;
import com.example.Test.service.UserService;
import com.example.Test.service.VacancyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateDtoServiceImp implements CandidateDtoService {

    private final CandidateMapper candidateMapper;
    private final UserService userService;
    private final CandidateService candidateService;
    private final VacancyService vacancyService;

    @Value("${file.uploads.folder}")
    private String fileUploadsFolder;

    public CandidateDtoServiceImp(CandidateMapper candidateMapper, UserService userService,
                                  CandidateService candidateService, VacancyService vacancyService) {
        this.candidateMapper = candidateMapper;
        this.userService = userService;
        this.candidateService = candidateService;
        this.vacancyService = vacancyService;
    }

    @Override
    public CandidateDto create(CreateCandidateDto createCandidateDto, MultipartFile file,
                               String currentPrincipalName, Long vacancyId) throws IOException {
        Candidate candidate = new Candidate();
        User user = userService.findByUsername(currentPrincipalName);

        candidate.setFirstName(createCandidateDto.getFirstName());
        candidate.setLastName(createCandidateDto.getLastName());
        candidate.setEmail(createCandidateDto.getEmail());
        candidate.setPhoneNumber(createCandidateDto.getPhoneNumber());
        candidate.setSkype(createCandidateDto.getSkype());
        candidate.setDepartment(createCandidateDto.getDepartment());
        candidate.setDegree(createCandidateDto.getDegree());
        candidate.setNotes(createCandidateDto.getNotes());
        candidate.setVacancy(vacancyService.getById(vacancyId));
        candidate.setUser(user);
        candidate.setFileData(file.getBytes());

        CandidateDto saveCandidateDto = candidateMapper.toCandidateDto(candidateService.save(candidate));

        String filename = StringUtils.cleanPath(saveCandidateDto.getId() + file.getOriginalFilename());

        candidate.setFileName(filename);

        String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/" + vacancyId + "/candidates/" + saveCandidateDto.getId() + "/").
                path(filename).toUriString();

        candidate.setCv(fileUri);

        saveFileInFolder(filename, file);

        return candidateMapper.toCandidateDto(candidateService.save(candidate));
    }

    @Override
    public CandidateDto update(UpdateCandidateDto updateCandidateDto, MultipartFile file,
                               String currentPrincipalName, Long vacancyId) throws IOException {
        Candidate candidate = candidateService.getById(updateCandidateDto.getId());

        User user = userService.findByUsername(currentPrincipalName);

        candidate.setFirstName(updateCandidateDto.getFirstName());
        candidate.setLastName(updateCandidateDto.getLastName());
        candidate.setEmail(updateCandidateDto.getEmail());
        candidate.setPhoneNumber(updateCandidateDto.getPhoneNumber());
        candidate.setSkype(updateCandidateDto.getSkype());
        candidate.setDepartment(updateCandidateDto.getDepartment());
        candidate.setDegree(updateCandidateDto.getDegree());
        candidate.setNotes(updateCandidateDto.getNotes());
        candidate.setVacancy(vacancyService.getById(vacancyId));
        candidate.setUser(user);
        candidate.setFileData(file.getBytes());

        deleteFileInFolder(candidate.getFileName());

        String filename = StringUtils.cleanPath(candidate.getId() + file.getOriginalFilename());

        candidate.setFileName(filename);

        String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/" + vacancyId + "/candidates/" + candidate.getId() + "/").
                path(filename).toUriString();
        candidate.setCv(fileUri);

        saveFileInFolder(filename, file);

        return candidateMapper.toCandidateDto(candidateService.save(candidate));
    }

    @Override
    public CandidateDto getById(Long id) {
        return candidateMapper.toCandidateDto(candidateService.getById(id));
    }

    @Override
    public List<CandidateDto> getAll() {
        return candidateService.getAll()
                .stream()
                .map(candidateMapper::toCandidateDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id, String currentPrincipalName, Long vacancyId) {
        Candidate candidate = candidateService.getById(id);
        User user = userService.findByUsername(currentPrincipalName);

        deleteFileInFolder(candidate.getFileName());

        candidate.setDeleted(true);
        candidate.setUser(user);
        candidate.setVacancy(vacancyService.getById(vacancyId));

        candidateService.save(candidate);
    }

    @Override
    public Candidate getFile(CandidateDto candidateDto, String fileName) throws FileNotFoundException {
        return candidateService.getFile(candidateDto.getId());
    }

    private void deleteFileInFolder(String fileName) {
        File file2 = new File(fileUploadsFolder + "/" + fileName);
        file2.delete();
    }

    private void saveFileInFolder(String filename, MultipartFile file) throws IOException {

        File convertFile = new File(fileUploadsFolder + filename);
        convertFile.createNewFile();
        FileOutputStream fOut = new FileOutputStream(convertFile);
        fOut.write(file.getBytes());
        fOut.close();
    }
}