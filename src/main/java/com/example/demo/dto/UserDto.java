package com.example.demo.dto;

import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotNull(message = "username is required")
    private String name;
    @NotNull(message = "email is mandatory")
    @Email(message = "invalid email address")
    private String email;
    @NotNull(message = "mobile number is required")
    private String mobile;
    @NotNull(message = "gender is required")
    private String gender;
    @Max(60)@Min(18)
    private int age;
    @NotBlank
    private String nationality;
}
