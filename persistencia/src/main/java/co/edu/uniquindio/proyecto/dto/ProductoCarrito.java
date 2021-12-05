package co.edu.uniquindio.proyecto.dto;


import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductoCarrito {

    @EqualsAndHashCode.Include
    private String id;
    private String nombre, imagen;
    private Double precio;
    private Integer unidades;

}
