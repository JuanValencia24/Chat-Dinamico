
package com.co.jv.service;

import com.co.jv.entity.Sala_Has_Mensaje;
import com.co.jv.repository.Sala_Has_MensajeDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Sala_Has_MensajeServiceImpl implements Sala_Has_MensajeService{
    
    @Autowired
    private Sala_Has_MensajeDao salaMensajeDao;
    
     @PersistenceContext
    EntityManager entityManager;

    @Override
    public void guardar(Sala_Has_Mensaje sala_Has_Mensaje) {
        salaMensajeDao.save(sala_Has_Mensaje);
    }
    
}
