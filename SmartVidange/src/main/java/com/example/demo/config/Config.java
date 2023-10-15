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

import com.example.demo.service.AgentService;

@Configuration
@EnableWebSecurity
public class Config{
	
	@Autowired
	private AgentService customUserDetails;
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
        .requestMatchers(mvc.pattern("assets/**")).permitAll());
    
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
        .requestMatchers(mvc.pattern("/poubelle/**")).permitAll());
    
    http
    .authorizeHttpRequests((authorize) -> authorize
        .requestMatchers(mvc.pattern("/ongoingtrans")).authenticated());
    
    http
    .authorizeHttpRequests((authorize) -> authorize
        .requestMatchers(mvc.pattern("/done")).authenticated());
    
    http
    .authorizeHttpRequests((authorize) -> authorize
        .requestMatchers(mvc.pattern("/poubelles/**")).authenticated());
    
    http
    .authorizeHttpRequests((authorize) -> authorize
        .requestMatchers(mvc.pattern("/vider/**")).authenticated());
    
    http
    .authorizeHttpRequests((authorize) -> authorize
        .requestMatchers(mvc.pattern("/mail")).authenticated());
    
    http
    .authorizeHttpRequests((authorize) -> authorize
        .requestMatchers(mvc.pattern("/ongoing")).authenticated());
    
    http
    .authorizeHttpRequests((authorize) -> authorize
        .requestMatchers(mvc.pattern("/finished")).authenticated());
    
    http
    .authorizeHttpRequests((authorize) -> authorize
        .requestMatchers(mvc.pattern("/contact")).authenticated());
    
    http
    .authorizeHttpRequests((authorize) -> authorize
        .requestMatchers(mvc.pattern("/finishedtrans")).authenticated());
    
    http
    .authorizeHttpRequests((authorize) -> authorize
        .requestMatchers(mvc.pattern("assets/**")).permitAll());
    
    http
    .authorizeHttpRequests((authorize) -> authorize
        .requestMatchers(mvc.pattern("/agent_transac")).authenticated());
    
    http
    .authorizeHttpRequests((authorize) -> authorize
        .requestMatchers(mvc.pattern("/client")).authenticated());

    http
    .authorizeHttpRequests((authorize) -> authorize
        .requestMatchers(mvc.pattern("/finish")).authenticated());
    
    http
    .authorizeHttpRequests((authorize) -> authorize
        .requestMatchers(mvc.pattern("/choisir")).authenticated());
    
    http
    .authorizeHttpRequests((authorize) -> authorize
        .requestMatchers(mvc.pattern("/trans_client")).authenticated());
    
    http
    .authorizeHttpRequests((authorize) -> authorize
        .requestMatchers(mvc.pattern("/end_user_side")).authenticated());

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