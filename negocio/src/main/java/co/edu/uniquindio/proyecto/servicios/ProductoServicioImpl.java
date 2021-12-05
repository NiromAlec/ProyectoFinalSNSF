package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import co.edu.uniquindio.proyecto.repositorios.DetalleCompraRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicioImpl implements ProductoServicio{

    private final ProductoRepo productoRepo;
    private final ComentarioRepo comentarioRepo;
    private final DetalleCompraRepo detalleCompraRepo;
    private final CompraRepo compraRepo;

    public ProductoServicioImpl(ProductoRepo produtoRepo, ComentarioRepo comentarioRepo, DetalleCompraRepo detalleCompraRepo, CompraRepo compraRepo) {
        this.productoRepo = produtoRepo;
        this.comentarioRepo = comentarioRepo;
        this.detalleCompraRepo = detalleCompraRepo;
        this.compraRepo = compraRepo;
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
    public void eliminarProducto(Integer codigo) throws Exception {
        Optional<Producto> producto= productoRepo.findById(codigo);
        if(producto.isEmpty()){
            throw new Exception("El codigo del producto no existe");
        }
        productoRepo.deleteById(codigo);
    }

    @Override
    public Producto obtenerProducto(Integer codigo) throws Exception {
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
        return productoRepo.listaProductosUsuario(codigoUsuario);
    }

    @Override
    public Integer categoriaPromedio(Integer codigo) {
        return productoRepo.calificacionPromedio(codigo);
    }

    @Override
    public Compra comprarProductos(Usuario usuario, ArrayList<ProductoCarrito> productoCarrito, MedioPago medioPago) throws Exception{
        try{
            Compra c= new Compra();
            c.setFechaCompra(LocalDateTime.now());
            c.setUsuario(usuario);
            c.setMedioPago(medioPago);

            Compra compraGuardada= compraRepo.save(c);
            DetalleCompra dc;
            for (ProductoCarrito p:productoCarrito) {
                dc=new DetalleCompra();
                dc.setCompra(compraGuardada);
                dc.setPrecioProducto(p.getPrecio());
                dc.setUnidades(p.getUnidades());
                dc.setProducto(productoRepo.findById(Integer.parseInt(p.getId())).get());
                detalleCompraRepo.save(dc);
            }

            return compraGuardada;
        } catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
