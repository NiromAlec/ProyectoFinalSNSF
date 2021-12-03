package co.edu.uniquindio.proyecto.converter;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;

@Component
public class CiudadConverter implements Converter<Ciudad>, Serializable {
    @Autowired
    private CiudadServicio ciudadServicio;
    @Override
    public Ciudad getAsObject(FacesContext arg0, UIComponent arg1, String value) {
        Ciudad ciudad=null;
        try {
            ciudad=ciudadServicio.obtenerCiudad(Integer.parseInt(value));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ciudad;
    }
    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Ciudad value) {
        if(value!=null){
            return value.getCodigo().toString();
        }
        return "";
    }
}