package br.edu.ifpb.pweb2.bitbank.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class BitbankSecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests( auth -> auth
                .requestMatchers("/css/**", "/imagens/**").permitAll()
                .anyRequest().authenticated()
                
            )
            .formLogin( form -> form
                .loginPage("/auth/login")
                .defaultSuccessUrl("/home", true)
                .permitAll()
            )
            .logout( (logout) -> 
                logout.logoutUrl("/auth/logout")
                // logout.logoutSuccessUrl("/auth/login");
                // logout.invalidateHttpSession(true);
                // logout.clearAuthentication(true);
                // logout.deleteCookies("JSESSIONID");
            );
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService() {
        UserDetails user1 = User.withUsername("admin@bitbank.com")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();
        UserDetails user2 = User.withUsername("sagan")
                .password(passwordEncoder().encode("cosmos"))
                .roles("CLIENTE")
               .build();
       JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
       if (!users.userExists(user1.getUsername())) {
            users.createUser(user1);
            users.createUser(user2);
        }
        return users;

       // Para banco em memória
       // return new InMemoryUserDetailsManager(user1, user2); 
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}