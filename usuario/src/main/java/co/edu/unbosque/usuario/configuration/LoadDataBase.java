package co.edu.unbosque.usuario.configuration;

import java.time.LocalDate;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import co.edu.unbosque.usuario.model.Usuario;
import co.edu.unbosque.usuario.repository.UsuarioRepository;

@Configuration
public class LoadDataBase {

	private static final Logger log = LoggerFactory.getLogger(LoadDataBase.class);

	private PasswordEncoder cifrador;

	public LoadDataBase(PasswordEncoder cifrador) {
		this.cifrador = cifrador;
	}

	@Bean
	CommandLineRunner initDatabase(UsuarioRepository adminRepo) {
		return args -> {
			Optional<Usuario> found = adminRepo.findByNombreCompleto("Admin");

			String contrasenaCifrada = cifrador.encode("admin123");

			if (found.isPresent()) {
				log.info("Admin ya existe, omitiendo creación del usuario admin...");
			} else {
				adminRepo.save(new Usuario("Admin", "CC", "1000000000", "acguerrerom@unbosque.edu.co", "30000000000",
						contrasenaCifrada, "activo", LocalDate.now(), "Admin"));
				log.info("Usuario admin creado correctamente.");
			}
		};
	}
}
