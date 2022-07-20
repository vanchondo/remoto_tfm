package com.vanchondo.tfm.repositories;

import com.vanchondo.tfm.entities.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, String> {
}
