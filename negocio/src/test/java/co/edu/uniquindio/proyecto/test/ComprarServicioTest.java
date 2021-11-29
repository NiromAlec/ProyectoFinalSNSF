package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.MedioPago;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.CompraServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class ComprarServicioTest {

    @Autowired
    private CompraServicio compraServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Test
    public void registrarCompraTest(){

        try{
            Usuario u= new Usuario ("741", "Thomas", "tomas1@email", "tom0201", null, null);
            Usuario respuesta = usuarioServicio.registrarUsario(u);
            Compra c= new Compra(LocalDateTime.now(), MedioPago.EFECTIVO, u);
            Compra respuestaC= compraServicio.comprarProductos(c);
            Assertions.assertNotNull(respuesta);
        }catch(Exception e){
            e.printStackTrace();
            Assertions.assertTrue(false);
        }

    }
}
