package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;


import com.example.demo.service.CustomUserDetails;

@Configuration
@EnableWebSecurity
public class Config{
	
	@Autowired
	private CustomUserDetails customUserDetails;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	LoginSuccessHandler successHandler;
	

@Bean
MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
	    return new MvcRequestMatcher.Builder(introspector);
	}
	
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
    http.csrf().disable();
    http
    .authorizeHttpRequests((authorize) -> authorize
        .requestMatchers(mvc.pattern("/login")).permitAll());
    http
    .authorizeHttpRequests((authorize) -> authorize
        .requestMatchers(mvc.pattern("RuLink/**")).permitAll());
    
    http
            .formLogin()
            .loginPage("/login")
            .successHandler(successHandler)
            .permitAll();

  /*  http.authorizeRequests().requestMatchers(mvc.pattern("/"),"/map","/mapLittoral","/mapAdmin","/mapLittoralChef","/mail","/buy","/mapCentreChef","/Douala","/Yaounde")
    .authenticated();*/
    http
    .authorizeHttpRequests((authorize) -> authorize
        .requestMatchers(mvc.pattern("/")).authenticated());
    
    http
    .authorizeHttpRequests((authorize) -> authorize
        .requestMatchers(mvc.pattern("/map")).authenticated());
    
    http
    .authorizeHttpRequests((authorize) -> authorize
        .requestMatchers(mvc.pattern("/mapLittoral")).authenticated());
    
    http
    .authorizeHttpRequests((authorize) -> authorize
        .requestMatchers(mvc.pattern("/mapAdmin")).authenticated());
    
    http
    .authorizeHttpRequests((authorize) -> authorize
        .requestMatchers(mvc.pattern("/mapLittoralChef")).authenticated());
    
    http
    .authorizeHttpRequests((authorize) -> authorize
        .requestMatchers(mvc.pattern("/mail")).authenticated());
    
    http
    .authorizeHttpRequests((authorize) -> authorize
        .requestMatchers(mvc.pattern("/buy")).authenticated());
    
    http
    .authorizeHttpRequests((authorize) -> authorize
        .requestMatchers(mvc.pattern("/mapCentreChef")).authenticated());
    
    http
    .authorizeHttpRequests((authorize) -> authorize
        .requestMatchers(mvc.pattern("/Douala")).authenticated());
    
    http
    .authorizeHttpRequests((authorize) -> authorize
        .requestMatchers(mvc.pattern("/Yaounde")).authenticated());
    
    http
    .authorizeHttpRequests((authorize) -> authorize
        .requestMatchers(mvc.pattern("/Areas")).authenticated());
    
    http
    .authorizeHttpRequests((authorize) -> authorize
        .requestMatchers(mvc.pattern("/error")).authenticated());

    return http.build();

}



@Bean
public AuthenticationManager authenticationManager(HttpSecurity http)
        throws Exception {
    AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
    authenticationManagerBuilder.userDetailsService(customUserDetails).passwordEncoder(passwordEncoder);
    return authenticationManagerBuilder.build();
}

}