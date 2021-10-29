package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepo extends JpaRepository <Usuario, String>{

    List<Usuario> findAllByNombreContains(String nombre);
    Optional<Usuario> findAllByEmail(String email);
    Page<Usuario> findAll(Pageable paginador);

    @Query("select p from Usuario u, IN (u.favoritos) p where u.email = :email")
    List<Producto> obtenerProductoFavoritos(String email);

    @Query("select u.email, p from Usuario u left join u.productos p")
    List<Object[]> listarUsuariosyProductos();




}
