package com.finalyear.VolunteeringSystm.security.config;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {
    private static final String[] WHITE_LIST_URL = {"/api/auth/**","/api/auth/authenticate",
            "/api/hospitals/**",
            "/api/departments/**",
            //LIST OF AUTHORIZED SWAGGER URLs
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html"
            };
    private static final String[] LIST_FOR_LOGGED_IN_URL = {
        "/api/v2/order/submit_order",
        "/api/v2/reviews/submitReview",
        "/api/v2/users/{userId}",
        "/api/v2/products/ordered",
    };
    private static final String[] ADMIN_SELLER_LIST_URL = {

    };
    private static final String[] ADMIN_LIST_URL = {


    };
//    private static final String[]
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            // Customize the error response
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Access Denied: " + accessDeniedException.getMessage());
        };
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AccessDeniedHandler accessDeniedHandler) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeHttpRequests(req -> req
                        .requestMatchers(WHITE_LIST_URL)
//                .requestMatchers("/api/v1/auth/**")
                        .permitAll()
//                        .requestMatchers(LIST_FOR_LOGGED_IN_URL).hasAnyRole(ADMIN.name(), SELLER.name(), BUYER.name())
//                        .requestMatchers(ADMIN_SELLER_LIST_URL).hasAnyRole(ADMIN.name(), SELLER.name())
//                        .requestMatchers(ADMIN_LIST_URL).hasRole(ADMIN.name())
                        .anyRequest()
                        .authenticated())
                .exceptionHandling(exceptions -> exceptions
                .accessDeniedHandler(accessDeniedHandler)
        )

                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))

                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout.logoutUrl("/api/auth/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                );
        return http.build();
    }

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // Adjust the frontend URL
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
