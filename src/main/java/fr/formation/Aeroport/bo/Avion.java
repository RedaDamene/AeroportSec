package fr.formation.Aeroport.bo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@JsonView(AvionsViews.Basic.class)
public class Avion {
	@Id
	@GeneratedValue
	@JsonIgnore
	private Integer idAvion;
	
	private String code;
	private String constructeur;
	private String model;

	@OneToMany(mappedBy = "avion",cascade = CascadeType.ALL)
	@JsonView(AvionsViews.All.class)
	private List<Passager> lstPassagers = new ArrayList<>();
	
	public void addPassager(Passager... passagers) {
		for (Passager passager : passagers) {
			lstPassagers.add(passager);
			passager.setAvion(this);
		}
	}
	
	public void removeAllPassager() {
		for (Passager passager : this.lstPassagers) {
			passager.setAvion(null);
		}	
		lstPassagers.removeAll(lstPassagers);
	}
	
	
	public Avion(String code, String constructeur, String model) {
		super();
		this.code = code;
		this.constructeur = constructeur;
		this.model = model;
	}

}
