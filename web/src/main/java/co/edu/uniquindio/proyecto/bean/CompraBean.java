package co.edu.uniquindio.proyecto.bean;


import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.CompraServicio;
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
public class CompraBean implements Serializable {



    @Getter
    @Setter
    private Compra compra;

    @Getter @Setter
    private List<Compra> Listacompras;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    @Autowired
    private CompraServicio compraServicio;

    @PostConstruct
    public void inicializar(){

        this.compra=new Compra();

        this.Listacompras=compraServicio.listarComprasUsuario(usuarioSesion.getCodigo());

    }
}
