package co.edu.uniquindio.proyecto.servicios;


import co.edu.uniquindio.proyecto.dto.ProductoValido;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicioImpl implements ProductoServicio {

    private final ProductoRepo productoRepo;
    private final ComentarioRepo comentarioRepo;

    public ProductoServicioImpl(ProductoRepo productoRepo, ComentarioRepo comentarioRepo) {
        this.productoRepo = productoRepo;
        this.comentarioRepo = comentarioRepo;
    }

    @Override
    public Producto publicarProducto(Producto p) throws Exception {
        try {
            return productoRepo.save(p);
        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }

    @Override
    public void actualizarProducto(Producto p) throws Exception {

    }

    @Override
    public void eliminarProducto(String codigo) throws Exception {

        Optional<Producto> producto= productoRepo.findByCodigo(codigo);

        if(producto.isEmpty()){
            throw new Exception("El codigo del producto no existe");
        }

        productoRepo.deleteById(codigo);

    }

    @Override
    public Producto obtenerProducto(String codigo) throws Exception {
        return productoRepo.findById(codigo).orElseThrow(()-> new Exception("el codigo del producto no es valido"));
    }

    @Override
    public List<ProductoValido> listarProductosPorCategoria(Integer codigo, LocalDateTime  fecha) {

        List<ProductoValido> productosV= productoRepo.listarProductosDisponibles(codigo, fecha);
        return productosV;
        //productos.forEach(System.out::println);
    }

    @Override
    public List<Producto> listarProductos(Categoria categoria) {
        return null;
    }

    @Override
    public void comentarProducto(Comentario c) throws Exception {

        try {
            comentarioRepo.save(c);
        }catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void guardarProductoFav(Producto p, Usuario u) throws Exception {

        try {
            List<Producto> lista= u.getFavoritos();

            if(lista != null) {
                for (Producto pl : lista) {
                    if (pl.getCodigo().equals(p.getCodigo())) {
                        throw new Exception("Este producto ya ha sido anadido a la lista de favoritos");
                    }
                }
            }else {
               u.getFavoritos().add(p);
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }


    }

    @Override
    public void eliminarProductoFav(Producto p, Usuario u) throws Exception {
        try {
            u.getFavoritos().add(p);
            List<Producto> lista= u.getFavoritos();
            for(Producto pl : lista){
                if(pl.getCodigo().equals(p.getCodigo())){
                    u.getFavoritos().remove(p);
                }
            }

        }catch (Exception e){

            throw new Exception("El producto no existe en Favoritos");
        }

    }

    @Override
    public void comprarProducto(Compra compra) throws Exception {

    }

    @Override
    public List<Producto> buscarProducto(String nombreProducto, String[] filtros) {
        return null;
    }

    @Override
    public List<Producto> listarProducto(String codigoUsuario) throws Exception {
        return null;
    }
}
