package org.example.healthcare.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.example.healthcare.Users.User;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "patient")
@PrimaryKeyJoinColumn(name = "id")
public class Patient extends User {

    private String telephone;
    private LocalDate dateNaissance;


    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<RendezVous>rendezVousList;

    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
    private DossierMedical dossierMedical;
}
