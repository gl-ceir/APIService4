package com.ceir.CeirCode.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.ceir.CeirCode.security.JWTAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	JWTAuthorizationFilter jwtAuthFilter;
	
	@Autowired
	AuthenticationProvider authenticationProvider;
	
	@Autowired
	LogoutHandler logoutHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
        .authorizeRequests()
        .antMatchers("/login",
        		"/checkUser",
        		"/ipLog",
        		"/forgotPassword",
        		"/changeLanguage",
        		"/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authenticationProvider(authenticationProvider)
        .addFilterAfter(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .authorizeRequests().and()
        .logout()
        .logoutUrl("/logout")
        .addLogoutHandler(logoutHandler)
        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());
	}
}
