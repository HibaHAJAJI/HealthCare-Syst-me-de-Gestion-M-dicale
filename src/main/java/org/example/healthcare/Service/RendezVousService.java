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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


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

    public Page<RendezVousDto> getAllRendezVous(Pageable pageable){
        return rendezVousRepository.findAll(pageable).map(rendezVousMapper::toDto);
    }

   public RendezVousDto updateRendezVous(Long id,RendezVousDto dto){

        RendezVous rendezVous = rendezVousRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Rendez-vous introuvable"));

        if(dto.getMedecinId()!=null){
          Medecin medecin=medecinRepository.findById(dto.getMedecinId())
                  .orElseThrow(()->new RuntimeException("aucun medecin !"));
            rendezVous.setMedecin(medecin);
        }

        if (dto.getPatientId()!=null){
      Patient patient = patientRepository.findById(dto.getPatientId())
              .orElseThrow(()->new RuntimeException("aucun patient"));
           rendezVous.setPatient(patient);
        }

       rendezVous.setDateRendezVous(dto.getDateRendezVous());
       rendezVous.setStatut(dto.getStatut());
        rendezVousMapper.updateRendezVous(dto,rendezVous);
        return rendezVousMapper.toDto(rendezVousRepository.save(rendezVous));
    }

    public RendezVousDto annulerRendezVous(Long id){
        RendezVous rendezVous = rendezVousRepository.findById(id)
                .orElseThrow(()->new RuntimeException("aucun rendez-vous !"));
        rendezVous.setStatut(Statut.ANNULE);
        return rendezVousMapper.toDto(rendezVousRepository.save(rendezVous));
    }

    public Page<RendezVousDto> getRendezVousByPatientById(Long id, Pageable pageable){
        return  rendezVousRepository.findByPatientId(id,pageable).map(rendezVousMapper::toDto);
    }

    public Page<RendezVousDto> getRendezVousByMedecinById(Long medecintId,Pageable pageable){
        return rendezVousRepository.findByMedecinId(medecintId,pageable).map(rendezVousMapper::toDto);
    }
    public Page<RendezVousDto> chercherParStatut(Statut statut, Pageable pageable){
        return rendezVousRepository
                .findByStatut(statut,pageable)
                .map(rendezVousMapper::toDto);
    }

    public Page<RendezVousDto> getByDate(LocalDateTime dateRendezVous, Pageable pageable) {
        Page<RendezVous> rendezVous = rendezVousRepository.findByDateRendezVous(dateRendezVous, pageable);
        return rendezVous.map(rendezVousMapper::toDto);
    }}
