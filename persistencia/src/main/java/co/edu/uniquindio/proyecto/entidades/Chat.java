package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Chat implements Serializable {

    @Id
    @Column(nullable = false,length = 10)
    @EqualsAndHashCode.Include
    private String codigo;

    @OneToMany(mappedBy = "chat")
    @ToString.Exclude
    private List<Mensaje> mensajes;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Usuario usuario;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Producto producto;

    public Chat(String codigo, Usuario usuario, Producto producto) {
        this.codigo = codigo;
        this.usuario=usuario;
        this.producto=producto;
    }
}
