package com.br.api.mapper;

import java.util.Map;

import com.br.api.dto.request.UserRequestDTO;
import com.br.api.dto.response.UserPageableResponseDTO;
import com.br.api.dto.response.UserResponseDTO;
import com.br.api.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    public static UserPageableResponseDTO pageableMapToResonse(Map<String, Object> map) {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.convertValue(map, UserPageableResponseDTO.class);
    }
}
