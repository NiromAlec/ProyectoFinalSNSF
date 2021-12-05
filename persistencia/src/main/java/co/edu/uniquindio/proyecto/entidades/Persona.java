package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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

    @Column(nullable = false, length = 150)
    @Length(max = 150)
    @NotBlank
    private String nombre;

    @Column(nullable = false, length = 40, unique = true)
    @Email(message = "Escriba un email valido")
    @NotBlank
    private String email;

    @Column(nullable = false, length = 100)
    @Length(max=100)
    @NotBlank
    private String contrasena;


}
