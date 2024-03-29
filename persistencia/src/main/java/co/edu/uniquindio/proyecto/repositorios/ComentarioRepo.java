package co.edu.uniquindio.proyecto.repositorios;


import co.edu.uniquindio.proyecto.entidades.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ComentarioRepo extends JpaRepository<Comentario, String> {

    @Query("select c from Comentario c where c.calificacion > :calificacionMenor and c.calificacion < :calificacionMayor")
    List<Comentario> listarComentariosRango1(double calificacionMenor, double calificacionMayor);

    @Query("select c from Comentario c where c.calificacion between :calificacionMenor and :calificacionMayor")
    List<Comentario> listarComentariosRango2(double calificacionMenor, double calificacionMayor);

}
