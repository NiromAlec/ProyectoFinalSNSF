package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class InicioBean implements Serializable {

    @Getter @Setter
    private List<Producto> productos;

    @Autowired
    private ProductoServicio productoServicio;

    @PostConstruct
    public void incializar(){
       // this.productos= productoServicio.listarTodosLosProductosDisponibles();
        this.productos= productoServicio.listarTodosLosProductos();

    }

    public String irADetalle(String codigo){
        return "/detalleProducto?faces-redirect=true&amp;producto="+codigo;
    }
}
