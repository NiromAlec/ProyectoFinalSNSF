package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import co.edu.uniquindio.proyecto.repositorios.DetalleCompraRepo;
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
public class DetalleCompraTest {
    @Autowired
    private ProductoRepo productoRepo;
    @Autowired
    private CompraRepo compraRepo;
    @Autowired
    private DetalleCompraRepo detalleCompraRepo;


    @Test
    @Sql("classpath:pruebas.sql")
    public void registrarTest(){

        Compra compra= compraRepo.findById(1).orElse(null);
        Producto producto= productoRepo.findById(111).orElse(null);
        DetalleCompra detalleCompra= new DetalleCompra(5,600.0,producto,compra);
        DetalleCompra detalleCompraGuardado= detalleCompraRepo.save(detalleCompra);
        System.out.println(detalleCompraGuardado);

        Assertions.assertNotNull(detalleCompraGuardado);

    }
    @Test
    @Sql("classpath:pruebas.sql")
    public void eliminarTest(){

        detalleCompraRepo.deleteById("569");
        DetalleCompra detalleCompraBucado= detalleCompraRepo.findById("569").orElse(null);
        Assertions.assertNull(detalleCompraBucado);
    }

    @Test
    @Sql("classpath:pruebas.sql")
    public void actualizarTest(){

        DetalleCompra detalleCompraGuardado= detalleCompraRepo.findById("785").orElse(null);
        detalleCompraGuardado.setUnidades(100);

        detalleCompraRepo.save(detalleCompraGuardado);
        DetalleCompra detalleCompraBucado= detalleCompraRepo.findById("785").orElse(null);
        Assertions.assertEquals(100, detalleCompraBucado.getUnidades());

    }

    @Test
    @Sql("classpath:pruebas.sql")
    public  void listarTest(){

        List<DetalleCompra> detalleCompras= detalleCompraRepo.findAll();
        detalleCompras.forEach(detalleCompra -> System.out.println(detalleCompra));
    }


    }
