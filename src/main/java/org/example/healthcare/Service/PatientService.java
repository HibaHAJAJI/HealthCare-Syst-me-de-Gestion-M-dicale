package org.example.healthcare.Service;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "patient")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PatientService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private Long téléphone;
    private LocalDate dateNaissance;

}
