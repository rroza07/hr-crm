package com.example.Test.dtoService;

import com.example.Test.dto.email.Email;
import org.springframework.stereotype.Service;

@Service
public interface SendEmailDtoService {
    void sendEmail(Email email, Long candidateId);
}