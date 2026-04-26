package org.example.healthcare.Controller;

import org.example.healthcare.Dto.PatientDto;
import org.example.healthcare.Service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }
    @PostMapping
    public PatientDto savePatient(@RequestBody PatientDto dto){
        return patientService.addPatient(dto);
    }

    @GetMapping
    public List<PatientDto> findAllPatient(){
        return patientService.getAllPatient();
    }
}
