package com.co.jv.dtos;

import lombok.Data;

@Data
public class MensajeDTO {
    private String contenido;
    

    public MensajeDTO(String contenido) {
        this.contenido = contenido;
    }

    public MensajeDTO() {
    }

   
   
    
    
}
