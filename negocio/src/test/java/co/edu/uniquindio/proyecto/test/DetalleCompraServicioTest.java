package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.CompraServicio;
import co.edu.uniquindio.proyecto.servicios.DetalleCompraServicio;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class DetalleCompraServicioTest {

    @Autowired
    public DetalleCompraServicio detalleCompraServicio;
    @Autowired
    public CompraServicio compraServicio;
    @Autowired
    public ProductoServicio productoServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Test
    public void anadirDetalleAlCarrito(){

        try{
            //CREA LA COMPRA
            Usuario u= new Usuario ("741", "Thomas", "tomas1@email", "tom0201", null, null);
            Usuario respuesta = usuarioServicio.registrarUsario(u);
            Compra c= new Compra(LocalDateTime.now(), MedioPago.EFECTIVO, u);
            Compra respuestaC= compraServicio.comprarProductos(c);
            //
            //CREA EL PRODUCTO
            Usuario vendedor= usuarioServicio.obtenerUnUsuario("741");
            LocalDateTime ldt= LocalDateTime.of(2023, 11, 30, 20, 10);
            Producto producto= new Producto("1", "Televisor LG 55 pulgadadas", 2, "bla bla bla", 250000.55, ldt, 0.2, vendedor, null);
            Producto publicado=productoServicio.publicarProducto(producto);
            //
            DetalleCompra dc= new DetalleCompra(2,publicado.getPrecio(), publicado, respuestaC);
            //DetalleCompra dc2= new DetalleCompra(2,publicado.getPrecio(), publicado, respuestaC);
            DetalleCompra respuestaDc= detalleCompraServicio.anadirCarrito(dc);
            Assertions.assertNotNull(respuestaDc);
        }catch(Exception e){
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
    }
}
