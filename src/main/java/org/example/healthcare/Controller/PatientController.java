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
    public List<PatientDto> findAllPatients(){
        return patientService.getAllPatients();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        patientService.deletePatient(id);
    }

    @PutMapping("update/{id}")
    public PatientDto update(@PathVariable Long id, @RequestBody PatientDto dto){
        return patientService.updatePatient(id, dto);
    }

    @GetMapping("/{id}")
    public PatientDto findPatientById(@PathVariable Long id){
        return patientService.getPatientById(id);
    }

}
