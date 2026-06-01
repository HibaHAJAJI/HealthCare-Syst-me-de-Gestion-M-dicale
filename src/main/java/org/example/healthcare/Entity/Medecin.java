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
public class Medecin  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String specialite;
    private String telephone;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "medecin",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<RendezVous>rendezVous;
}
