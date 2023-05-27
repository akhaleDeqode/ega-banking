package com.ega.banking.dto;

import com.ega.banking.constants.ApplicationConstants;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = ApplicationConstants.INVALID_EMAIL)
    private String email;
    private String password;
}
