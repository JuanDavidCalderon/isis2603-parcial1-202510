package co.edu.uniandes.dse.parcialprueba.services;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import jakarta.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;


import co.edu.uniandes.dse.parcialprueba.services.PacienteService;
import co.edu.uniandes.dse.parcialprueba.entities.PacienteEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * Pruebas de logica de Editorials
 *
 * @author ISIS2603
 */
@DataJpaTest
@Transactional
@Import(PacienteService.class)
public class PacienteServiceTEst {

	@Autowired
	private PacienteService pacienteService;

	@Autowired
	private TestEntityManager entityManager;

	private PodamFactory factory = new PodamFactoryImpl();

	private List<PacienteEntity> pacienteList = new ArrayList<>();

	/**
	 * Configuración inicial de la prueba.
	 */
	@BeforeEach
	void setUp() {
		clearData();
		insertData();
	}

	/**
	 * Limpia las tablas que están implicadas en la prueba.
	 */
	private void clearData() {
		entityManager.getEntityManager().createQuery("delete from PacienteEntity");
		
	}

	/**
	 * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
	 */
	private void insertData() {

		for (int i = 0; i < 3; i++) {
			PacienteEntity pacienteEntity = factory.manufacturePojo(PacienteEntity.class);
			entityManager.persist(pacienteEntity);
			pacienteList.add(pacienteEntity);
		}


	}

    @Test
	void testCreatePaciente() throws IllegalOperationException {
		PacienteEntity nuevoPaciente = factory.manufacturePojo(PacienteEntity.class);
		PacienteEntity resultadoPaciente = pacienteService.crearPaciente(nuevoPaciente);
		assertNotNull(resultadoPaciente);

		PacienteEntity pacienteEntity = entityManager.find(PacienteEntity.class, resultadoPaciente.getId());
		assertEquals(nuevoPaciente.getId(), pacienteEntity.getId());
		assertEquals(nuevoPaciente.getNombre(), pacienteEntity.getNombre());
        assertEquals(nuevoPaciente.getCoreo(), pacienteEntity.getCoreo());
        assertEquals(nuevoPaciente.getTelefono(), pacienteEntity.getTelefono());



}
}
