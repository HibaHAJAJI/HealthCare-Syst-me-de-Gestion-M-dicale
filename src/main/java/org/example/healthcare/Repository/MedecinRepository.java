package org.example.healthcare.Repository;

import org.example.healthcare.Entity.Medecin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedecinRepository extends JpaRepository<Medecin,Long> {

    @EntityGraph(attributePaths = {"rendezVous"})
    Page<Medecin> findBySpecialite(String specialite, Pageable pageable);

    @EntityGraph(attributePaths = {"rendezVous"})
    Page<Medecin> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"rendezVous"})
    Optional<Medecin> findById(Long id);
}
