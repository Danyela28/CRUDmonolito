package com.TGarciaProgramacionNCapas25.Proyect.Configuration;

import com.TGarciaProgramacionNCapas25.Proyect.Component.CustomAuthenticationFailureHandler;
import com.TGarciaProgramacionNCapas25.Proyect.DAO.UserDetailsJPAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class SpringSegurityConfig {
    
    @Autowired
    private CustomAuthenticationFailureHandler failureHandler;
    
    private final UserDetailsJPAService userDetailsJPAService;
    
    public SpringSegurityConfig(UserDetailsJPAService userDetailsJPAService){
        this.userDetailsJPAService = userDetailsJPAService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/usuario/cargamasiva").hasAnyRole("Administrador", "Editador")
                .requestMatchers("/Login", "/usuario/**").hasAnyRole("Administrador", "Editador", "Estandar", "Lector")
                .anyRequest().authenticated()
        )
                .formLogin(form -> form
                .loginPage("/usuario/Login")
                .loginProcessingUrl("/usuario/Login")
                .defaultSuccessUrl("/usuario", true)
                .failureHandler(failureHandler)
                .permitAll()
                )
                .logout(logout -> logout
                .logoutUrl("/usuario/logout")
                .logoutSuccessUrl("/usuario/Login")
                .permitAll()
                )
                .userDetailsService(userDetailsJPAService);
                

        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
//        @Bean
//        public InMemoryUserDetailsManager inMemoryUserDetailsManager
//        
//            () {
//        UserDetails user = User.builder()
//                    .username("user")
//                    .password("{noop}test123")
//                    .roles("USER")
//                    .build();
//
//            UserDetails admin = User.builder()
//                    .username("admin")
//                    .password("{noop}test123")
//                    .roles("ADMIN")
//                    .build();
//
//            return new InMemoryUserDetailsManager(user, admin);
//        }

    }
