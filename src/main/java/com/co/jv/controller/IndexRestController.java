package com.co.jv.controller;

import com.co.jv.dtos.SalaDTO;
import com.co.jv.dtos.UsuarioDTO;
import com.co.jv.dtos.UsuarioLogin;
import com.co.jv.entity.Sala;
import com.co.jv.entity.TipoUsuario;
import com.co.jv.entity.Usuario;
import com.co.jv.service.SalaService;
import com.co.jv.service.UsuarioService;
import com.co.jv.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class IndexRestController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private SalaService salaService;
    @Autowired
    private JWTUtil jwtUtil;

    private boolean validarToken(String token) {
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }

    @PostMapping(value = "login/post")
    @ResponseBody
    public String login(@RequestBody UsuarioLogin login) {
        Usuario usuarioLogeado = new Usuario();
        usuarioLogeado.setUsername(login.getUsername());
        usuarioLogeado.setPassword(login.getPassword());
        Usuario respuesta = usuarioService.obtenerUsuarioPorCredenciales(usuarioLogeado);
        if (respuesta != null) {
            String tokenJWT = jwtUtil.create(String.valueOf(usuarioLogeado.getId()), respuesta.getUsername());
            return tokenJWT + "~" + respuesta.getNombre() + "~" + respuesta.getApellido() + "~" + respuesta.getId();
        }
        return "FAIL";
    }

    @PostMapping(value = "/registro/post")
    public String registro(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellido(usuarioDTO.getApellido());
        usuario.setUsername(usuarioDTO.getUsername());
        var respuesta = usuarioService.obtenerUsuarioPorCorreo(usuario);
        if (respuesta == null) {
            Argon2 argor2 = Argon2Factory.create();
            String hash = argor2.hash(1, 1024, 1, usuarioDTO.getPassword());
            usuario.setPassword(hash);

            TipoUsuario tipoUsuario = new TipoUsuario();
            usuarioDTO.setTipo_usuario_id(1);
            tipoUsuario.setId(usuarioDTO.getTipo_usuario_id());
            usuario.setTipoUsuario(tipoUsuario);
            usuarioService.guardar(usuario);
            return "OK";
        }

        return "FAIL";

    }

    @PostMapping(value = "/registroAdmin/post")
    public String registroAdmin(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellido(usuarioDTO.getApellido());
        usuario.setUsername(usuarioDTO.getUsername());
        var respuesta = usuarioService.obtenerUsuarioPorCorreo(usuario);
        if (respuesta == null) {
            Argon2 argor2 = Argon2Factory.create();
            String hash = argor2.hash(1, 1024, 1, usuarioDTO.getPassword());
            usuario.setPassword(hash);

            TipoUsuario tipoUsuario = new TipoUsuario();
            usuarioDTO.setTipo_usuario_id(2);
            tipoUsuario.setId(usuarioDTO.getTipo_usuario_id());
            usuario.setTipoUsuario(tipoUsuario);
            usuarioService.guardar(usuario);
            return "OK";
        }

        return "FAIL";
    }

    @PostMapping("/sala/verificar")
    public String verificarEmailAdmin(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setUsername(usuarioDTO.getUsername());
        var respuesta = usuarioService.obtenerUsuarioPorCorreo(usuario);
        if (respuesta.getTipoUsuario().getId() == 1) {
            return "USER";
        } else if (respuesta == null) {
            return "ERROR";
        }
        return "OK";
    }

    @PostMapping(value = "/registroSala/post")
    public String crearSala(@RequestBody SalaDTO salaDTO, @RequestHeader(value = "Authorization") String token, Errors errores) {
        if (!validarToken(token)) {
            return "FAIL";
        }
        Sala sala = new Sala();
        sala.setNombre(salaDTO.getNombre());
        sala.setEstado("ACTIVO");
        sala.setId_admin(salaDTO.getId_admin());
        salaService.guardar(sala);
        if (errores.hasErrors()) {
            return "FAIL";
        }
        return "OK";
    }
    
    @PostMapping("/sala/verificarAdmin")
    public String verificarAdminSala(@RequestBody SalaDTO salaDTO, Errors errores){
        Sala sala = new Sala();
        sala.setId_admin(salaDTO.getId_admin());
        sala = salaService.obtenerIdAdminPorSala(sala);
        if (errores.hasErrors()) {
            return "ERROR";
        }if(sala!=null){
        return "OK";
        }
        return "FAIL";
    }
    
    @PostMapping("/sala/cerrarSala")
    public String cerrarAdminSala(@RequestBody SalaDTO salaDTO, Errors errores){
        Sala sala = new Sala();
        sala.setId(salaDTO.getId());
        var salaUpdate = salaService.modificarEstado(sala);
        if (errores.hasErrors()) {
            return "ERROR";
        }if(salaUpdate != "ERROR"){
        return "OK";
        }
        return "FAIL";
    }

}
