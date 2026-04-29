package com.example.easytable.Repositories;

import com.example.easytable.Entities.Favorite;
import com.example.easytable.Entities.FavoriteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFavoriteRepository extends JpaRepository<Favorite, FavoriteId> {
    @Query("SELECT f FROM Favorite f WHERE f.usuario.idUsuario = :idUsuario")
    List<Favorite> findByUsuario(@Param("idUsuario") int idUsuario);
}
