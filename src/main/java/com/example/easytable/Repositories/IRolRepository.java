package com.example.easytable.Repositories;

import com.example.easytable.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRolRepository extends JpaRepository<Role,Integer> {
}
