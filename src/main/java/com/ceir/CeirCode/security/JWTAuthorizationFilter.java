package com.ceir.CeirCode.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ceir.CeirCode.repo.TokenRepository;
import com.ceir.CeirCode.service.JwtServiceImpl;
import com.ceir.CeirCode.service.UserTypeUrlMappingServiceImpl;

import io.jsonwebtoken.Claims;


@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {
	private static final Logger logger = LogManager.getLogger(JWTAuthorizationFilter.class);
	
	@Autowired
	JwtServiceImpl jwtService;
	
	@Autowired
	TokenRepository tokenRepository;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	UserTypeUrlMappingServiceImpl urlMappingService;

	@Override
	protected void doFilterInternal(
			@NonNull HttpServletRequest request,
			@NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain
			) throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");
		final String url = request.getRequestURI();
		logger.info("Authorization header:"+authHeader+" and requested url:["+url+"]");
	    final String jwt;
	    final String userName;
	    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
	    	filterChain.doFilter(request, response);
	    	return;
	    }
	    jwt = authHeader.substring(7);
	    if (jwtService.extractClaim(jwt, Claims::getId) == null ) {
	    	filterChain.doFilter(request, response);
	    	return;
	    }
	    userName = jwtService.extractUsername(jwt);
	    if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null
	    		&& urlMappingService.isRequestedUrlAllowed(userName, url)) {
	    	UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
	    	Boolean isTokenValid = tokenRepository.findByToken(jwt)
	          .map(t -> !t.isExpired() && !t.isRevoked())
	          .orElse(false);
	      if (isTokenValid && jwtService.isTokenValid(jwt, userDetails)) {
	    	  UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
	    			  userDetails,
	    			  null,
	    			  userDetails.getAuthorities());
	        authToken.setDetails(
	            new WebAuthenticationDetailsSource().buildDetails(request)
	        );
	        SecurityContextHolder.getContext().setAuthentication(authToken);
	      }else {
		    	filterChain.doFilter(request, response);
		    	return;
		  }
	    }else {
	    	filterChain.doFilter(request, response);
	    	return;
	    }
	    filterChain.doFilter(request, response);
	  }

}