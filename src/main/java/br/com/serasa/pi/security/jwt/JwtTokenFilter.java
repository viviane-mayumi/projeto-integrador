package br.com.serasa.pi.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.serasa.pi.exceptions.InvalidJwtAuthenticationException;

public class JwtTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JwtProvider jwtProvider;

	public JwtTokenFilter(JwtProvider jwtProvider) {

		this.jwtProvider = jwtProvider;
	}

	// o filtro serve para filtrar se há um token devolvido pelo cabeçalho
	// trabalhado no nosso resolverToken
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
		throws ServletException, IOException {
	
		String token = jwtProvider.resolveToken(request);
		if (token != null && jwtProvider.validateToken(token)) {
			Authentication auth = jwtProvider.getAuthentication(token);
			if (auth != null) {
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		
		filterChain.doFilter(request, response);
	}
}
