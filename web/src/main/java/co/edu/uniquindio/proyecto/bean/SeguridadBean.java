package co.edu.uniquindio.proyecto.bean;


import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.MedioPago;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.CompraServicio;
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
import java.util.List;

@Scope("session")
@ApplicationScope
@Component
public class SeguridadBean implements Serializable {
    @Getter @Setter
    private boolean autenticado;

    @Getter @Setter
    private String email,password;

    @Getter @Setter
    private Usuario usuarioSesion;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private CompraServicio compraServicio;

    @Getter @Setter
    private ArrayList<ProductoCarrito> productosCarrito;

    @Getter @Setter
    private Double subtotal;

    @Autowired
    private ProductoServicio productoServicio;
    @Getter @Setter
    private List<Producto> productos;
    @Getter @Setter
    private List<MedioPago> mediosPago;
    @Getter @Setter
    private Compra compra;






    @PostConstruct
    public void inicializar(){
        this.subtotal=0D;
        this.compra=new Compra();
        this.productosCarrito=new ArrayList<>();
       // this.productos=new ArrayList<>();
        mediosPago=productoServicio.listarMediosPago();
       // this.productos= productoServicio.listarTodosLosProductos();
       // this.Listacompras=compraServicio.listarComprasUsuario(usuarioSesion.getCodigo());


    }

    public String iniciarSesion(){
        if(!email.isEmpty() && !password.isEmpty()){
            try {
                usuarioSesion = usuarioServicio.iniciarSesion(email,password);
                autenticado = true;
               // this.productos=productoServicio.buscarProductosUsuario(usuarioSesion.getCodigo());

                return "/index?faces-redirect=true";

            } catch (Exception e) {
                FacesMessage fm= new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("login-bean", fm);
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

        for(ProductoCarrito p: productosCarrito){
            subtotal+= p.getPrecio()*p.getUnidades();
        }

    }



    public void comprar(){
        Boolean centinela=true;
        if(usuarioSesion!=null && !productosCarrito.isEmpty()){
            try {
                for(ProductoCarrito p: productosCarrito){
                    if(p.getUnidades()> productoServicio.obtenerProducto(Integer.parseInt(p.getId())).getUnidades()) {
                        FacesMessage fm= new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta", "No hay unidades disponibles para el Producto: "+ p.getNombre());
                        FacesContext.getCurrentInstance().addMessage("compra-msj", fm);
                        centinela=false;
                    }
                }
                if(centinela) {
                    productoServicio.comprarProductos(usuarioSesion, productosCarrito, MedioPago.CREDITO);
                    productosCarrito.clear();
                    subtotal = 0D;

                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Compra Realizada Correctamente");
                    FacesContext.getCurrentInstance().addMessage("compra-msj", fm);
                }

            }catch (Exception e){
                FacesMessage fm= new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("compra-msj", fm);

            }
        }

    }
}
