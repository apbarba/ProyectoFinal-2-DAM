package com.salesianostriana.dam.imagineria_web.security.jwt.access;

import com.salesianostriana.dam.imagineria_web.model.User;
import com.salesianostriana.dam.imagineria_web.security.errorHandling.JwtTokenException;
import com.salesianostriana.dam.imagineria_web.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Log
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserService imaginerosService;
    private final JwtProvider jwtProvider;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = getJwtTokenFromRequest(request);

        try{

            if (StringUtils.hasText(token) && jwtProvider.validateToken(token)){

                UUID imagineroId = jwtProvider.getImagineroIdFromJwtToken(token);

                Optional<User> result = imaginerosService.findById(imagineroId);

                if (result.isPresent()){

                    User imaginero = result.get();

                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            imaginero,
                            null,
                            imaginero.getAuthorities()
                    );

                    authenticationToken.setDetails(new WebAuthenticationDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }

            filterChain.doFilter(request, response);
        }catch (JwtTokenException exception){

            log.info("La autorización ha sido errónea por el token: " + exception.getMessage());;

            resolver.resolveException(request, response, null, exception);
        }
    }

    private String getJwtTokenFromRequest(HttpServletRequest request){

        String bearerToken = request.getHeader(JwtProvider.TOKEN_HEADER);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(JwtProvider.TOKEN_PREFIX)){

            return bearerToken.substring(JwtProvider.TOKEN_PREFIX.length());
        }

        return null;
    }
}
