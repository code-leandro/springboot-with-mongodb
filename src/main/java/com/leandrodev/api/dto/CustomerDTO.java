package com.leandrodev.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {

    @NotBlank(message = "First name cannot be empty. ")
    @Length(max = 64, message = "Fist name should shorter - maximum of 64 characters.")
    @Schema(example = "Leandro", description = "The first name of Customer", required = true)
    String firstName;

    @NotBlank(message = "Last name cannot be empty. ")
    @Length(max = 64, message = "Last name should shorter - maximum of 64 characters.")
    @Schema(example = "Souza", description = "The last name of Customer", required = true)
    String lastName;
}
