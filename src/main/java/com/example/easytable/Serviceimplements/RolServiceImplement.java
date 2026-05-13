package com.example.easytable.Serviceimplements;

import com.example.easytable.Entities.Role;
import com.example.easytable.Repositories.IRolRepository;
import com.example.easytable.Serviceinterfaces.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImplement implements IRolService {
   @Autowired //sirve para inyectar dependencias
   private IRolRepository RR;

   @Override
   public void insert(Role rol) {
       RR.save(rol);
   }

   @Override
   public List<Role> list() {
       return RR.findAll();
   }

   @Override
   public void delete(int id) {
       RR.deleteById(id);
   }

   @Override
   public Role listId(int id) {
       return RR.findById(id).orElse(null);
   }

   @Override
   public void update(Role rol) { RR.save(rol); }
}
