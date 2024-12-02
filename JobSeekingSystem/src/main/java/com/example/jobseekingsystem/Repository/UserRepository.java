package com.example.jobseekingsystem.Repository;

import com.example.jobseekingsystem.Model.TheUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<TheUser, Integer> {
}
