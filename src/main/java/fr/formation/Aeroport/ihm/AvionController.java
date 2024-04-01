package fr.formation.Aeroport.ihm;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.formation.Aeroport.bll.AeroportManager;
import fr.formation.Aeroport.bo.Avion;
import fr.formation.Aeroport.bo.Passager;

@Controller
@RequestMapping({"/","/avions"})
public class AvionController {
	@Autowired
	AeroportManager manager;

	@ModelAttribute("lstAeroports")
	List<String> getAeroports() {
		return Arrays.asList("localhost:8081", "localhost:8080");

	}

	@ModelAttribute("lstAvions")
	List<Avion> getAvions() {
		return manager.getAllAvions();
	}

	@ModelAttribute("lstPassagers")
	List<Passager> getPassagers() {
		return manager.getAllPassagers();
	}

	@GetMapping
	public String index() {
		return "index";
	}

	@GetMapping("/debarquement/{id}")
	public String debarque(@PathVariable("id") Integer id) {
		manager.debarquement(id);
		return "redirect:/avions";
	}

	@GetMapping("/embarquement/{idAvion}/{idPassager}")
	public String embarque(@PathVariable("idAvion") Integer idAvion, @PathVariable("idPassager") Integer idPassager) {
		manager.embarquer(idAvion, idPassager);
		return "redirect:/avions";
	}
	
	@GetMapping("/decolage/{aero}/{idAvion}")
	public String decolage(@PathVariable("aero") String aero, @PathVariable("idAvion") Integer idAvion) {
		manager.decolage(idAvion, aero);		
		return "redirect:/avions";
	}
}
