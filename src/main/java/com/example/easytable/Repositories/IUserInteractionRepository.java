package com.example.easytable.Repositories;

import com.example.easytable.Entities.UserInteraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserInteractionRepository extends JpaRepository<UserInteraction, Integer> {
}
