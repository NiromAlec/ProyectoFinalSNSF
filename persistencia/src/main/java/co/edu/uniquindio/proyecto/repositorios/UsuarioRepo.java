package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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

    //PERSPECTIVA DE CHAT
    @Query("select ch from Chat ch where ch.producto.usuario.codigo= :id")
    List<Chat> ObtenerChatUsuario(String id);
    //PUNTO 1, CONTAR SUBASTAS POR CATEGORIA DE PRODUCTOS
    @Query("select  c.codigo,count(c) from Producto p,IN (p.categorias) c, IN (p.subastas) su group by c.codigo")
    List<Object[]> ObtenerSubastaXCategoria();
/*
    @Query("select cat.codigo, count (cat) from Subasta su, IN(su.producto.categorias) cat group by  cat.codigo")
    List<Object[]> ObtenerSubastaXCategoria2();
*/
    //PUNTO 5 OBTENER SUBASTAS DADA UNA CATEGORIA DETERMINADA
    @Query("select s from Subasta s,IN(s.producto.categorias) c where c.codigo = :id ")
    List<Subasta> ObtenerSubasta(Integer id);
    //PUNTO 3 OBTENER CANTIDAD DE COMPRAS POR MEDIO DE PAGO
    @Query("select c.medioPago,count(c) from Compra c group by c.medioPago")
    List<Object[]> ObtenerCantidadMedioPago();
}
