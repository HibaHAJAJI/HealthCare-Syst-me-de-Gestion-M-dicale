package org.example.healthcare.Repository;

import org.example.healthcare.Entity.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RendezVousRepository extends JpaRepository<RendezVous,Long> {

    @Query("select rv from RendezVous  rv where rv.patient.id =:id")
    List<RendezVous> findByPatientId(@Param("id")Long id);

    @Query(value = "select *from rendez_vous rv where medecin_id =:id ",nativeQuery = true)
    List<RendezVous>findByMedecinId(@Param("id")Long id);
}
