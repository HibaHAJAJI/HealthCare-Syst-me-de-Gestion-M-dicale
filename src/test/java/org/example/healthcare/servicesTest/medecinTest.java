package org.example.healthcare.servicesTest;

import jakarta.transaction.Transactional;
import org.example.healthcare.Dto.MedecinDto;
import org.example.healthcare.Entity.Medecin;
import org.example.healthcare.Repository.MedecinRepository;
import org.example.healthcare.Service.MedecinService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class medecinTest {

    @Autowired
    private MedecinRepository repository;
    @Autowired
    private MedecinService service;

    @Test
    void should_Create_Medecin(){
        MedecinDto dto=new MedecinDto();
        dto.setNom("imane");
        dto.setEmail("sr@email.com");
        dto.setSpecialite("generaliste");
        dto.setTelephone("0987654");

        MedecinDto result =service.saveMedecin(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
    }

    @Test
    void should_update_medecin() {

        Medecin medecin = new Medecin();

        medecin.setNom("yasmine");
        medecin.setEmail("dr@email.com");
        medecin.setSpecialite("cardoligie");
        medecin.setTelephone("0987654");

        Medecin result = repository.save(medecin);

        MedecinDto dto = new MedecinDto();
        dto.setNom("yasmine updated");
        dto.setEmail("dr@email.com");
        dto.setSpecialite("neurologie");
        dto.setTelephone("1111111");

        MedecinDto updated = service.updateMedecinById(result.getId(), dto);

        assertNotNull(updated);
        assertNotNull(updated.getId());
        assertEquals("neurologie", updated.getSpecialite());
    }

    @Test
    void should_delete_medecin(){

        Medecin medecin = new Medecin();

        medecin.setNom("yasmine");
        medecin.setEmail("dr@email.com");
        medecin.setSpecialite("cardoligie");
        medecin.setTelephone("0987654");

        Medecin del = repository.save(medecin);

        service.DeleteMedecinById(del.getId());

        Boolean result = repository.existsById(del.getId());
        assertFalse(result);
    }

    @Test
    void should_getAll_medecin(){
        Medecin medecin = new Medecin();

        medecin.setNom("karima");
        medecin.setEmail("dr@email.com");
        medecin.setSpecialite("cardoligie");
        medecin.setTelephone("0987654");
        repository.save(medecin);
        Medecin medecin1 = new Medecin();

        medecin1.setNom("noura");
        medecin1.setEmail("nr@email.com");
        medecin1.setSpecialite("neurlogie");
        medecin1.setTelephone("04567875");
        repository.save(medecin1);
        List<MedecinDto> result = service.getAllMedecins();

        assertNotNull(result);
        assertEquals(3,result.size());
    }
}
