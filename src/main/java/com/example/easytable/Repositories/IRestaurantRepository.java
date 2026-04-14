package com.example.easytable.Repositories;

import com.example.easytable.Entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IRestaurantRepository extends JpaRepository<Restaurant, Integer> {

}
