package org.example.healthcare.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.healthcare.Enum.Statut;

import java.time.LocalDateTime;

@Getter
@Setter
public class RendezVousDto {

    private Long id;
    private LocalDateTime dateRendezVous;
    private Statut statut;

    @NotNull
    private Long medecinId;

    @NotNull
    private Long patientId ;
}
