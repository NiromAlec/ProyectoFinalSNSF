package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicioImpl implements ProductoServicio{

    private final ProductoRepo productoRepo;
    private final ComentarioRepo comentarioRepo;

    public ProductoServicioImpl(ProductoRepo produtoRepo, ComentarioRepo comentarioRepo) {
        this.productoRepo = produtoRepo;
        this.comentarioRepo = comentarioRepo;
    }

    @Override
    public Producto publicarProducto(Producto p) throws Exception {
        try{
            return productoRepo.save(p);
        }catch(Exception e){
            throw new Exception("Hubo un error publicando el producto");
        }

    }

    @Override
    public void actualizarProducto(Producto p) throws Exception {

    }

    @Override
    public void eliminarProducto(String codigo) throws Exception {
        Optional<Producto> producto= productoRepo.findById(codigo);
        if(producto.isEmpty()){
            throw new Exception("El codigo del producto no existe");
        }
        productoRepo.deleteById(codigo);
    }

    @Override
    public Producto obtenerProducto(String codigo) throws Exception {
        return productoRepo.findById(codigo).orElseThrow(()-> new Exception("El codigo del producto no es valido"));
    }

    @Override
    public List<Producto> listarProductosCategoria(Integer codigo) {
        return productoRepo.listarProductosCategoria(codigo);
    }

    @Override
    public List<Producto> listarTodosLosProductos() {
        return productoRepo.findAll();
    }

    @Override
    public void comentarProducto(Comentario comentario) throws Exception {
        comentario.setFechaComentario(LocalDateTime.now());
        comentarioRepo.save(comentario);
    }

    @Override
    public void guardarProductoFavorito(Producto producto, Usuario usuario) throws Exception {

    }

    @Override
    public void eliminarProductoFavorito(Producto producto, Usuario usuario) throws Exception {

    }

    @Override
    public void calificarProducto(double calificacion) {

    }

    @Override
    public List<Producto> buscarProductos(String nombreProducto, String[] filtros) {

        return productoRepo.buscarProductoNombre(nombreProducto);
    }

    @Override
    public List<Producto> listarProductoUsuario(String codigoUsuario) throws Exception {
        return null;
    }

}
