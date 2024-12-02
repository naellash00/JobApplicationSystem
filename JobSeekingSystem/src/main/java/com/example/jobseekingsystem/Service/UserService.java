package com.example.jobseekingsystem.Service;

import com.example.jobseekingsystem.Model.TheUser;
import com.example.jobseekingsystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<TheUser> getAllUsers(){
        return userRepository.findAll();
    }

    public void addUser(TheUser user){
        userRepository.save(user);
    }

    public Boolean updateUser(Integer id, TheUser user){
        TheUser oldUser = userRepository.getById(id);
        if(oldUser==null){
            return false;
        }
        oldUser.setAge(user.getAge());
        oldUser.setRole(user.getRole());
        oldUser.setName(user.getName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        userRepository.save(oldUser);
        return true;
    }

    public Boolean deleteUser(Integer id){
        TheUser user = userRepository.getById(id);
        if(user==null){
            return false;
        }
        userRepository.delete(user);
        return true;
    }
}








