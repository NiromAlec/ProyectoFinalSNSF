package co.edu.uniquindio.proyecto.entidades;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@ToString

public class Comentario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    private String mensajeComentario;

    private String respuesta;

    @Column(nullable = false)
    private LocalDateTime fechaComentario;

    private Integer calificacion;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Usuario usuario;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Producto producto;
}
