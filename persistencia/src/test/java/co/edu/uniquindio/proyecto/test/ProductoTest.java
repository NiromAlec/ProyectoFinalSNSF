package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductoTest {

    @Autowired
    private ProductoRepo productoRepo;
    @Autowired
    private CiudadRepo ciudadRepo;
    @Autowired
    private UsuarioRepo usuarioRepo;

    @Test
    @Sql("classpath:pruebas.sql")
    public void regirtrarTest(){

        Ciudad ciudad = ciudadRepo.findById(1).orElse(null);
        Usuario usuario = usuarioRepo.findById("789").orElse(null);
        Producto producto= new Producto("444", "arequipe", 10, "arequipe de queso",5000.0,LocalDate.now(), 0.5,  usuario,ciudad);

        Producto productoGuardado= productoRepo.save(producto);
        System.out.println(productoGuardado);
        Assertions.assertNotNull(productoGuardado);
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public void eliminarTest(){

        productoRepo.deleteById("222");

        Producto productoBuscado= productoRepo.findById("222").orElse(null);

        Assertions.assertNull(productoBuscado);
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public void actualizarTest(){

        Producto guardado = productoRepo.findById("333").orElse(null);
        guardado.setNombre("Cafe");
        productoRepo.save(guardado);
        Producto productoBuscado= productoRepo.findById("333").orElse(null);
        Assertions.assertEquals("Cafe", productoBuscado.getNombre());
    }
    @Test
    @Sql("classpath:pruebas.sql")
    public  void listarTest(){
        List<Producto> productos= productoRepo.findAll();
        productos.forEach(producto -> System.out.println(producto));
    }


}
