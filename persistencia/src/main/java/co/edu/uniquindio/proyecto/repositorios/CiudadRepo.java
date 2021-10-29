package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CiudadRepo extends JpaRepository<Ciudad, Integer>
{

    Optional<Ciudad> findByNombre(String ciudad);

    @Query("select u from Ciudad c join c.usuarios u where c.nombre = :nombre ")
    List<Usuario> listarUsuario(String nombre);
}
