package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioTest {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Test
    @Sql("classpath:pruebas.sql")
    public void registrarTest(){
        Ciudad ciudad= ciudadRepo.findById(1).orElse(null);
        Map<String, String> telefonos= new HashMap<>();
        telefonos.put("Trabajo", "3015219172");
        telefonos.put("Celular", "3132963897");
        Usuario usuario= new Usuario("741", "Thomas", "tomas@email", "tom0201", telefonos, ciudad);
        Usuario usuarioGuardado= usuarioRepo.save(usuario);
        System.out.println(usuarioGuardado);
        Assertions.assertNotNull(usuarioGuardado);

    }
    @Test
    @Sql("classpath:pruebas.sql")
    public void eliminarTest(){

        usuarioRepo.deleteById("456");

        Usuario usuarioBuscado= usuarioRepo.findById("456").orElse(null);

        Assertions.assertNull(usuarioBuscado);
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public void actualizarTest(){


        Usuario guardado = usuarioRepo.findById("123").orElse(null);

        guardado.setEmail("meLlevalaVerdura@email");

        usuarioRepo.save(guardado);

        Usuario usuariobuscado= usuarioRepo.findById("123").orElse(null);

        Assertions.assertEquals("meLlevalaVerdura@email", usuariobuscado.getEmail());

    }

    @Test
    @Sql("classpath:pruebas.sql")
    public  void listarTest(){

        List<Usuario> usuarios= usuarioRepo.findAll();

        usuarios.forEach(usuario -> System.out.println(usuario));
    }
}
