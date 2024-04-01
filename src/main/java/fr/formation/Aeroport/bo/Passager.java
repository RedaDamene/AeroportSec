package fr.formation.Aeroport.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@JsonView(AvionsViews.All.class)
public class Passager {
	@Id
	@GeneratedValue
	@JsonIgnore
	private Integer idPassager;
	private String nom;
	private String prenom;
	private Integer age;
	
	@ManyToOne
	@ToString.Exclude
	@JsonIgnore
	Avion avion;
	
	public Passager(String nom, String prenom, Integer age) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
	}
	
	
	
}
