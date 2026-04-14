package com.example.easytable.Serviceimplements;

import com.example.easytable.Entities.Usuario;
import com.example.easytable.Repositories.IUsuarioRepository;
import com.example.easytable.Serviceinterfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImplement implements IUsuarioService {
    @Autowired
    private IUsuarioRepository UR;

    @Override
    public List<Usuario> list() {
        return UR.findAll();
    }

    @Override
    public void insert(Usuario usuario) {
        UR.save(usuario);
    }

    @Override
    public void delete(int id) {
        UR.deleteById(id);
    }

    @Override
    public void update(Usuario usuario) {
        UR.save(usuario);
    }

    @Override
    public Usuario listId(int id) {
        return UR.findById(id).get();
    }
}
