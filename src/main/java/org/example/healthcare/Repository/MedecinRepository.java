package org.example.healthcare.Repository;

import org.example.healthcare.Entity.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository extends JpaRepository<Medecin,Long> {
}
