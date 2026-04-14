package com.example.easytable.Serviceimplements;

import com.example.easytable.Entities.Favorite;
import com.example.easytable.Entities.FavoriteId;
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
    public void delete(int idUsuario, int idRestaurant) {
        FavoriteId id = new FavoriteId(idUsuario, idRestaurant);
        FR.deleteById(id);
    }

    @Override
    public void update(Favorite favorite) { FR.save(favorite); }

    @Override
    public Favorite listId(int idUsuario, int idRestaurant) {
        FavoriteId id = new FavoriteId(idUsuario, idRestaurant);
        return FR.findById(id).orElse(new Favorite()); }
}
