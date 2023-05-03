package com.co.jv.service;

import com.co.jv.entity.Sala;
import com.co.jv.repository.SalaDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SalaServiceImpl implements SalaService {

    @Autowired
    private SalaDao salaDao;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public void guardar(Sala sala) {
        salaDao.save(sala);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Sala> getSalas() {
        String query = "FROM Sala WHERE estado = :estado";
        List<Sala> lista = entityManager.createQuery(query)
                .setParameter("estado", "ACTIVO")
                .getResultList();
        if (lista.isEmpty()) {
            return null;
        }
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public Sala obtenerIdAdminPorSala(Sala sala) {
        String query = "FROM Sala WHERE id_admin = :id_admin";
        List<Sala> lista = entityManager.createQuery(query)
                .setParameter("id_admin", sala.getId_admin())
                .getResultList();
        if (lista.isEmpty()) {
            return null;
        }
        return lista.get(0);
    }

    @Override
    @Transactional
    public String modificarEstado(Sala sala) {
        Sala salaUpdate = entityManager.find(Sala.class, sala.getId());
        if (salaUpdate != null) {
            salaUpdate.setEstado("INACTIVO");
            entityManager.merge(salaUpdate);
            return "OK";
        }
        return "ERROR";
    }

}
