package com.example.demo.models;

import com.example.demo.utils.validations.password.ValidPassword;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    private Long id;

    @NotNull(message = "No puede ser nulo")
    private String userName;

    @NotNull(message = "No puede ser nulo")
    @ValidPassword
    private String password;

    @Email(message = "No cumple con formato email ejemplo@ejemplo")
    private String email;
    private String avatar;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime lastLoginDate;
}
