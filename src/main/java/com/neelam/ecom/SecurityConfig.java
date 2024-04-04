package com.neelam.ecom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.http.HttpServletResponse;


@Configuration
@EnableWebSecurity
public class SecurityConfig {


	@Autowired
	private JwtTokenFilter jwtTokenFilter;
	
	@Autowired
	private CorsConfigure corsFilter;
	
	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
	        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	                .exceptionHandling(exceptions -> exceptions
	                        .authenticationEntryPoint((request, response, ex) -> {
	                            response.setContentType("application/json");
	                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	                            response.getWriter().println("{ \"error\": \"" + ex.getMessage() + "\" }");
	                        })
	                        .accessDeniedPage("/custom-error-page") 
	                        ).csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(registry -> registry
	                        .requestMatchers(HttpMethod.GET,"/products/**").permitAll()
	                        .requestMatchers(HttpMethod.GET,"/product/**").permitAll()
	                        .requestMatchers(HttpMethod.POST,"/signIn").permitAll()
	                        .requestMatchers(HttpMethod.POST,"/registerUser").permitAll()
	                        .requestMatchers(HttpMethod.POST,"/razorpay/**").permitAll()
	                        .anyRequest().authenticated()
	                ).addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class).addFilterBefore(corsFilter, ChannelProcessingFilter.class);
	        ;

	        return httpSecurity.build();
	    }
}