package com.co.jv.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;


@Entity
@Data
@Table(name = "salas_has_mensajes")
public class Sala_Has_Mensaje {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private LocalDateTime fecha;
    
    @ManyToOne
    @JoinColumn(name = "salas_id")
    private Sala sala;
    
    @ManyToOne
    @JoinColumn(name = "mensajes_id")
    private Mensaje mensaje;
    
}
