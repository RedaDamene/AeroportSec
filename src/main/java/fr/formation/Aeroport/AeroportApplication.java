package fr.formation.Aeroport;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.formation.Aeroport.bll.AeroportManager;
import fr.formation.Aeroport.bo.Avion;
import fr.formation.Aeroport.bo.Passager;
import fr.formation.Aeroport.bo.Utilisateur;
import fr.formation.Aeroport.dal.UtilisateurDAO;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class AeroportApplication {
	@Autowired
	AeroportManager manager;

	@Autowired
	UtilisateurDAO userDao;

	@PostConstruct
	public void init() {
		userDao.save(new Utilisateur("manu", "manu", "Malabry","Emmanuel","USER"));
		userDao.save(new Utilisateur("admin", "admin", "Malabry","Emmanuel","ADMIN"));
		
		Avion a1 = new Avion("A1","Boing","747");
		Avion a2 = new Avion("A2","Boing","658");


		manager.addAvion(a1);
		manager.addAvion(a2);
	
		Passager p1 = new Passager("Cérien","Jean",20);
		Passager p2 = new Passager("Terrieur","Alain",20);
		Passager p3 = new Passager("Dupont","Vincent",50);
		manager.addPassager(p1);
		manager.addPassager(p2);
		manager.addPassager(p3);
		
		manager.embarquer(a1.getIdAvion(), p1.getIdPassager());
		manager.embarquer(a1.getIdAvion(), p2.getIdPassager());
		manager.embarquer(a2.getIdAvion(), p3.getIdPassager());
	}
	
	public static void main(String[] args) {
		SpringApplication.run(AeroportApplication.class, args);
	}

}
