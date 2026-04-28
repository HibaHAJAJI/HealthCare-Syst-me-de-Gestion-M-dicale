package org.example.healthcare.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "medecin")
public class Medecin {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String specialite;
    private String email;
    private String telephone;

    @OneToMany(mappedBy = "medecin",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<RendezVous>rendezVous;
}
