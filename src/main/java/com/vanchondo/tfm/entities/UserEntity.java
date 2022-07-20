package com.vanchondo.tfm.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("users")
@Data
public class UserEntity {
    @Id
    private String username;
    private String email;
    private String password;
    private boolean isDeleted;
    private boolean isActive;
    private LocalDateTime lastUpdatedAt;
}
