package co.edu.uniandes.dse.parcialprueba.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.repositories.PacienteRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PacienteService {
    @Autowired
	PacienteRepository pacienteRepository;

    @Transactional
	public PacienteEntity crearPaciente(PacienteEntity pacienteEntity) throws IllegalOperationException {

        if(pacienteEntity.getTelefono().length()!=11){
            throw new IllegalOperationException("El telefono no tiene 11 caracteres");}
        
        if (!(pacienteEntity.getTelefono().substring(0,3)=="311")||!(pacienteEntity.getTelefono().substring(0,3)=="601")){
            throw new IllegalOperationException("El telefono no empieza por 311 o por 601");
        }   

        return pacienteRepository.save(pacienteEntity);

        }

    }


