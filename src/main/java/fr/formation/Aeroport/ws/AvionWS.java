package fr.formation.Aeroport.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import fr.formation.Aeroport.bll.AeroportManager;
import fr.formation.Aeroport.bo.Avion;
import fr.formation.Aeroport.bo.AvionsViews;

@RestController
@RequestMapping("/ws/avion")
public class AvionWS {
	@Autowired
	AeroportManager manager;
	
	@GetMapping
	@JsonView(AvionsViews.Basic.class)
	public ResponseEntity<?> getAll(){
		return ResponseEntity.ok(manager.getAllAvions());
	}
	
	@PostMapping
	@JsonView(AvionsViews.All.class)
	public ResponseEntity<?> addAvion(@RequestBody Avion avion){
		manager.atterissage(avion);
		return ResponseEntity.ok(avion);
	}
}
