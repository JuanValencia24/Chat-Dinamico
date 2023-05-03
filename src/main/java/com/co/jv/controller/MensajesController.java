package com.co.jv.controller;

import com.co.jv.dtos.Contenido;
import com.co.jv.dtos.HelloMessage;
import com.co.jv.dtos.MensajeDTO;
import com.co.jv.dtos.UsuarioDTO;
import com.co.jv.entity.Mensaje;
import com.co.jv.entity.Sala;
import com.co.jv.entity.Sala_Has_Mensaje;
import com.co.jv.entity.Usuario;
import com.co.jv.service.MensajeService;
import com.co.jv.service.Sala_Has_MensajeService;
import com.co.jv.service.UsuarioService;
import com.co.jv.utils.JWTUtil;
import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.util.HtmlUtils;

@Controller
public class MensajesController {

    @Autowired
    private MensajeService MensajeService;

    @Autowired
    private Sala_Has_MensajeService SalaMensajeService;
    
    @Autowired
    private UsuarioService UsuarioService;

    @Autowired
    private JWTUtil jwtUtil;

    private boolean validarToken(String token) {
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Contenido contenido(@Payload HelloMessage message) throws Exception {
        Mensaje mensaje = new Mensaje();
        Usuario usuario = new Usuario();
        Sala_Has_Mensaje salaMensaje = new Sala_Has_Mensaje();
        Sala sala = new Sala();
        Thread.sleep(1000); // simulated delay
        String token = message.getToken();
        int idUsuario = Integer.parseInt(message.getId());
        int key = 1;
        if (!validarToken(token)) {
            key = 0;
            return new Contenido(key);
        }
        mensaje.setContenido(message.getName());
        usuario.setId(idUsuario);
        mensaje.setUsuario(usuario);
        mensaje = MensajeService.guardar(mensaje);
        sala.setId(Integer.parseInt(message.getSalaId()));
        salaMensaje.setMensaje(mensaje);
        salaMensaje.setSala(sala);
        salaMensaje.setFecha(LocalDateTime.now());
        SalaMensajeService.guardar(salaMensaje);
         var usuriodata = UsuarioService.obtenerUsuarioPorId(usuario);
        if(usuriodata.isPresent()){
            return new Contenido(key,message.getName(),usuriodata.get().getNombre(),usuriodata.get().getApellido());
        }  
        return new Contenido(key=0);
    }

}
