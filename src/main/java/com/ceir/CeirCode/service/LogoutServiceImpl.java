package com.ceir.CeirCode.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import com.ceir.CeirCode.model.Token;
import com.ceir.CeirCode.repo.TokenRepository;

@Service
public class LogoutServiceImpl implements LogoutHandler {
	
	@Autowired
	TokenRepository tokenRepository;
	
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
	    final String authHeader = request.getHeader("Authorization");
	    final String jwt;
	    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
	      return;
	    }
	    jwt = authHeader.substring(7);
	    Token storedToken = tokenRepository.findByToken(jwt)
	        .orElse(null);
	    if (storedToken != null) {
	      storedToken.setExpired(true);
	      storedToken.setRevoked(true);
	      tokenRepository.save(storedToken);
	      SecurityContextHolder.clearContext();
	    }
	}
}
