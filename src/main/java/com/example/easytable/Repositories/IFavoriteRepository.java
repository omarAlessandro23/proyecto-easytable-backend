package com.example.easytable.Repositories;

import com.example.easytable.Entities.Favorite;
import com.example.easytable.Entities.FavoriteId;
import com.example.easytable.Entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFavoriteRepository extends JpaRepository<Favorite, FavoriteId> {
    @Query("SELECT f FROM Favorite f WHERE f.usuario.idUsuario = :idUsuario")
    List<Favorite> findByUsuario(@Param("idUsuario") int idUsuario);
    @Query(value = "SELECT COUNT(*) > 0 FROM favorite WHERE id_usuario = :uId AND id_restaurant = :rId", nativeQuery = true)
    boolean isFavoriteNative(@Param("uId") int userId, @Param("rId") int resId);
    @Query(value = "SELECT r.* FROM restaurant r JOIN favorite f ON r.id_restaurant = f.id_restaurant WHERE f.id_usuario = :uId", nativeQuery = true)
    List<Restaurant> findFavoriteRestaurantsByUserNative(@Param("uId") int userId);
    @Query(value = "SELECT DISTINCT r.* FROM restaurant r " +
            "WHERE r.id_category IN (" +
            "    SELECT r2.id_category FROM restaurant r2 " +
            "    JOIN favorite f ON r2.id_restaurant = f.id_restaurant " +
            "    WHERE f.id_usuario = :uId" +
            ") " +
            "AND r.id_restaurant NOT IN (" +
            "    SELECT id_restaurant FROM favorite WHERE id_usuario = :uId" +
            ")", nativeQuery = true)
    List<Restaurant> findSuggestedRestaurantsNative(@Param("uId") int userId);
}
