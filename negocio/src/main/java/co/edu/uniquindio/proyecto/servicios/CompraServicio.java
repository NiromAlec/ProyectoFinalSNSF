package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Compra;

import java.util.List;

public interface CompraServicio {
    Compra comprarProductos(Compra compra) throws Exception;

    List<Compra> listarComprasUsuario(String id);
}
