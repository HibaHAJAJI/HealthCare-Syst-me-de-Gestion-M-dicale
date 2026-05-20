package org.example.healthcare.Repository;

import org.example.healthcare.Entity.RendezVous;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface RendezVousRepository extends JpaRepository<RendezVous,Long> {
    

    @Query("select rv from RendezVous  rv where rv.patient.id =:patientId")
    Page<RendezVous> findByPatientId(@Param("patientId")Long patientId, Pageable pageable);

    @Query("select rv from RendezVous  rv where rv.medecin.id =:medecintId")
    Page<RendezVous> findByMedecinId(@Param("medecintId")Long medecintId, Pageable pageable);

    Page<RendezVous> findByDateRendezVous(LocalDateTime dateRendezVous, Pageable pageable);

}
