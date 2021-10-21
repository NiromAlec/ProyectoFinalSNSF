package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

@ToString

public class Persona implements Serializable {
    @Id
    @Column(length = 30)
    @EqualsAndHashCode.Include
    private String codigo;

    @Column(nullable = false, length = 40)
    private String nombre;

    @Column(nullable = false, length = 40, unique = true)
    private String email;

    @Column(nullable = false, length = 30)
    private String contrasena;


}