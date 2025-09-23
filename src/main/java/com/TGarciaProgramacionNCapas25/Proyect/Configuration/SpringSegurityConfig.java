package com.TGarciaProgramacionNCapas25.Proyect.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class SpringSegurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/usuario/cargamasiva").hasRole("ADMIN")
                .requestMatchers("/Login", "/usuario/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()
        )
                .formLogin(form -> form
                .loginPage("/usuario/Login")
                .loginProcessingUrl("/usuario/Login")
                .defaultSuccessUrl("/usuario", true)
                .failureUrl("/usuario/Login")
                .permitAll()
                )
                .logout(logout -> logout
                .logoutUrl("/usuario/logout")
                .logoutSuccessUrl("/usuario/Login")
                .permitAll()
                );

        return http.build();
    }
        @Bean
        public InMemoryUserDetailsManager inMemoryUserDetailsManager
        
            () {
        UserDetails user = User.builder()
                    .username("user")
                    .password("{noop}test123")
                    .roles("USER")
                    .build();

            UserDetails admin = User.builder()
                    .username("admin")
                    .password("{noop}test123")
                    .roles("ADMIN")
                    .build();

            return new InMemoryUserDetailsManager(user, admin);
        }

    }
