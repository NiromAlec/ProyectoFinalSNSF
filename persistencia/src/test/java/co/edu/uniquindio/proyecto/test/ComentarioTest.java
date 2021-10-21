package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
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
public class ComentarioTest {

    @Autowired
    ProductoRepo productoRepo;
    @Autowired
    UsuarioRepo usuarioRepo;
    @Autowired
    ComentarioRepo comentarioRepo;


    @Test
    @Sql("classpath:pruebas.sql")
    public void regirtrarTest(){
        Producto producto=productoRepo.findById("111").orElse(null);
        Usuario usuario=usuarioRepo.findById("123").orElse(null);
        Comentario comentario=new Comentario("283", "me gusto el producto", "Thanksyou", LocalDate.now(), 5.0, usuario, producto);
        Comentario comentarioGuardado= comentarioRepo.save(comentario);
        System.out.println(comentarioGuardado);
        Assertions.assertNotNull(comentarioGuardado);
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public void eliminarTest(){
        comentarioRepo.deleteById("a2");
        Comentario comentarioBuscado = comentarioRepo.findById("a2").orElse(null);
        Assertions.assertNull(comentarioBuscado);
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public void actualizarTest(){


        Comentario guardado = comentarioRepo.findById("a3").orElse(null);
        guardado.setRespuesta("I like it");
        comentarioRepo.save(guardado);
        Comentario comentarioBuscado= comentarioRepo.findById("a3").orElse(null);
        Assertions.assertEquals("I like it", comentarioBuscado.getRespuesta());
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public  void listarTest(){
        List<Comentario> comentarios= comentarioRepo.findAll();
        comentarios.forEach(comentario -> System.out.println(comentario));
    }

}
