package co.edu.uniquindio.proyecto.entidades;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @Lob
    @NotBlank
    @Column(nullable = false)
    private String mensajeComentario;

    @Lob
    private String respuesta;

    @Column(nullable = false)
    private LocalDateTime fechaComentario;

    @PositiveOrZero
    private Integer calificacion;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Usuario usuario;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Producto producto;

    public String getFechaEstilo(){
        return fechaComentario.format(DateTimeFormatter.ISO_DATE);
    }
}
