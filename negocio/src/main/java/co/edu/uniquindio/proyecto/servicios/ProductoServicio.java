package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import java.util.List;

public interface ProductoServicio {

    Producto publicarProducto(Producto p) throws Exception;

    void actualizarProducto(Producto p) throws Exception;

    void eliminarProducto(String codigo) throws Exception;

    Producto obtenerProducto(String codigo) throws Exception;

    List<Producto> listarProductos(Categoria categoria);

    void comentarProducto(String mensaje, double calificacion, Usuario u, Producto p)throws  Exception;

    void guardarProductoFav(Producto p, Usuario u) throws Exception;

    void eliminarProductoFav(Producto p, Usuario u) throws Exception;

    void comprarProducto(Compra compra) throws Exception;

    //void recuperarContraseña();

    List<Producto> buscarProducto(String nombreProducto, String[] filtros);

    List<Producto> listarProducto(String codigoUsuario) throws Exception;
}
