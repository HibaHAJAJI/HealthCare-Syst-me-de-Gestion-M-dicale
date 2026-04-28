package org.example.healthcare.Dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DossierMedicalDto {

    private Long id;
    private String diagnostic;
    private String observation;
    private LocalDate dateCreation;
    private Long patientId;
}
