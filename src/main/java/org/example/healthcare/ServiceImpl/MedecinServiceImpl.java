package org.example.healthcare.ServiceImpl;


import lombok.RequiredArgsConstructor;
import org.example.healthcare.Dto.MedecinDto;
import org.example.healthcare.Entity.Medecin;
import org.example.healthcare.Enum.Role;
import org.example.healthcare.Mapper.MedecinMapper;
import org.example.healthcare.Repository.MedecinRepository;
import org.example.healthcare.Service.MedecinService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MedecinServiceImpl  implements MedecinService {

    private final MedecinRepository repository;
    private final MedecinMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public MedecinDto saveMedecin(MedecinDto dto){
        Medecin medecin=mapper.toEntity(dto);
        medecin.setRole(Role.MEDECIN);
        medecin.setPassword(passwordEncoder.encode(dto.getPassword()));
        return mapper.toDto(repository.save(medecin));
    }

    @Override
    public void DeleteMedecinById(Long id){
        Medecin medecin=repository.findById(id)
                .orElseThrow(()->new RuntimeException("Aucun medecin !"));
        repository.delete(medecin);
    }

    @Override
    public Page<MedecinDto> getAllMedecins(Pageable pageable){
        return repository.findAll(pageable).map(mapper::toDto);
    }

    @Override
    public MedecinDto updateMedecinById(Long id,MedecinDto dto){
        Medecin medecin=repository.findById(id)
                .orElseThrow(()->new RuntimeException("Aucun medecin  !"));
        mapper.updateMedecinDto(dto,medecin);
        return mapper.toDto(repository.save(medecin));
    }

    @Override
    public Page<MedecinDto>getMedecinBySpecialite(String specialite, Pageable pageable){
        Page<Medecin>medecins=repository.findBySpecialite(specialite, pageable);
        return medecins.map(mapper::toDto);
    }
}
