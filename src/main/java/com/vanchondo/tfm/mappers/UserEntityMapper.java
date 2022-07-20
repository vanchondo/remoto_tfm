package com.vanchondo.tfm.mappers;

import com.vanchondo.tfm.dtos.users.SaveUserDTO;
import com.vanchondo.tfm.entities.UserEntity;

public class UserEntityMapper {

    public static UserEntity map(SaveUserDTO dto){
        UserEntity entity = new UserEntity();
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());

        return entity;
    }

}
