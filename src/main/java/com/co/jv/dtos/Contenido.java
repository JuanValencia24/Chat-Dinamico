package com.co.jv.dtos;

import lombok.Data;


@Data
public class Contenido {
    private int key;
   private String content;
   private String nombre;
   private String apellido;

    public Contenido(int key) {
        this.key = key;
    }

    public Contenido(int key, String content, String nombre, String apellido) {
        this.key = key;
        this.content = content;
        this.nombre = nombre;
        this.apellido = apellido;
    }


   
}

