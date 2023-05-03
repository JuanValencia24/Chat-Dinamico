package com.co.jv.service;

import com.co.jv.entity.Sala;
import java.util.List;


public interface SalaService {
    
    public List<Sala> getSalas();
    
    public void guardar(Sala sala);
    
    public Sala obtenerIdAdminPorSala(Sala sala);
    
    public String modificarEstado(Sala sala);
    
}
