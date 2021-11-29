package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicioImpl implements UsuarioServicio{

    private final UsuarioRepo usuarioRepo;

    public UsuarioServicioImpl(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public Usuario registrarUsario(Usuario u) throws Exception {

        Optional<Usuario> buscado= usuarioRepo.findById(u.getCodigo());
        if(buscado.isPresent()){
            throw new Exception("El código del usuario ya Existe");
        }
        buscado= buscarEmail(u.getEmail());
        if(buscado.isPresent()){
            throw new Exception("El Email del usuario ya Existe");
        }
        return usuarioRepo.save(u);
    }

    @Override
    public Usuario actualizarUsuario(Usuario u) throws Exception {

        Optional<Usuario> buscado= usuarioRepo.findById(u.getCodigo());
        if (buscado.isEmpty()){
            throw new Exception("El usuario no existe");
        }
        return usuarioRepo.save(u);
    }

  /*  private Usuario buscarUsuario(Usuario u) throws Exception {
        Optional<Usuario> buscado= usuarioRepo.findById(u.getCodigo());
        if(buscado.isPresent()){
            throw new Exception("El código del usuario ya Existe");
        }
        buscado= buscarEmail(u.getEmail());
        if(buscado.isPresent()){
            throw new Exception("El Email del usuario ya Existe");
        }

        return usuarioRepo.save(u);
    }*/

    private Optional<Usuario> buscarEmail(String Email){
       return usuarioRepo.findAllByEmail(Email);

    }

    @Override
    public void eliminarUsuario(String codigo) throws Exception {
        Optional<Usuario> buscado= usuarioRepo.findById(codigo);
        if(buscado.isEmpty()){
            throw new Exception("El código del usuario No Existe");
        }
        usuarioRepo.deleteById(codigo);
    }

    @Override
    public List<Usuario> listarUsuario() {
        return usuarioRepo.findAll();
    }

    @Override
    public List<Producto> listarFavoritos(String email) throws Exception {

        Optional<Usuario> buscado= buscarEmail(email);
        if(buscado.isEmpty()){
            throw new Exception("El código No Existe");
        }
        return usuarioRepo.obtenerProductoFavoritos(email);
    }

    @Override
    public Usuario obtenerUnUsuario(String codigo) throws Exception {
        Optional<Usuario> buscado= usuarioRepo.findById(codigo);
        if(buscado.isEmpty()){
            throw new Exception("El usuario No Existe");
        }
        return buscado.get();
    }

    @Override
    public Usuario iniciarSesion(String email, String password) throws Exception {
        return usuarioRepo.findByEmailAndContrasena(email,password).orElseThrow( () -> new Exception("Los datos de autenticacion son incorrectos"));
    }

    @Override
    public String recuperarConstrasena(String email) throws Exception {
        Optional<Usuario> buscado= usuarioRepo.findAllByEmail(email);
        if(buscado.isEmpty()){
            throw new Exception("El usuario No Existe");
        }
        return buscado.get().getContrasena();
    }
}
