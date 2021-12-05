package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString

public class Subasta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private  Integer codigo;

    @Column(nullable = false)
    @Future
    private LocalDateTime fechaLimite;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Producto producto;

    @OneToMany(mappedBy = "subasta")
    @ToString.Exclude
    private List<SubastaUsuario> subastaUsuarios;

    public Subasta(LocalDateTime ffechaLimite, Producto producto ){

        this.fechaLimite=fechaLimite;
        this.producto=producto;
    }
}
