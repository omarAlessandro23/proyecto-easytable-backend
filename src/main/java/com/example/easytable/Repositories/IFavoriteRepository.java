package com.example.easytable.Repositories;

import com.example.easytable.Entities.Favorite;
import com.example.easytable.Entities.FavoriteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFavoriteRepository extends JpaRepository<Favorite, FavoriteId> {
}
