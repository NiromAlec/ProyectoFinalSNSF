package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CompraRepo  extends JpaRepository<Compra, Integer> {

    @Query("select c from Compra  c join c.usuario u where :codigo=u.codigo")
    List<Compra> comprasUsuario(String codigo);
}
