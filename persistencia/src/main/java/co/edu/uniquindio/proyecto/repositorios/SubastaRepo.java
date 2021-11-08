package co.edu.uniquindio.proyecto.repositorios;


import co.edu.uniquindio.proyecto.entidades.Subasta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SubastaRepo extends JpaRepository<Subasta, String>  {
/*
    @Query("select s from Subasta s,IN(s.producto.categorias) c where  c.codigo = :codigo")
    List<Subasta> ObtenerSubasta(Integer id);

*/
}
