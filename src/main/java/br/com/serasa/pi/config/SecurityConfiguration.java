package br.com.serasa.pi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import br.com.serasa.pi.exceptions.RestAccessDeniedHandler;
import br.com.serasa.pi.security.jwt.JwtConfigurer;
import br.com.serasa.pi.security.jwt.JwtProvider;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfiguration {
	
	@Autowired
	private JwtProvider jwtProvider;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {

		return http.csrf().disable()
        .httpBasic().disable()
        .cors()
        .and()
        .authorizeHttpRequests()
        .antMatchers("/auth/signin", "/api-docs/**", "/swagger-ui.html").permitAll()
        .antMatchers("/api/user/**").authenticated().antMatchers("/api/usuario").hasRole("ADMIN")
        .and()
        .apply(new JwtConfigurer(jwtProvider)).and()
        .exceptionHandling()
        .accessDeniedHandler(accessDeniedHandler())
        .authenticationEntryPoint(authenticationEntryPoint())
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().build();
	}

	@Bean
	public RestAccessDeniedHandler accessDeniedHandler() {
		return new RestAccessDeniedHandler();
	}
	
	@Bean
	public RestAuthenticationEntryPoint authenticationEntryPoint() {
		return new RestAuthenticationEntryPoint();
	}
}
