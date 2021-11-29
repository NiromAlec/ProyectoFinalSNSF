package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicioImpl implements ProductoServicio{

    private final ProductoRepo productoRepo;

    public ProductoServicioImpl(ProductoRepo produtoRepo) {
        this.productoRepo = produtoRepo;
    }

    @Override
    public Producto publicarProducto(Producto p) throws Exception {
        try{
            return productoRepo.save(p);
        }catch(Exception e){
            throw new Exception((e.getMessage()));
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
        return productoRepo.findById(codigo).orElseThrow(()-> new Exception("El codigo del prpducto no es valido"));
    }

    @Override
    public List<Producto> listarProductos(Categoria categoria) {
        return null;
    }

    @Override
    public void comentarProducto(String mensaje, Double calificacion, Usuario usuario, Producto producto) throws Exception {

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
        return null;
    }

    @Override
    public List<Producto> listarProductoUsuario(String codigoUsuario) throws Exception {
        return null;
    }
}
