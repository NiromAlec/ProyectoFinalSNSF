package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString


public class DetalleCompra implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Positive
    @Column(nullable = false)
    private Integer unidades;

    @Positive
    @Column(nullable = false)
    private Double precioProducto;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Producto producto;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Compra compra;

    public DetalleCompra(Integer unidades, Double precioProducto, Producto producto, Compra compra) {
        this.unidades = unidades;
        this.precioProducto = precioProducto;
        this.producto = producto;
        this.compra = compra;
    }
}