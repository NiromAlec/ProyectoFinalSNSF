package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.CategoriaServicio;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.shaded.commons.io.IOUtils;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@Component
@ViewScoped
public class ProductoBean implements Serializable {

    @Getter
    @Setter
    private Producto producto;
    @Autowired
    private ProductoServicio productoServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private CategoriaServicio categoriaServicio;
    @Autowired
    private CiudadServicio ciudadServicio;

    private ArrayList<String> imagenes;
    @Getter @Setter
    private List<Categoria> categorias;
    @Getter @Setter
    private List<Ciudad> ciudades;

    @Value("${upload.url}")
    private String urlUploads;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    @PostConstruct
    public void inicializar() {
        this.producto = new Producto();
        this.imagenes = new ArrayList<>();
        categorias=categoriaServicio.listarCategorias();
        ciudades= ciudadServicio.listarCiudades();
    }

    public void modificarProducto(){

    }
    public void crearProducto() {
        try {
            if(usuarioSesion!=null) {
                if (!imagenes.isEmpty()) {

                    producto.setUsuario(usuarioSesion);
                    producto.setImagen(imagenes);
                    producto.setFechaLimite(LocalDateTime.now().plusMonths(1));
                    productoServicio.publicarProducto(producto);
                    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta",
                            "Producto creado satisfacotiramente :D");
                    FacesContext.getCurrentInstance().addMessage("productoBean", facesMsg);
                }
            }

        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta",
                    "Es necesario subir almenos una imagen");
            FacesContext.getCurrentInstance().addMessage("productoBean", facesMsg);
        }
    }

    public void subirImagenes(FileUploadEvent event) {
        UploadedFile imagen = event.getFile();
        String nombreImagen = subirImagen(imagen);
        if (nombreImagen != null) {
            imagenes.add(nombreImagen);
        }

    }

    private String subirImagen(UploadedFile imagen) {
        try {
            File archivo = new File(urlUploads + "/"+ imagen.getFileName());
            OutputStream outputStream = new FileOutputStream(archivo);
            IOUtils.copy(imagen.getInputStream(), outputStream);
            return imagen.getFileName();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }
}
