package com.example.project.dao.model;

import lombok.*;

import java.sql.Date;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;

    private String userId;

    private String name;

    private String phone;

    private String password;

    private String createUser;

    private String modifiedUser;
    private String email;
    private Date createTime;
}
