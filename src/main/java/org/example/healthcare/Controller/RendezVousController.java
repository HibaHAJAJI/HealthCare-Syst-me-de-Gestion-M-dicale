package org.example.healthcare.Controller;

import org.example.healthcare.Dto.RendezVousDto;
import org.example.healthcare.Service.RendezVousService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rendez-vous")
public class RendezVousController {

    private final RendezVousService rendezVousService;

    public RendezVousController(RendezVousService rendezVousService) {
        this.rendezVousService = rendezVousService;
    }

    @GetMapping
    public List<RendezVousDto> findAllRendezVous(){
        return rendezVousService.getAllRendezVous();
    }

    @PostMapping
    public RendezVousDto saveRendezVous(@RequestBody RendezVousDto dto){
        return rendezVousService.addRendezVous(dto);
    }

    @PutMapping("/update/{id}")
    public RendezVousDto update(@PathVariable Long id,@RequestBody RendezVousDto dto){
        return rendezVousService.updateRendezVous(id,dto) ;
    }

}
