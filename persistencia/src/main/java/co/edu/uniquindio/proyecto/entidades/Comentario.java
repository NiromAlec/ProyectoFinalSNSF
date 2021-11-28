package co.edu.uniquindio.proyecto.entidades;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//@AllArgsConstructor
@ToString

public class Comentario implements Serializable {
    @Id
    @Column(nullable = false,length = 10)
    @EqualsAndHashCode.Include
    private String codigo;

    @Column(nullable = false)
    private String mensajeComentario;

    @Column(nullable = false)
    private String respuesta;

    @Column(nullable = false)
    private LocalDate fechaComentario;

    @Column(nullable = false)
    private double calificacion;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Usuario usuario;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Producto producto;

    public Comentario(String codigo, String mensajeComentario, String respuesta, LocalDate fechaComentario, double calificacion, Usuario usuario, Producto producto) {
        this.codigo = codigo;
        this.mensajeComentario = mensajeComentario;
        this.respuesta = respuesta;
        this.fechaComentario = fechaComentario;
        this.calificacion = calificacion;
        this.usuario = usuario;
        this.producto = producto;
    }
}
