package com.co.jv.service;

import com.co.jv.entity.Mensaje;
import com.co.jv.repository.MensajeDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MensajeServiceImpl implements MensajeService{
    
    @Autowired
    private MensajeDao mensajeDao;
    
     @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public Mensaje guardar(Mensaje mensaje) {
        return mensajeDao.save(mensaje);
    }
}
