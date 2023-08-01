package ua.com.tarvic.javaspring.security.jwt.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import ua.com.tarvic.javaspring.security.jwt.dao.AppUserDAO;
import ua.com.tarvic.javaspring.security.jwt.models.ResponseError;
import ua.com.tarvic.javaspring.security.jwt.services.JwtService;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private JwtService jwtService;
    private UserDetailsService userDetailsService;
    ///+++++++++++++ JWT PAIR
    private AppUserDAO appUserDAO;
    ///+++++++++++++ JWT PAIR

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        @Nullable HttpServletResponse response,
        @Nullable FilterChain filterChain
    ) throws ServletException, IOException
    {
        try {
            String authHeader = request.getHeader("Authorization");// Bearer dedqwdwdwqdwdqw
            String jwt;
            String userEmail;

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                assert filterChain != null;
                filterChain.doFilter(request, response);
                return;
            }

            jwt = authHeader.substring(7);
            userEmail = jwtService.extractUsername(jwt); // username userDetails obj

            if (userEmail == null) {
                assert response != null;
                response.setContentType(MediaType.TEXT_PLAIN_VALUE);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid token");
                return;
            }

            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
                if (jwtService.isTokenValid(jwt, userDetails)
                    ///+++++++++++++ JWT PAIR
                    && !jwt.equals(appUserDAO.findAppUserByEmail(userEmail).orElseThrow().getRefreshToken())
                    ///+++++++++++++ JWT PAIR
                ) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                } else {
                    assert response != null;
                    response.setContentType(MediaType.TEXT_PLAIN_VALUE);
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Invalid token");
                    return;
                }
            }
        } catch (IOException | ServletException | UsernameNotFoundException e) {
            throw new RuntimeException(e);
        } ///+++++++++++++ JWT PAIR
        catch (ExpiredJwtException e) {
            assert response != null;
            response.setHeader("Error", "Token is DEAD!!!");

//            String sb = "{\n" +
//                    "  \"error\": \"Unauthorized\",\n" +
//                    "  \"message\": \"Token is expired\",\n" +
//                    "  \"path\": \"" + request.getRequestURL() + "\"\n" +
//                    "}";
//
//            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().write(sb);


            response.resetBuffer();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            ResponseError responseError = ResponseError
                    .builder()
                    .code(HttpStatus.UNAUTHORIZED.value())
                    .error(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                    .message("Token is expired")
                    .path(request.getRequestURI())
                    .build();

//            response
//                    .getOutputStream()
//                    .write(new ObjectMapper().writeValueAsBytes(responseError));

            response
                    .getWriter()
                    .write(new ObjectMapper().writeValueAsString(responseError));

            return;
        }
        ///+++++++++++++ JWT PAIR

        //--------------------------------------------------
        assert filterChain != null;
        filterChain.doFilter(request, response);
    }
}
