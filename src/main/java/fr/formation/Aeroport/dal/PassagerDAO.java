package fr.formation.Aeroport.dal;

import org.springframework.data.repository.CrudRepository;

import fr.formation.Aeroport.bo.Passager;

public interface PassagerDAO extends CrudRepository<Passager, Integer>{

}
