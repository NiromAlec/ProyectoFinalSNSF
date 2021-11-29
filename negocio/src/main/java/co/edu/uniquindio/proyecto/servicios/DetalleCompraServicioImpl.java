package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.repositorios.DetalleCompraRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleCompraServicioImpl implements DetalleCompraServicio{

    private final DetalleCompraRepo detalleCompraRepo;

    public DetalleCompraServicioImpl(DetalleCompraRepo detalleCompraRepo) {
        this.detalleCompraRepo = detalleCompraRepo;
    }

    @Override
    public DetalleCompra anadirCarrito(DetalleCompra dc) throws Exception {
        try{
            return detalleCompraRepo.save(dc);
        }catch(Exception e){
            throw new Exception((e.getMessage()));
        }
    }
}
