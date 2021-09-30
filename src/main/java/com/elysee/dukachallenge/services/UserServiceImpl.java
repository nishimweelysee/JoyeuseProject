package com.elysee.dukachallenge.services;

import com.elysee.dukachallenge.Exceiptions.UserWithProvidedUsernameExistException;
import com.elysee.dukachallenge.domain.User;
import com.elysee.dukachallenge.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public User signUp(User user) {
        Optional<User> existingUser = repository.findDistinctByUsername(user.getUsername());
        if(existingUser.isPresent()){
            log.error("Attempting to create duplicate user with username: {}", existingUser.get().getUsername());
            throw new UserWithProvidedUsernameExistException("Provided username is already in use");
        }
        return repository.save(user);
    }
}
