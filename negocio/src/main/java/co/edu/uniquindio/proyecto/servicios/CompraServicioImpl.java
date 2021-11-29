package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompraServicioImpl implements CompraServicio{

    public final CompraRepo compraRepo;

    public CompraServicioImpl(CompraRepo compraRepo) {
        this.compraRepo = compraRepo;
    }

    @Override
    public Compra comprarProductos(Compra compra) throws Exception {
        try{
            return compraRepo.save(compra);
        }catch(Exception e){
            throw new Exception((e.getMessage()));
        }
    }
}
