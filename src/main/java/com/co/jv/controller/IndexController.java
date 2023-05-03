package com.co.jv.controller;

import com.co.jv.dtos.UsuarioDTO;
import com.co.jv.entity.Sala;
import com.co.jv.entity.TipoUsuario;
import com.co.jv.entity.Usuario;
import com.co.jv.service.SalaService;
import com.co.jv.service.UsuarioService;
import com.co.jv.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

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

    @GetMapping(value = {"/login","/"})
    public String formularioLogin(Model model) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        model.addAttribute("usuarioAuth", usuarioDTO);
        return "login";
    }

    @GetMapping("/index")
    public String chat() {
        return "index";
    }

    @GetMapping("/registro")
    public String formularioRegistro(Model model) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        model.addAttribute("usuario", usuarioDTO);
        return "register";
    }
    
    @GetMapping("/registroAdmin")
    public String formularioRegistroAdmin(Model model){
        UsuarioDTO ususrioDTO = new UsuarioDTO();
        model.addAttribute("usuarioAdmin", ususrioDTO);
        return "register_admin";
    }
    
    @GetMapping("/registroSala")
    public String crearSala(){
        return "crear_sala";
    }
    
    @GetMapping("/sala")
    public String salas(Model model){
        var salas = salaService.getSalas();
          model.addAttribute("salas", salas);
        return "sala";
    }
    

}
