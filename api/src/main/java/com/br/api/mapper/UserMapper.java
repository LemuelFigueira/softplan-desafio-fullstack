package com.br.api.mapper;

import com.br.api.dto.request.UserRequestDTO;
import com.br.api.dto.response.UserResponseDTO;
import com.br.api.model.User;

public final class UserMapper {

    public static User requestToEntity(UserRequestDTO userRequestDTO) {
        return User.builder()
                .name(userRequestDTO.getName())
                .email(userRequestDTO.getEmail())
                .profile(userRequestDTO.getProfile()).build();
    }

    public static UserResponseDTO entityToResponse(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .profile(user.getProfile()).build();
    }
}
