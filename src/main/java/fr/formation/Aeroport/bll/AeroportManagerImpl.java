package fr.formation.Aeroport.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fr.formation.Aeroport.bo.Avion;
import fr.formation.Aeroport.bo.Passager;
import fr.formation.Aeroport.dal.AvionDAO;
import fr.formation.Aeroport.dal.PassagerDAO;
import jakarta.transaction.Transactional;

@Service
public class AeroportManagerImpl implements AeroportManager {
	@Autowired
	AvionDAO avionDAO;

	@Autowired
	PassagerDAO passagerDAO;

	@Override
	@Transactional
	public void embarquer(Integer idAvion, Integer idPassager) {
		Passager passager = passagerDAO.findById(idPassager).get();
		Avion avion = avionDAO.findById(idAvion).get();
		avion.addPassager(passager);
		avionDAO.save(avion);
	}

	@Override
	@Transactional
	public void debarquement(Integer idAvion) {
		Avion avion = avionDAO.findById(idAvion).get();
		avion.removeAllPassager();
		avionDAO.save(avion);
	}

	@Override
	@Transactional
	public void addAvion(Avion avion) {
		avionDAO.save(avion);
	}

	@Override
	@Transactional
	public void addPassager(Passager p1) {
		passagerDAO.save(p1);
	}

	@Override
	@Transactional
	public void atterissage(Avion avion) {
		// sauvegarde pour avoir un nouvel id
		avionDAO.save(avion);

		// parcours des passagers
		for (Passager passager : avion.getLstPassagers()) {
			passager.setAvion(avion);
			passagerDAO.save(passager);
		}
	}

	@Override
	@Transactional
	public void decolage(Integer idAvion, String aeroport) {
		Avion avion = avionDAO.findById(idAvion).orElse(null);
		// suppression des id
//		avion.setIdAvion(null);
		for (Passager passager : avion.getLstPassagers()) {
			passager.setIdPassager(null);
		}

		String url = "http://" + aeroport + "/ws/avion";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForEntity(url, avion, Avion.class);

		avionDAO.delete(avion);
	}

	@Override
	public List<Avion> getAllAvions() {
		return (List<Avion>) avionDAO.findAll();
	}

	@Override
	public List<Passager> getAllPassagers() {
		return (List<Passager>) passagerDAO.findAll();
	}

}
