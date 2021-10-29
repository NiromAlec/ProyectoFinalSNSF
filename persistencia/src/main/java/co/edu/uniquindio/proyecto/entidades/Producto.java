package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString


public class Producto implements Serializable {
    @Id
    @Column(nullable = false,length = 10)
    @EqualsAndHashCode.Include
    private String codigo;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private int unidades;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private double precio;

    @Column(nullable = false)
    private LocalDateTime fechaLimite;

    @Column(nullable = false)
    private double descuento;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Usuario usuario;

    @ManyToMany(mappedBy = "favoritos")
    private List<Usuario> anadioFavorito;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Ciudad ciudad;

    @ManyToMany
    private List<Categoria> categorias;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<DetalleCompra> detallesCompras;

    @ElementCollection
    @Column(nullable = false)
    private Map<String, String> imagen;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<Subasta> subastas;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<Comentario> comentarios;

    public Producto(String codigo, String nombre, int unidades, String descripcion, double precio, LocalDateTime fechaLimite, double descuento, Usuario usuario, Ciudad ciudad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.unidades = unidades;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fechaLimite = fechaLimite;
        this.descuento = descuento;
        this.usuario = usuario;
        this.ciudad = ciudad;
    }

}
