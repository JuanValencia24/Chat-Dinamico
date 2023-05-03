package com.co.jv.entity;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.List;
import lombok.Data;

@Entity
@Data
@Table(name = "tipo_usuario")
public class TipoUsuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String tipo;
    
    //@OneToMany(mappedBy = "tipo_usuario")
    //private List<Usuario> usuario;
    
    
}
