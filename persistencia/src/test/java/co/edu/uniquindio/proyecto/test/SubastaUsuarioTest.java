package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.SubastaRepo;
import co.edu.uniquindio.proyecto.repositorios.SubastaUsuarioRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SubastaUsuarioTest {

    @Autowired
    SubastaUsuarioRepo subastaUsuarioRepo;
    @Autowired
    SubastaRepo subastaRepo;
    @Autowired
    UsuarioRepo usuarioRepo;


    @Test
    @Sql("classpath:pruebas.sql")
    public void registrarTest(){
        Subasta subasta=subastaRepo.findById("4").orElse(null);
        Usuario usuario=usuarioRepo.findById("123").orElse(null);

        SubastaUsuario subastaUsuario =new SubastaUsuario("1234", 45000.0, LocalDate.now(), usuario, subasta);

        SubastaUsuario subastaUsuarioGuardado= subastaUsuarioRepo.save(subastaUsuario);
        System.out.println(subastaUsuarioGuardado);
        Assertions.assertNotNull(subastaUsuarioGuardado);
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public void eliminarTest(){
        subastaUsuarioRepo.deleteById("1");
        SubastaUsuario subastaUsuarioBuscado = subastaUsuarioRepo.findById("1").orElse(null);
        Assertions.assertNull(subastaUsuarioBuscado);
    }


    @Test
    @Sql("classpath:pruebas.sql")
    public void actualizarTest(){

        SubastaUsuario guardado = subastaUsuarioRepo.findById("2").orElse(null);
        guardado.setValor(55000.0);
        subastaUsuarioRepo.save(guardado);
        SubastaUsuario subastaUsuarioBuscado= subastaUsuarioRepo.findById("2").orElse(null);
        Assertions.assertEquals(55000, subastaUsuarioBuscado.getValor());
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public  void listarTest(){
        List<SubastaUsuario> subastaUsuarios= subastaUsuarioRepo.findAll();
        subastaUsuarios.forEach(subastaUsuario -> System.out.println(subastaUsuario));
    }


}
