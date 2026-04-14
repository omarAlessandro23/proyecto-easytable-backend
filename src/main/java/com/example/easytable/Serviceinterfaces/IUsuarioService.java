package com.example.easytable.Serviceinterfaces;

import com.example.easytable.Entities.Usuario;

import java.util.List;

public interface IUsuarioService {
    public List<Usuario> list();
    public void insert(Usuario usuario);
    public void delete(int id);
    public void update(Usuario usuario);
    public Usuario listId(int id);
}
