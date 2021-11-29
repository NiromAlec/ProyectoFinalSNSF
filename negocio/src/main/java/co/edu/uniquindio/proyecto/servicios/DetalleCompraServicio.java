package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Producto;

public interface DetalleCompraServicio {

    DetalleCompra anadirCarrito(DetalleCompra dc) throws Exception;
}
