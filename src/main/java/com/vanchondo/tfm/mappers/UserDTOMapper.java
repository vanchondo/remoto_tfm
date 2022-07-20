package com.vanchondo.tfm.mappers;

import com.vanchondo.tfm.dtos.users.UserDTO;
import com.vanchondo.tfm.entities.UserEntity;

public class UserDTOMapper {

    public static UserDTO map(UserEntity entity){
        return new UserDTO(entity.getUsername(), entity.getEmail());
    }
}
