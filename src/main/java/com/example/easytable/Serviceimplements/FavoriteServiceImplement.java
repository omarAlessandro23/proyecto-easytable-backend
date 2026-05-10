package com.example.easytable.Serviceimplements;

import com.example.easytable.Entities.Favorite;
import com.example.easytable.Entities.FavoriteId;
import com.example.easytable.Entities.Restaurant;
import com.example.easytable.Repositories.IFavoriteRepository;
import com.example.easytable.Serviceinterfaces.IFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImplement implements IFavoriteService {
    @Autowired
    private IFavoriteRepository FR;

    @Override
    public List<Favorite> list() { return FR.findAll(); }

    @Override
    public void insert(Favorite favorite) { FR.save(favorite); }

    @Override
    public void delete(int id) { FR.deleteById(id);
    }

    @Override
    public void update(Favorite favorite) {

    }

    @Override
    public List<Object[]> countFavoritesByRestaurant() {
        return FR.findTopRestaurantsWithMostFavorites();
    }

    @Override
    public Favorite listId(int id) {
        return FR.findById(id).orElse(null);
    }


    @Override
    public List<Favorite> listByUser(int idUsuario) {
        return FR.findAllByUsuarioId(idUsuario);
    }


}
