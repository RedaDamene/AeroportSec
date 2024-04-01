package fr.formation.Aeroport.Inspector;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.formation.Aeroport.bo.Passager;
import fr.formation.Aeroport.dal.PassagerDAO;

@Aspect
@Component
public class DupontInspector {
	@Autowired
	PassagerDAO dao;
	
	@Pointcut("execution(* fr.formation.Aeroport.bll.AeroportManagerImpl.embarquer(..))")
	public void methodesServeillees() {
	} 
	
	@Before("methodesServeillees()")
	public void debug(JoinPoint jp) {
		Integer idPassager = (Integer)jp.getArgs()[1];
		Passager p = dao.findById(idPassager).get();
		if("Dupont".equals(p.getNom())) {
			System.out.println(">>>>>>>>>>>>ALERTE YA UN DUPONT SUR LE VOL !!!!!!!!!!!!!!!!!!!!!!");
		}
	} 
}
