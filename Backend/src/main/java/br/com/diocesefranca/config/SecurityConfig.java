package br.com.diocesefranca.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    // 🔐 Bean global para criptografia de senhas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ⚙️ Configuração de segurança HTTP básica
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Desativa proteção CSRF (útil para APIs REST)
                .csrf(csrf -> csrf.disable())
                // Permite todos os endpoints sem autenticação
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**").permitAll()
                        .anyRequest().permitAll()
                )
                // Desativa login por formulário
                .formLogin(login -> login.disable())
                // Desativa autenticação HTTP básica
                .httpBasic(basic -> basic.disable());

        return http.build();
    }
}
