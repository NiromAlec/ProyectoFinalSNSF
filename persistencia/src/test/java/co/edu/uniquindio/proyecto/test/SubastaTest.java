package co.edu.uniquindio.proyecto.test;



import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.SubastaRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class SubastaTest {

    @Autowired
    private SubastaRepo subastaRepo;
    @Autowired
    private ProductoRepo productoRepo;

    @Test
    @Sql("classpath:pruebas.sql")
    public void registrarTest(){

        Producto producto = productoRepo.findById("111").orElse(null);

        Subasta subasta= new Subasta("4", LocalDate.now(), producto );

        Subasta subastaGuardado= subastaRepo.save(subasta);
        System.out.println(subastaGuardado);
        Assertions.assertNotNull(subastaGuardado);

    }



    @Test
    @Sql("classpath:pruebas.sql")
    public void eliminarTest(){
        subastaRepo.deleteById("5");
        Subasta subastaBuscado = subastaRepo.findById("5").orElse(null);
        Assertions.assertNull(subastaBuscado);
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public void actualizarTest(){

        Producto producto = productoRepo.findById("222").orElse(null);
        Subasta guardado = subastaRepo.findById("6").orElse(null);
        guardado.setProducto(producto);
        subastaRepo.save(guardado);
        Subasta subastaBuscado= subastaRepo.findById("6").orElse(null);
        Assertions.assertEquals(producto, subastaBuscado.getProducto());
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public  void listarTest(){
        List<Subasta> subastas= subastaRepo.findAll();
        subastas.forEach(subasta -> System.out.println(subasta));
    }
/*
    @Test
    @Sql("classpath:pruebas.sql")
    public void obtenerSubastaCategoria(){
        List<Subasta> subastas=sRepo.ObtenerSubasta(1);
        //Assertions.assertEquals(2,subastas.size());
        subastas.forEach(System.out::println);
    }
*/

}
