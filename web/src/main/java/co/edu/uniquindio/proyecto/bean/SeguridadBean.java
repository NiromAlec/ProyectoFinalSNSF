package co.edu.uniquindio.proyecto.bean;


import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.MedioPago;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;

@Scope("session")
@ApplicationScope
@Component
public class SeguridadBean implements Serializable {
    @Getter @Setter
    private boolean autenticado;

    @Getter @Setter
    private String  email,password;

    @Getter @Setter
    private Usuario usuarioSesion;

    @Getter @Setter
    private ArrayList<ProductoCarrito> productosCarrito;
    @Getter @Setter
    private Double subtotal;
    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private ProductoServicio productoServicio;
    @PostConstruct
    public void inicializar(){
        this.subtotal=0D;
        this.productosCarrito=new ArrayList<>();
    }

    public String iniciarSesion(){
        if(!email.isEmpty() && !password.isEmpty()){
            try {
                usuarioSesion = usuarioServicio.iniciarSesion(email,password);
                autenticado = true;
                return "/index?faces-redirect=true";
            } catch (Exception e) {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta",
                        e.getMessage());
                FacesContext.getCurrentInstance().addMessage("login-bean", facesMsg);
            }
        }
        return null;
    }

    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }

    public void agregarAlCArrito(String id, Double precio, String nombre, String imagen){
        ProductoCarrito pc=new ProductoCarrito(id,nombre,imagen, precio, 1);
        if(!productosCarrito.contains(pc)) {
            productosCarrito.add(pc);
            subtotal += precio;
            FacesMessage fm= new FacesMessage(FacesMessage.SEVERITY_INFO,"Alerta", "El Producto se Agregó al Carrito");
            FacesContext.getCurrentInstance().addMessage("add-cart", fm);
        }

    }

    public void eliminarDelCarrito(int index){
        subtotal-=productosCarrito.get(index).getPrecio();
        productosCarrito.remove(index);

    }

    public void actualizarSubtotal(){
        subtotal=0D;
        for (ProductoCarrito p: productosCarrito) {
            subtotal+=p.getPrecio()*p.getUnidades();
        }
    }

    public void comprar(){
        if(usuarioSesion!=null&&!productosCarrito.isEmpty()){
            try {

                productoServicio.comprarProductos(usuarioSesion, productosCarrito, MedioPago.EFECTIVO);
                productosCarrito.clear();
                subtotal=0D;
                FacesMessage fm= new FacesMessage(FacesMessage.SEVERITY_INFO,"Alerta", "La compra fue realizada satisfactoriamente");
                FacesContext.getCurrentInstance().addMessage("compra-msj", fm);
            } catch (Exception e) {
                FacesMessage fm= new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("compra-msj", fm);
            }
        }

    }
}