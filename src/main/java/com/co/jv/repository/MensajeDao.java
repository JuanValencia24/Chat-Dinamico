package com.co.jv.repository;

import com.co.jv.entity.Mensaje;
import org.springframework.data.repository.CrudRepository;

public interface MensajeDao extends CrudRepository<Mensaje, Integer>{
    
}
