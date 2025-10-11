package co.edu.unbosque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.model.Notificacion;

/**
 * Repositorio JPA para la entidad Notificacion.
 */
@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
}