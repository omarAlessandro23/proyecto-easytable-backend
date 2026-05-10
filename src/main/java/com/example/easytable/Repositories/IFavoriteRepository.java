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
public interface IFavoriteRepository extends JpaRepository<Favorite, Integer> {
    // 1. Query en JPQL (Esta suele estar bien porque usa nombres de atributos Java)


    // 2. ¿Es favorito? (Nativa)
// Cambié id_usuario -> usuario_id e id_restaurant -> restaurant_id
    @Query(value = "SELECT COUNT(*) > 0 FROM favorite WHERE usuario_id = :uId AND restaurant_id = :rId", nativeQuery = true)
    boolean isFavoriteNative(@Param("uId") int userId, @Param("rId") int resId);

    @Query("SELECT f.restaurant, COUNT(f) AS total FROM Favorite f " +
            "GROUP BY f.restaurant " +
            "ORDER BY total DESC")
    List<Object[]> findTopRestaurantsWithMostFavorites();

    @Query("SELECT f FROM Favorite f WHERE f.usuario.idUsuario = :idUsuario")
    List<Favorite> findAllByUsuarioId(@Param("idUsuario") int idUsuario);

}
