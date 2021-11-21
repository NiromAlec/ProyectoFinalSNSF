package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.dto.ProductoValido;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductoRepo extends JpaRepository<Producto, String> {

        Page<Producto> findAll(Pageable paginador);
        Optional<Producto> findByCodigo(String codigo);

        @Query("select p.usuario.nombre from Producto p where p.codigo= :id")
        String ObtenerNombreVendedor(String id);

        //PERSPECTIVA PRODUCTO OBTENER COMENTARIO
        @Query("select c from Producto p join p.comentarios c where p.codigo= :id")
        List<Comentario> ObtenerComentario(String id);

        //PERSPECTIVA COMENTARIO
        @Query("select c from Comentario c where c.producto.codigo= :id")
        List<Comentario> ObtenerComentario2(String id);

        @Query("select p.nombre, c from Producto p left join p.comentarios c ")
        List<Object[]> listarProductosYComentario();

        @Query("select distinct c.usuario from Producto p join p.comentarios c where p.codigo= :id ")
        List<Usuario> listarUsuariosComentarios(String id);

        //MEDIANTE UN ARREGLO
        @Query("select p.nombre, p.descripcion, p.precio, p.ciudad.nombre from Producto p where :fechaActual < p.fechaLimite ")
        List<Object[]> listarProductosValidos(LocalDateTime fechaActual);
        //MEDIANTE DTO
        @Query("select new co.edu.uniquindio.proyecto.dto.ProductoValido(p.nombre, p.descripcion, p.precio, p.ciudad) from Producto p where :fechaActual < p.fechaLimite ")
        List<ProductoValido> listarProductosValidos2(LocalDateTime fechaActual);


}
