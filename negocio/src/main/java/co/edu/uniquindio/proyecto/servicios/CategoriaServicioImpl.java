package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServicioImpl implements CategoriaServicio{
    @Autowired
    private CategoriaRepo categoriaRepo;

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepo.findAll();
    }

    @Override
    public Categoria obtenerCategoria(Integer codigo) throws Exception {
        return categoriaRepo.findById(codigo).orElseThrow(()-> new Exception("El codigo de la categoria no corresponde a ninguna de las existentes"));
    }
}
