package com.ghev.ToDo.service;


import com.ghev.ToDo.exception.UsernameAlreadyExist;
import com.ghev.ToDo.model.User;
import com.ghev.ToDo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    public final UserRepo userRepo;


    @Autowired
    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;

    }

    public Optional<User> getUserById(int id){

        return userRepo.findById(id);
    }

    public List<User> getUsers(){
        return userRepo.findAll();
    }

    public User addUser(User user) throws UsernameAlreadyExist, SQLException {

    if(checkIfUserExist(user.getUsername())){
        throw new UsernameAlreadyExist();
    }

    user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepo.save(user);
    }

    public void deleteUser(int id){
        userRepo.deleteById(id);
    }

    public User login(String username, String password){

        return userRepo.findByusernameAndPassword(username,password);

    }

    public boolean checkIfUserExist(String username){

        return userRepo.findByUsername(username) !=null ? false : true;
    }

}
