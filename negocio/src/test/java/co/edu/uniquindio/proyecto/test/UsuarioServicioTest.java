package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class UsuarioServicioTest {
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Test
    public void registrarTest() {
        Usuario u = new Usuario("741", "Thomas", "tomas1@email", "tom0201", null, null);
        try {
            Usuario respuesta = usuarioServicio.registrarUsario(u);
            Assertions.assertNotNull(respuesta);
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
    }

    @Test
    public void eliminarTest() {

        try {
            Usuario u = new Usuario("741", "Thomas", "tomas1@email", "tom0201", null, null);
            Usuario respuesta = usuarioServicio.registrarUsario(u);
            usuarioServicio.eliminarUsuario("741");
            Assertions.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
    }

    @Test
    public void listarTest() {
        List<Usuario> lista = usuarioServicio.listarUsuario();
        lista.forEach(System.out::println);
    }

    @Test
    public void actualizarTest() {

        try {
            Usuario u = usuarioServicio.obtenerUnUsuario("741");
            u.setContrasena("789ko");
            usuarioServicio.actualizarUsuario(u);

            Usuario modificado = usuarioServicio.obtenerUnUsuario("741");
            Assertions.assertEquals("789ko", modificado.getContrasena());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void iniciarSesionTest() {
        try {
            Usuario usuario = usuarioServicio.iniciarSesion("tomas1@email", "tom0201");
            Assertions.assertNotNull(usuario);
        } catch (Exception e) {
            Assertions.assertTrue(false, e.getMessage());
        }
    }

    @Test
    public void recuperarContrasena() {
        try {
            Usuario u = new Usuario("741", "Thomas", "tomas1@email", "tom0201", null, null);
            usuarioServicio.registrarUsario(u);
            String contrasenaRecuperada = usuarioServicio.recuperarConstrasena("tomas2@email");

            Assertions.assertEquals(contrasenaRecuperada, "tom0201");
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
    }
}


