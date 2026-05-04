package org.example.healthcare.Service;


import lombok.AllArgsConstructor;
import org.example.healthcare.Dto.RendezVousDto;
import org.example.healthcare.Entity.Medecin;
import org.example.healthcare.Entity.Patient;
import org.example.healthcare.Entity.RendezVous;
import org.example.healthcare.Enum.Statut;
import org.example.healthcare.Mapper.RendezVousMapper;
import org.example.healthcare.Repository.MedecinRepository;
import org.example.healthcare.Repository.PatientRepository;
import org.example.healthcare.Repository.RendezVousRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RendezVousService {

    private final RendezVousMapper rendezVousMapper;
    private final RendezVousRepository rendezVousRepository;
    private final MedecinRepository medecinRepository;
    private final PatientRepository patientRepository;


    public RendezVousDto addRendezVous(RendezVousDto dto){
        Patient patient =patientRepository.findById(dto.getPatientId())
                .orElseThrow(()->new  RuntimeException("patient id introuvable"));
        Medecin medecin=medecinRepository.findById(dto.getMedecinId())
                .orElseThrow(()->new RuntimeException("Medecin introuvable !"));

        RendezVous rendezVous=rendezVousMapper.toEntity(dto);
         rendezVous.setPatient(patient);
         rendezVous.setMedecin(medecin);
         rendezVous.setStatut(Statut.EN_ATTENTE);
       return rendezVousMapper.toDto(rendezVousRepository.save(rendezVous));
    }

    public List<RendezVousDto> getAllRendezVous(){
        return rendezVousMapper.toDtos(rendezVousRepository.findAll());
    }

   public RendezVousDto updateRendezVous(Long id,RendezVousDto dto){
        RendezVous rendezVous = rendezVousRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Rendez-vous introuvable"));

        if(dto.getMedecinId()!=null){
          Medecin medecin=medecinRepository.findById(dto.getPatientId())
                  .orElseThrow(()->new RuntimeException("aucun medecin !"));
           medecinRepository.save(medecin);
        }
        if (dto.getPatientId()!=null){
      Patient patient = patientRepository.findById(dto.getPatientId())
              .orElseThrow(()->new RuntimeException("aucun patient"));
          patientRepository.save(patient);
        }
        rendezVousMapper.updateRendezVous(dto,rendezVous);
        return rendezVousMapper.toDto(rendezVousRepository.save(rendezVous));
    }

    public RendezVousDto annulerRendezVous(Long id){
        RendezVous rendezVous = rendezVousRepository.findById(id)
                .orElseThrow(()->new RuntimeException("aucun rendez-vous !"));
        rendezVous.setStatut(Statut.ANNULE);
        return rendezVousMapper.toDto(rendezVousRepository.save(rendezVous));
    }

    public List<RendezVousDto> getRendezVousByPatientById(Long id){
        return  rendezVousMapper.toDtos(rendezVousRepository.findByPatientId(id));
    }

    public List<RendezVousDto> getRendezVousByMedecinById(Long id){
        return rendezVousMapper.toDtos(rendezVousRepository.findByMedecinId(id));
    }
}
