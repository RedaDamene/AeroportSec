package fr.formation.Aeroport.bll;

import java.util.List;

import fr.formation.Aeroport.bo.Avion;
import fr.formation.Aeroport.bo.Passager;

public interface AeroportManager {
	public void embarquer(Integer idAvion, Integer idPassager);

	public void debarquement(Integer idAvion);

	public void addAvion(Avion avion);

	public void atterissage(Avion avion);

	public void decolage(Integer idAvion, String aeroport);

	public List<Avion> getAllAvions();

	public List<Passager> getAllPassagers();

	public void addPassager(Passager p1);
}
