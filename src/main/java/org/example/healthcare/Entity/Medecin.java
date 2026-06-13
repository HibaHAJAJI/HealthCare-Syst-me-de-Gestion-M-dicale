package org.example.healthcare.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.healthcare.Users.User;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "medecin")
@PrimaryKeyJoinColumn(name = "id")
public class Medecin extends User {

    private String specialite;
    private String telephone;



    @OneToMany(mappedBy = "medecin",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<RendezVous>rendezVous;
}
