package fr.formation.Aeroport.dal;

import org.springframework.data.repository.CrudRepository;

import fr.formation.Aeroport.bo.Avion;

public interface AvionDAO extends CrudRepository<Avion, Integer>{

}
