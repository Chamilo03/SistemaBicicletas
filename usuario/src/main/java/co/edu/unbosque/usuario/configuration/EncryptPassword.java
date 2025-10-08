package co.edu.unbosque.usuario.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class EncryptPassword {

    @Bean
    public PasswordEncoder cifrarContrasena() {
        return new BCryptPasswordEncoder(10);
    }
}