package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
/**
 * Clase Usuario, restricciones para atributos y uso de lombok
 * Extiende de Persona por ende llama tambien su ToString
 */
public class Usuario extends Persona implements Serializable {

    @ElementCollection
    @Column(nullable = false)
    private Map <String, String> telefonos;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Chat> chats;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Ciudad ciudad;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Compra> compras;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<SubastaUsuario> subastaUsuarios;
    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "usuario")
    @ToString.Exclude
    private List<Producto> productos;

    @ManyToMany
    @ToString.Exclude
    private List<Producto> favoritos;


    public Usuario(String codigo, String nombre, String email, String password, Map<String, String> telefonos, Ciudad ciudad) {
        super(codigo, nombre, email, password);
        this.telefonos = telefonos;
        this.ciudad=ciudad;
    }





}
