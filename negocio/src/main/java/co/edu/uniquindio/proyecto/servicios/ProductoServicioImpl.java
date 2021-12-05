package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import co.edu.uniquindio.proyecto.repositorios.DetalleCompraRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicioImpl implements ProductoServicio{

    private final ProductoRepo productoRepo;
    private final ComentarioRepo comentarioRepo;
    private final CompraRepo compraRepo;
    private final DetalleCompraRepo detalleCompraRepo;

    public ProductoServicioImpl(ProductoRepo produtoRepo, ComentarioRepo comentarioRepo, CompraRepo compraRepo, DetalleCompraRepo detalleCompraRepo) {
        this.productoRepo = produtoRepo;
        this.comentarioRepo = comentarioRepo;
        this.compraRepo = compraRepo;
        this.detalleCompraRepo = detalleCompraRepo;
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
    public List<Producto> listarTodosLosProductosDisponibles() {
        return productoRepo.buscarProductosDisponibles();
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

    @Override
    public Compra comprarProductos(Usuario usuario, ArrayList<ProductoCarrito> productos, MedioPago medioPago) throws Exception {
        try {
            Compra c = new Compra();
            c.setFechaCompra(LocalDateTime.now(ZoneId.of("America/Bogota")));
            c.setUsuario(usuario);
            c.setMedioPago(medioPago);

            Compra compraGuardada = compraRepo.save(c);

            DetalleCompra dc;
            for (ProductoCarrito p : productos) {
                dc = new DetalleCompra();
                dc.setCompra(compraGuardada);
                dc.setPrecioProducto(p.getPrecio());
                dc.setUnidades(p.getUnidades());
                //productoRepo.disminuirUnidades(p.getUnidades(),p.getId());
                Producto pro=obtenerProducto(Integer.parseInt(p.getId()));
                pro.setUnidades(pro.getUnidades()-p.getUnidades());
                productoRepo.save(pro);
               // productoRepo.findById(p.getId());
                dc.setProducto(productoRepo.findById(Integer.parseInt(p.getId())).get());
                //unidadesdisp
                detalleCompraRepo.save(dc);

            }
            return compraGuardada;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Integer calificacionPromedio(Integer codigo) {
        return productoRepo.calificacionPromedio(codigo);
    }

    @Override
    public List<Producto> buscarProductosUsuario(String id) {
        return productoRepo.buscarProductosUsuario(id);
    }

    @Override
    public List<MedioPago> listarMediosPago() {
        return Arrays.asList(MedioPago.values());
    }

    @Override
    public MedioPago obtenerMedioPago(String pos) throws Exception {
        return MedioPago.valueOf(pos);
    }

}
