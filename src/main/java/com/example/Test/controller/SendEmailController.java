package com.example.Test.controller;

import com.example.Test.dto.email.Email;
import com.example.Test.dtoService.SendEmailDtoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/{candidateId}/sendEmail")
public class SendEmailController {

    private final SendEmailDtoService sendEmailDtoService;

    public SendEmailController(SendEmailDtoService sendEmailDtoService) {
        this.sendEmailDtoService = sendEmailDtoService;
    }

    @PostMapping
    public String sendEmail(@RequestBody Email email, @PathVariable Long candidateId){
            sendEmailDtoService.sendEmail(email, candidateId);
            return "Email sent successfully";
    }
}