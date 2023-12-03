package com.syno.word.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class UserEntity {

    @Id
    private String username;
    private String password;
    private String[] roles;
}

