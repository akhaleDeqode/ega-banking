package com.ega.banking.dto;

import com.ega.banking.constants.ApplicationConstants;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BearerTokenDTO {

    @NotBlank(message = ApplicationConstants.INVALID_TOKEN)
    private String token;
}
