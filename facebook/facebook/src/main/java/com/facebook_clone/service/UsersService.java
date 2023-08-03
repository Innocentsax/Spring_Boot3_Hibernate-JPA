package com.facebook_clone.service;


import com.facebook_clone.model.UsersModel;
import org.springframework.stereotype.Service;
import com.facebook_clone.repository.UserRepository;

@Service
public class UsersService {

    private final UserRepository usersRepository;

    public UsersService(UserRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UsersModel registerUser(String login, String password, String email){
        if(login != null && password != null){
            UsersModel usersModel = new UsersModel();
            usersModel.setLogin(login);
            usersModel.setPassword(password);
            usersModel.setEmail(email);
            return usersRepository.save(usersModel);
        } else {
            return null;
        }
    };

    public UsersModel authenticate(String login, String password){
        return usersRepository.findByLoginOrPassword(login, password).orElse(null);
    }
}
