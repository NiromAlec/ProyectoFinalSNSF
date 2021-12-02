package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.dto.ProductoValido;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional

public class ProductoServicioTest {
    @Autowired
    private ProductoServicio productoServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Test
    public void obtenerProductoTest(){
        try {
            Usuario u= new Usuario ("69697", "Santy", "santy02@email", "santy0201", null, null);
            usuarioServicio.registrarUsario(u);
            Usuario vendedor = usuarioServicio.obtenerUnUsuario("69697");
            LocalDateTime ldt = LocalDateTime.of(2021, 12, 25, 20, 10);
            Producto producto = new Producto("6161","TV", 2, "444", 4.500000, ldt, 1.5, vendedor, null,"Promocion");
            //Producto producto2 = new Producto("6161","TV", 2, "444", 4.500000, ldt, 1.5, vendedor, null);
            //Producto producto3 = new Producto("6161","TV", 2, "444", 4.500000, ldt, 1.5, vendedor, null);

            Producto publicado= productoServicio.publicarProducto(producto);
            Assertions.assertNotNull(publicado);

        }catch (Exception e){
            Assertions.assertTrue(false, e.getMessage());
        }
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public void listarProductoDisponible(){

        List<ProductoValido> productos= productoServicio.listarProductosPorCategoria(3,LocalDateTime.now());
        productos.forEach(System.out::println);

    }

    @Test
    public void comentarProductoTest(){

            Usuario u = new Usuario("6969", "Santy", "santy@email", "santy0201", null, null);
        try {
            usuarioServicio.registrarUsario(u);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Usuario vendedor = null;
        try {
            vendedor = usuarioServicio.obtenerUnUsuario("6969");
        } catch (Exception e) {
            e.printStackTrace();
        }
        LocalDateTime ldt = LocalDateTime.of(2021, 11, 25, 20, 10);
            Producto producto = new Producto("6162","TV", 2, "444", 4.500000, ldt, 1.5, vendedor, null,"Promocion");
        try {
            Producto publicado= productoServicio.publicarProducto(producto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Comentario comentario= new Comentario("500","excelente servicio y buena calidad",null,null, 4.5, u,producto);
            productoServicio.comentarProducto(comentario);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void guardarProductoFavorito(){
        Usuario u = new Usuario("6969", "Santy", "santy@email", "santy0201", null, null);
        try {
            usuarioServicio.registrarUsario(u);

        Usuario vendedor = null;
        vendedor = usuarioServicio.obtenerUnUsuario("6969");

        LocalDateTime ldt = LocalDateTime.of(2021, 11, 25, 20, 10);
        Producto producto = new Producto("6162","TV", 2, "444", 4.500000, ldt, 1.5, vendedor, null,"Promocion");

        Producto publicado= productoServicio.publicarProducto(producto);

        productoServicio.guardarProductoFav(producto,vendedor);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void eliminarProductoFavoritos(){
        Usuario u = new Usuario("6969", "Santy", "santy@email", "santy0201", null, null);
        try {
            usuarioServicio.registrarUsario(u);

            Usuario vendedor = null;
            vendedor = usuarioServicio.obtenerUnUsuario("6969");

            LocalDateTime ldt = LocalDateTime.of(2021, 11, 25, 20, 10);
            Producto producto = new Producto("6162","TV", 2, "444", 4.500000, ldt, 1.5, vendedor, null,"Promocion");

            Producto publicado= productoServicio.publicarProducto(producto);

            productoServicio.eliminarProductoFav(producto,vendedor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void eliminarProductoTest(){

        try {
            productoServicio.eliminarProducto("6969");
            Assertions.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
    }


}
