package com.restaurante.ecommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private ImplementsUserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/inicio").permitAll()
		
		.antMatchers("/loginOuCadastro").permitAll()
		.antMatchers("/cadastrarCliente").permitAll()
		
		.antMatchers("/pedido/**").hasRole("CLIENTE")
		
		.antMatchers("/cadastroPrato").hasRole("ADMIN")
		.antMatchers("/cadastrarPrato").hasRole("ADMIN")
		
		.anyRequest().authenticated()
		
		.and().formLogin().loginPage("/loginOuCadastro").defaultSuccessUrl("/inicio").permitAll()
		.permitAll()
		
		.and().logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/inicio").permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/js/**", "/css/**",  "/imgs/**", "/imagens/pratos/**");
	}
}
