package com.example.easytable.Serviceinterfaces;

import com.example.easytable.Entities.Role;

import java.util.List;

public interface IRolService {
    public List<Role> list();
    public void insert(Role rol);
    public void delete(int id);
    public void update(Role rol);
    public Role listId(int id);
}
