package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import co.edu.uniquindio.proyecto.servicios.CompraServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class UsuarioBean implements Serializable {
    @Getter @Setter
    private Usuario usuario;
    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private CiudadServicio ciudadServicio;
    @Autowired
    private CompraServicio compraServicio;
    @Getter @Setter
    private List<Ciudad> ciudades;

    @Getter @Setter
    private Compra compra;

    @Getter @Setter
    private List<Compra> Listacompras;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    @PostConstruct
    public void inicializar(){

        usuario= new Usuario();
        this.compra=new Compra();
        ciudades= ciudadServicio.listarCiudades();
        if(usuarioSesion!=null){
            this.Listacompras=compraServicio.listarComprasUsuario(usuarioSesion.getCodigo());
        }
        //this.Listacompras=compraServicio.listarComprasUsuario(usuarioSesion.getCodigo());

    }
    public void registrarUsuario(){
        try {
            usuarioServicio.registrarUsario(usuario);
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta",
                    "Registro exitoso");
            FacesContext.getCurrentInstance().addMessage("usuarioBean", facesMsg);
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta",
                    e.getMessage());
            FacesContext.getCurrentInstance().addMessage("usuarioBean", facesMsg);
        }
    }


}
