package br.com.leonardolima.gestao_vagas.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration // spring vai ser responsavel por configurar
public class SecurityConfig {

    @Autowired
    private SecurityFilter securityFilter;

    private static final String[] SWAGGER_LIST = {
        "/swagger-ui/**",
        "/v3/api-docs/**",
        "/swagger-ressources/**"
    };
    
    @Bean // esse metodo existente na sua camada original eu quero que vc sobreescreva com isso que eu coloquei aqui dentro
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
        
        .authorizeRequests(auth -> {
            auth.requestMatchers("/candidate/").permitAll()
            .requestMatchers("/company/").permitAll()
            .requestMatchers("/auth/company").permitAll()
            .requestMatchers("/candidate/auth").permitAll()
            .requestMatchers(SWAGGER_LIST).permitAll();
            auth.anyRequest().authenticated();
        })
            
        .addFilterBefore(securityFilter, BasicAuthenticationFilter.class);
        
        ;
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
