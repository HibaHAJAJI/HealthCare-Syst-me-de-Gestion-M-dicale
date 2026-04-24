package org.example.healthcare.Repository;


import org.example.healthcare.Entity.DossierMedical;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DossierMedicalRepository extends JpaRepository<DossierMedical,Long> {
}
