
package com.co.jv.service;

import com.co.jv.entity.Usuario;
import java.util.Optional;


public interface UsuarioService {
    
    public void guardar(Usuario usuario);
    
    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);
    
    Usuario obtenerUsuarioPorCorreo(Usuario usuario);
   
    Optional<Usuario> obtenerUsuarioPorId(Usuario usuario);
}
