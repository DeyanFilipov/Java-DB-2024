package org.AutoMappingObjectsExercise.service;

import org.AutoMappingObjectsExercise.data.entities.User;
import org.AutoMappingObjectsExercise.service.dtos.UserLoginDTO;
import org.AutoMappingObjectsExercise.service.dtos.UserRegisterDTO;

public interface UserService {
    String registerUser(UserRegisterDTO userRegisterDTO);

    String loginUser(UserLoginDTO userLoginDTO);

    String logout();

    User getLoggedIn();
}
