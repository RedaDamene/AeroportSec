package fr.formation.Aeroport.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import fr.formation.Aeroport.dal.UtilisateurDAO;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	UtilisateurDAO dao;
	
	

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		UserDetails userDetails = dao.findByPseudoAndPassword(username,password);
		if (userDetails != null) {
	        Authentication authenticated = new UsernamePasswordAuthenticationToken(
	                userDetails, password, userDetails.getAuthorities());
	        return authenticated;
		} else {
			throw new BadCredentialsException("Incorrect user credentials !!");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
