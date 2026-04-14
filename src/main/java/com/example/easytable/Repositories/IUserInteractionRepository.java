package com.example.easytable.Repositories;

import com.example.easytable.Entities.UserInteraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserInteractionRepository extends JpaRepository<UserInteraction, Integer>{

    List<UserInteraction> findByUserId(Integer userId);

    List<UserInteraction> findByRestaurantId(int restaurantId);

}
