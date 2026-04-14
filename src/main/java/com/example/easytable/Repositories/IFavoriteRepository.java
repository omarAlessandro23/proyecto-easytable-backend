package com.example.easytable.Repositories;

import com.example.easytable.Entities.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFavoriteRepository extends JpaRepository<Favorite, Integer> {
}
