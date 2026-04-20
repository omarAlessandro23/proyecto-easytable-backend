package com.example.easytable.Serviceimplements;

import com.example.easytable.Entities.Favoritos;
import com.example.easytable.Repositories.IFavoritoRepository;
import com.example.easytable.Serviceinterfaces.IFavoritoService;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoritoServiceImplement implements IFavoritoService {
    @Autowired
    private IFavoritoRepository fr;
    @Override
    public List<Favoritos> list() {
        return fr.findAll();
    }

    @Override
    public void insert(Favoritos fav) {
        fr.save(fav);
    }

    @Override
    public void delete(int id) {
        fr.deleteById(id);
    }

    @Override
    public void update(Favoritos fav) {
        fr.save(fav);
    }

    @Override
    public Favoritos listId(int id) {
        return fr.findById(id).get();
    }
}
