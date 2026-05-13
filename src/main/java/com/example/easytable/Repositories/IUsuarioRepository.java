package com.example.easytable.Repositories;

import com.example.easytable.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario,Integer> {

    Usuario findByUsername(String username);
}
