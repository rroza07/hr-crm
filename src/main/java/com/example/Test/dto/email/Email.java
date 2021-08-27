package com.example.Test.dto.email;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class Email {
    @NotNull
    private String subject;
    @NotNull
    private String message;
}