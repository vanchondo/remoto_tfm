package com.vanchondo.tfm.services;

import com.vanchondo.tfm.dtos.users.SaveUserDTO;
import com.vanchondo.tfm.dtos.users.UserDTO;
import com.vanchondo.tfm.entities.UserEntity;
import com.vanchondo.tfm.mappers.UserDTOMapper;
import com.vanchondo.tfm.mappers.UserEntityMapper;
import com.vanchondo.tfm.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO saveUser(SaveUserDTO dto){
        UserEntity entity = UserEntityMapper.map(dto);
        entity.setActive(false);
        entity.setDeleted(false);
        entity.setLastUpdatedAt(LocalDateTime.now());
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        // TODO checar que el email se unico

        entity = userRepository.save(entity);
        return UserDTOMapper.map(entity);
    }
}
