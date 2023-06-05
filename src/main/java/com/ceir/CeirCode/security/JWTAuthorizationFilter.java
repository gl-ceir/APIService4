package com.ceir.CeirCode.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ceir.CeirCode.repo.app.TokenRepository;
import com.ceir.CeirCode.service.JwtServiceImpl;
import com.ceir.CeirCode.service.SysParamServiceImpl;
import com.ceir.CeirCode.service.UserTypeUrlMappingServiceImpl;

import io.jsonwebtoken.Claims;


@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {
	private static final Logger logger = LogManager.getLogger(JWTAuthorizationFilter.class);
	
	@Autowired
	JwtServiceImpl jwtServiceImpl;
	
	@Autowired
	TokenRepository tokenRepository;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	UserTypeUrlMappingServiceImpl urlMappingService;
	
	@Autowired
	SysParamServiceImpl configurationService;

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
	    if (authHeader != null) {
	    	if (!authHeader.startsWith("Bearer ")) {
	    		configurationService.getErrorDetails("BEARER_KEYWORD_MISSING", response);
	    		logger.error("Bearer keyword is missing in the authorozation header.");
		    	return;
	    	}
	    	jwt = authHeader.substring(7);
	    	if (jwtServiceImpl.extractClaim(jwt, Claims::getId) == null ) {
	    		configurationService.getErrorDetails("INVALID_JWT", response);
		    	return;
		    }
	    	userName = jwtServiceImpl.extractUsername(jwt);
		    if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
		    	if(urlMappingService.isRequestedUrlAllowed(userName, url)) {
		    		UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
			    	Boolean isTokenValid = tokenRepository.findByToken(jwt)
			          .map(t -> !t.isExpired() && !t.isRevoked())
			          .orElse(false);
			    	if (isTokenValid && jwtServiceImpl.isTokenValid(jwt, userDetails)) {
				    	  UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
				    			  userDetails,
				    			  null,
				    			  userDetails.getAuthorities());
				        authToken.setDetails(
				            new WebAuthenticationDetailsSource().buildDetails(request)
				        );
				        SecurityContextHolder.getContext().setAuthentication(authToken);
			    	} else {
			    		configurationService.getErrorDetails("TOKEN_REVOKED_EXPIRED", response);
			    		return;
			    	}
		    	} else {
		    		configurationService.getErrorDetails("UNAUTHORIZE_URL_ACCESS", response);
			    	return;
		    	}
		    } else {
		    	configurationService.getErrorDetails("INVALID_TOKEN_USER", response);
		    	return;
		    }
	    }
	    filterChain.doFilter(request, response);
	}

}