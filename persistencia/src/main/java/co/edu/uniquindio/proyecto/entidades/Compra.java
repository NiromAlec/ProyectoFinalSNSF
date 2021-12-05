package co.edu.uniquindio.proyecto.entidades;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Compra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false)
    private LocalDateTime fechaCompra;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private  MedioPago medioPago;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "compra")
    @ToString.Exclude
    private List<DetalleCompra> detallesCompras;

    public Compra(LocalDateTime fechaCompra, MedioPago medioPago, Usuario usuario) {
        //this.codigo = codigo;
        this.fechaCompra=fechaCompra;
        this.medioPago=medioPago;
        this.usuario=usuario;
    }

    public String getFechaEstilo(){
        return fechaCompra.format(DateTimeFormatter.ISO_DATE);
    }
}