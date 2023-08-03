package com.facebook_clone.repository;

import com.facebook_clone.model.UsersModel;
import jdk.dynalink.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UsersModel, Integer> {
    Optional<UsersModel> findByLoginOrPassword(String login, String password);
}
