package co.edu.uniandes.dse.parcialprueba.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.entities.HistoriaClinicaEntity;
import co.edu.uniandes.dse.parcialprueba.repositories.HistoriaClinicaRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.PacienteRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HistoriaClinicaService {
    @Autowired
	HistoriaClinicaRepository historiaClinicaRepository;

    @Autowired
	PacienteRepository pacienteRepository;

    @Transactional
	public HistoriaClinicaEntity crearHistoriaClinica(HistoriaClinicaEntity historiaClinicaEntity, Long idPaciente)  {

        Optional<PacienteEntity> pacienteEntity = pacienteRepository.findById(idPaciente);
        if(pacienteEntity.get().getAcudiente()!=null){
            historiaClinicaEntity.setDiagnostico("HistoriaCompartida-");
        }

        
		
        historiaClinicaEntity.setPaciente(pacienteEntity.get());

        return historiaClinicaRepository.save(historiaClinicaEntity);

	
	}

}
