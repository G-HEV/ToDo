package com.ghev.ToDo.repository;

import com.ghev.ToDo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

    User findByusernameAndPassword(String username, String password);

    Optional<User> findByUsername(String username);

}
