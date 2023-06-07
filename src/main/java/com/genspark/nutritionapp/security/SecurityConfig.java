package com.genspark.nutritionapp.security;

import com.genspark.nutritionapp.service.UserInfoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Arrays;

//code was gotten from tutorials in https://www.udemy.com/course/spring-hibernate-tutorial/learn/lecture/22338522#questions
@Configuration
public class SecurityConfig  {


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserInfoService userInfoService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userInfoService); //set the custom user details service
        auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
        return auth;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests((authorize) -> authorize.requestMatchers("/api/users").permitAll());
        http.cors(Customizer.withDefaults()).authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/api/users").hasRole("DEVELOPER")
                        .requestMatchers(HttpMethod.GET, "/api/users/{username}").hasRole("DEVELOPER")
                        .requestMatchers(HttpMethod.DELETE, "/api/users/{username}").hasRole("DEVELOPER")
                        .requestMatchers(HttpMethod.POST, "/api/users").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/dev").hasRole("DEVELOPER")
                        .requestMatchers(HttpMethod.POST, "/api/users/login").permitAll()
        );
        // use HTTP Basic authentication
        http.httpBasic(Customizer.withDefaults());

        // disable Cross Site Request Forgery (CSRF)
        // in general, not required for stateless REST APIs that use POST, PUT, DELETE, and/or PATCH
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

        //https://docs.spring.io/spring-security/reference/reactive/integrations/cors.html
        // and
        // https://reflectoring.io/spring-cors/
        // and
        // https://www.youtube.com/watch?v=HRwlT_etr60
        //were used for help
        @Bean
        CorsConfigurationSource corsConfigurationSource() {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
            configuration.setAllowedMethods(Arrays.asList("GET","POST", "DELETE"));
            configuration.setAllowedHeaders(Arrays.asList("*"));
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", configuration);
            return source;
        }

}
