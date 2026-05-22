package org.example.healthcare.Repository;

import org.example.healthcare.Entity.Medecin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository extends JpaRepository<Medecin,Long> {

    Page<Medecin> findBySpecialite(String specialite, Pageable pageable);
}
