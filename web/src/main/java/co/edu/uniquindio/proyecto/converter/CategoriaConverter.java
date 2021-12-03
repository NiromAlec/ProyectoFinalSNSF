package co.edu.uniquindio.proyecto.converter;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.servicios.CategoriaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;

@Component
public class CategoriaConverter implements Converter<Categoria>, Serializable {
    @Autowired
    private CategoriaServicio categoriaServicio;
    @Override
    public Categoria getAsObject(FacesContext arg0, UIComponent arg1, String value) {
        Categoria categoria=null;
        try {
            categoria=categoriaServicio.obtenerCategoria(Integer.parseInt(value));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoria;
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Categoria value) {
        if(value!=null){
            return value.getCodigo().toString();
        }
        return "";
    }
}