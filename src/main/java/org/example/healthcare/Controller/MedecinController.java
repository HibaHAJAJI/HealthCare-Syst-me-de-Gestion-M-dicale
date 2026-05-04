package org.example.healthcare.Controller;

import lombok.AllArgsConstructor;
import org.example.healthcare.Dto.MedecinDto;
import org.example.healthcare.Service.MedecinService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/medecins")
public class MedecinController {

    private final MedecinService medecinService;


    @GetMapping
    public List<MedecinDto> findAllMedecins(){
        return medecinService.getAllMedecins();
    }

    @PostMapping
    public MedecinDto saveMedecin(@RequestBody MedecinDto dto){
        return medecinService.saveMedecin(dto);
    }

    @PutMapping("/{id}")
    public MedecinDto updateMedecin(@PathVariable Long id,@RequestBody MedecinDto dto){
        return medecinService.updateMedecinById(id,dto);
    }
    @DeleteMapping("/{id}")
    public void deleteMedecin(@PathVariable Long id){
        medecinService.DeleteMedecinById(id);
    }
}
