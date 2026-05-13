package org.example.healthcare.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.healthcare.Dto.PatientDto;
import org.example.healthcare.Service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientDto>  savePatient(@Valid @RequestBody PatientDto dto){
        return new ResponseEntity<>( patientService.addPatient(dto), HttpStatus.CREATED);
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
    public ResponseEntity<PatientDto>  update(@PathVariable Long id,@Valid @RequestBody PatientDto dto){
        return new ResponseEntity<>(patientService.updatePatient(id, dto),HttpStatus.CREATED) ;
    }

    @GetMapping("/{id}")
    public PatientDto findPatientById(@PathVariable Long id){
        return patientService.getPatientById(id);
    }

}
