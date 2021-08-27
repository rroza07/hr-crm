package com.example.Test.dtoService.imp;

import com.example.Test.config.MailConfig;
import com.example.Test.dto.email.Email;
import com.example.Test.dtoService.SendEmailDtoService;
import com.example.Test.entity.Candidate;
import com.example.Test.service.CandidateService;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class SendEmailDtoServiceImp implements SendEmailDtoService {

    private final CandidateService candidateService;
    private final MailConfig mailConfig;

    public SendEmailDtoServiceImp(CandidateService candidateService, MailConfig mailConfig) {
        this.candidateService = candidateService;
        this.mailConfig = mailConfig;
    }

    @Override
    public void sendEmail(Email email, Long candidateId) {

        Candidate candidate = candidateService.getById(candidateId);

        try {
            MimeMessage msg = new MimeMessage(mailConfig.getSession());
            msg.setSubject(email.getSubject());
            msg.setText(email.getMessage());
            msg.setFrom(new InternetAddress(mailConfig.getSenderEmailID()));
            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(candidate.getEmail()));
            Transport.send(msg);
            System.out.println("Message send Successfully:)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}