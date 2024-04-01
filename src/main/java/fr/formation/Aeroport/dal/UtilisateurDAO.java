package fr.formation.Aeroport.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import fr.formation.Aeroport.bo.Utilisateur;

public interface UtilisateurDAO extends JpaRepository<Utilisateur, String> {
	//Rechercher un utilisateur par son pseudo
	Utilisateur findByPseudo(@Param("pseudo") String pseudo);

	//Rechercher un utilisateur par son pseudo et password
	Utilisateur findByPseudoAndPassword(@Param("pseudo") String pseudo, @Param("password") String password);
}
