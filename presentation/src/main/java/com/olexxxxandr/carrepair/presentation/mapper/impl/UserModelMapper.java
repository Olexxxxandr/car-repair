package com.olexxxxandr.carrepair.presentation.mapper.impl;

import com.olexxxxandr.carrepair.domain.impl.User;
import com.olexxxxandr.carrepair.presentation.mapper.ModelMapper;
import com.olexxxxandr.carrepair.presentation.model.impl.UserModel;

public class UserModelMapper implements ModelMapper<User, UserModel> {

    @Override
    public UserModel toModel(User user) {
        return UserModel.builder()
                .id(user.getId())
                .email(user.getEmail())
                .login(user.getLogin())
                .password(user.getPassword())
                .build();
    }

    @Override
    public User toDomain(UserModel userModel) {
        return User.builder()
                .id(userModel.getId())
                .email(userModel.getEmail())
                .login(userModel.getLogin())
                .password(userModel.getPassword())
                .build();
    }
}
