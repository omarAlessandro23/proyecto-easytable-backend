package com.example.easytable.Serviceimplements;

import com.example.easytable.Entities.Review;
import com.example.easytable.Repositories.IReviewRepository;
import com.example.easytable.Serviceinterfaces.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImplement implements IReviewService {
    @Autowired
    private IReviewRepository rR;

    @Override
    public List<Review> list() {
        return rR.findAll();
    }
    @Override
    public void insert(Review review){

        if(review.getRating() < 1 || review.getRating() > 5){
            throw new RuntimeException("El rating debe estar entre 1 y 5");
        }
        if(review.getCreatedAt() == null){
            review.setCreatedAt(LocalDateTime.now());
        }
        rR.save(review);

    }

    @Override
    public void delete(int id){
        rR.deleteById(id);
    }

    @Override
    public void update(Review review){
        if (review.getRating() < 1 || review.getRating() > 5){
            throw new RuntimeException("El rating debe estar entre 1 y 5");
        }
        rR.save(review);
    }
    @Override
    public Review listId(int id){
        return rR.findById(id).orElse(null);

    }
    @Override
    public  List<Review> findByUserId(int userId){
        return rR.findByUserId(userId);
    }
    @Override
    public List<Review> findByRestaurantId(int restaurantId){
        return rR.findByReviewId(restaurantId);
    }

    @Override
    public List<Object[]> recommendedRestaurantsByRating() {
        return rR.recommendedRestaurantsByRating();
    }
}
