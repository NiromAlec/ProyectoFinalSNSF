package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
//CONSULTAS 1
    @Test
    @Sql("classpath:pruebas.sql")
    public void filtrarNombreTest(){
        List<Usuario>Lista=usuarioRepo.findAllByNombreContains("Santiago");
        Lista.forEach(u->System.out.println(u));
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public void filtrarEmailTest(){
        Optional<Usuario> usuario= usuarioRepo.findAllByEmail("nikorodri@gmail.com");
        if(usuario.isPresent()){
            System.out.println(usuario.get());
        }else{
            System.out.println("No existe ese correo");
        }
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public void paginarListaTest(){

        Pageable paginador= PageRequest.of(1,2);
        Page<Usuario> lista= usuarioRepo.findAll(paginador);
        System.out.println(lista.stream().collect(Collectors.toList()));

    }

    @Test
    @Sql("classpath:pruebas.sql")
    public void ordenarListaTest(){

       // Pageable paginador= PageRequest.of(1,2);
        List<Usuario> lista= usuarioRepo.findAll(Sort.by("nombre"));
        System.out.println(lista);

    }

    //CONSULTAS 2
    @Test
    @Sql("classpath:pruebas.sql")
    public void obtenerListaFavoritosTest(){

        // Pageable paginador= PageRequest.of(1,2);
        List<Producto> fav= usuarioRepo.obtenerProductoFavoritos("nikorodri@gmail.com");
        Assertions.assertEquals(2, fav.size());

    }

    @Test
    @Sql("classpath:pruebas.sql")
    public void listarUsuuariosCiudadTest(){


        List<Usuario> usuarios= ciudadRepo.listarUsuario("Armenia");
       Assertions.assertEquals(2,usuarios.size());

    }

    @Test
    @Sql("classpath:pruebas.sql")
    public void listarUsuuariosProductosTest(){

         List<Object[]> respuesta= usuarioRepo.listarUsuariosyProductos();
        //respuesta.forEach(System.out::println);

        for (Object[] objeto: respuesta){
            System.out.println(objeto[0]+"----"+objeto[1]);
        }

    }



}
