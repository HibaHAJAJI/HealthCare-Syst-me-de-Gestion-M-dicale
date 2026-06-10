package org.example.healthcare.Repository;


import org.example.healthcare.Entity.DossierMedical;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DossierMedicalRepository extends JpaRepository<DossierMedical,Long> {

    @EntityGraph(attributePaths = {"patient"})
    Page<DossierMedical> findDossierMedicalByDiagnosticIsContainingIgnoreCase(String diagnostic, Pageable pageable);

    @EntityGraph(attributePaths = {"patient"})
    Page<DossierMedical> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"patient"})
    Optional<DossierMedical> findById(Long id);
}
