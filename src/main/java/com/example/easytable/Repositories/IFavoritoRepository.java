package com.example.easytable.Repositories;

import com.example.easytable.Entities.Favoritos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFavoritoRepository extends JpaRepository<Favoritos,Integer> {
}
