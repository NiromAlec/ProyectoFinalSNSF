package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
@Getter
@Setter
public class BusquedaBean implements Serializable {


    private String busqueda;
    @Value("#{param['busqueda']}")
    private String busquedaParam;

    private List<Producto> productos;
    @Autowired
    private ProductoServicio productoServicio;

    @PostConstruct
    public void inicializar(){
        if(busquedaParam != null && !busquedaParam.isEmpty() ){
            productos = productoServicio.buscarProducto(busquedaParam,null);
        }
    }

    public String buscar(){
        return "resultado_busqueda?faces-redirect=true&amp;busqueda="+busqueda;
    }


}
