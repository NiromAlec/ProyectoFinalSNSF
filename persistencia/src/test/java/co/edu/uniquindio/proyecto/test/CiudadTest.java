package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CiudadTest {

    @Autowired
    private CiudadRepo ciudadRepo;

    @Test
    public void registrarTest(){

        Ciudad ciudad = new Ciudad("Cali");

        Ciudad ciudadGuardado= ciudadRepo.save(ciudad);
        System.out.println(ciudadGuardado);
        Assertions.assertNotNull(ciudadGuardado);
    }


    @Test
    @Sql("classpath:pruebas.sql")
    public void eliminarTest(){

        ciudadRepo.deleteById(1);

        Ciudad ciudadBuscado= ciudadRepo.findById(1).orElse(null);

        Assertions.assertNull(ciudadBuscado);
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public void actualizarTest(){

        Ciudad guardado = ciudadRepo.findById(2).orElse(null);
        guardado.setNombre("Pereira");
        ciudadRepo.save(guardado);
        Ciudad ciudadBuscado= ciudadRepo.findById(2).orElse(null);
        Assertions.assertEquals("Pereira", ciudadBuscado.getNombre());
    }
    @Test
    @Sql("classpath:pruebas.sql")
    public  void listarTest(){
        List<Ciudad> ciudades= ciudadRepo.findAll();
        ciudades.forEach(ciudad -> System.out.println(ciudad));
    }
}
