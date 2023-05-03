
package com.co.jv.dtos;

import com.co.jv.entity.TipoUsuario;
import lombok.Data;

@Data
public class UsuarioDTO {
    
    private String nombre;
    private String apellido;
    private String username;
    private String password;
    
    private int tipo_usuario_id;

    public UsuarioDTO(String nombre, String apellido, String username, String password, int tipo_usuario_id) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.password = password;
        this.tipo_usuario_id = tipo_usuario_id;
    }

    public UsuarioDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UsuarioDTO(String username) {
        this.username = username;
    }

    public UsuarioDTO() {
    }
    
    
}
