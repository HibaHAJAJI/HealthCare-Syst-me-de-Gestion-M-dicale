package org.example.healthcare.Repository;


import org.example.healthcare.Entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient,Long> {

    @EntityGraph(attributePaths = {"rendezVousList", "dossierMedical"})
    Page<Patient> findByUsernameContainingIgnoreCase(String username, Pageable pageable);

    @EntityGraph(attributePaths = {"rendezVousList", "dossierMedical"})
    Page<Patient> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"rendezVousList", "dossierMedical"})
    Optional<Patient> findById(Long id);
}
