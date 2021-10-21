package co.edu.uniquindio.proyecto.entidades;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
/**
 * Clase compra, restricciones para atributos y uso de lombok
 */
public class Compra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    private LocalDate fechaCompra;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private  MedioPago medioPago;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "compra")
    @ToString.Exclude
    private List<DetalleCompra> detallesCompras;

    public Compra(Integer codigo, LocalDate fechaCompra, MedioPago medioPago, Usuario usuario) {
        this.codigo = codigo;
        this.fechaCompra=fechaCompra;
        this.medioPago=medioPago;
        this.usuario=usuario;
    }
}