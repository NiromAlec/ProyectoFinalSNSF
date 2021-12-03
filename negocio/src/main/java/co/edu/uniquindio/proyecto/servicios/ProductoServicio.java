package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductoServicio {

    Producto publicarProducto(Producto p) throws Exception;
    void actualizarProducto(Producto p) throws Exception;
    void eliminarProducto(String codigo) throws Exception;
    Producto obtenerProducto(String codigo) throws Exception;
    List<Producto> listarProductosCategoria(Integer codigo);
    List<Producto> listarTodosLosProductos();
    void comentarProducto(Comentario comentario) throws Exception;
    void guardarProductoFavorito(Producto producto, Usuario usuario) throws Exception;
    void eliminarProductoFavorito(Producto producto, Usuario usuario) throws Exception;
    //void comprarProductos(Compra compra) throws Exception;
    void calificarProducto(double calificacion);
    List<Producto> buscarProductos(String nombreProducto, String[] filtros);
    List<Producto> listarProductoUsuario(String codigoUsuario) throws Exception;
}
