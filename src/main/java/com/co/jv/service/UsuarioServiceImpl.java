package com.co.jv.service;

import com.co.jv.entity.Usuario;
import com.co.jv.repository.UsuarioDao;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public void guardar(Usuario usuario) {
        usuarioDao.save(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario obtenerUsuarioPorCredenciales(Usuario usuario) {
        String query = "FROM Usuario WHERE username = :username";
        List<Usuario> lista = entityManager.createQuery(query)
                .setParameter("username", usuario.getUsername())
                .getResultList();
        if (lista.isEmpty()) {
            return null;
        }
        String passwordHashed = lista.get(0).getPassword();

        Argon2 argon2 = Argon2Factory.create();
        
        if (argon2.verify(passwordHashed, usuario.getPassword())) {
            return lista.get(0);
        }
        return null;

    }

    @Override
    @Transactional(readOnly = true)
    public Usuario obtenerUsuarioPorCorreo(Usuario usuario) {
        String query = "FROM Usuario WHERE username = :username";
        List<Usuario> lista = entityManager.createQuery(query)
                .setParameter("username", usuario.getUsername())
                .getResultList();
        if (lista.isEmpty()) {
            return null;
        }
        else{
            return lista.get(0);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> obtenerUsuarioPorId(Usuario usuario) {
        return usuarioDao.findById(usuario.getId());
    }

    

}
