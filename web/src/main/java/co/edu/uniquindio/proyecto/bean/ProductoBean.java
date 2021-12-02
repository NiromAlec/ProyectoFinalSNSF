package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
@ViewScoped
@Getter
@Setter
public class ProductoBean implements Serializable {

    private Producto producto;
    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    private ArrayList<String> imagenes;

    @Value("${upload.url}")
    private String urlUploads;

    @PostConstruct
    public void inicializar(){
        this.producto= new Producto();
        this.imagenes=new ArrayList<>();
    }

    public void crearProducto(){

        try {
            if(!imagenes.isEmpty()) {
                Usuario usuario = usuarioServicio.obtenerUnUsuario("123");
                producto.setUsuario(usuario);
                producto.setImagen(imagenes);
                productoServicio.publicarProducto(producto);

                FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Producto Creado Satisfactoriamente");
                FacesContext.getCurrentInstance().addMessage(null,msg);

            }else{
                FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", "Es Necesario agregar al menos una Imagen para la Publicación del Producto");
                FacesContext.getCurrentInstance().addMessage(null,msg);
            }
        } catch (Exception e) {
            FacesMessage fm=new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
        }

    }

    public void subirImagenes(FileUploadEvent event){
        UploadedFile imagen= event.getFile();
        String nombreImagen= subirImagenen(imagen);
        if (nombreImagen != null){
            imagenes.add(nombreImagen);

        }
    }

    public String subirImagenen(UploadedFile imagen){
        try {
            File archivo = new File(urlUploads + "/" + imagen.getFileName());
            OutputStream outputStream = new FileOutputStream(archivo);
            IOUtils.copy(imagen.getInputStream(), outputStream);
            return imagen.getFileName();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
