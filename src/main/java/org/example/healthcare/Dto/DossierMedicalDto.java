package org.example.healthcare.Dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DossierMedicalDto {

    private Long id;

    @NotBlank(message = "Le champs diagnostic est invalide !")
    private String diagnostic;

    @NotBlank(message = "Le champs observation est invalide !")
    private String observation;

    private LocalDate dateCreation;

    private Long patientId;
}
