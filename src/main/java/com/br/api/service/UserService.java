package com.br.api.service;

import com.br.api.dto.request.UserRequestDTO;
import com.br.api.dto.response.UserResponseDTO;
import com.br.api.mapper.UserMapper;
import com.br.api.model.User;
import com.br.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public List<UserResponseDTO> getAll() {
        var users = userRepository.findAll();
        var response = new ArrayList<UserResponseDTO>();

        users.forEach(user -> response.add(UserMapper.entityToResponse(user)));

        return response;
    }

    public void save(UserRequestDTO userRequestDTO) {
        userRepository.save(UserMapper.requestToEntity(userRequestDTO));
    }

    public void update(Long id, UserRequestDTO userRequestDTO) {
        var user = getById(id);

        user.setEmail(userRequestDTO.getEmail());
        user.setName(userRequestDTO.getName());
        user.setProfile(userRequestDTO.getProfile());
        user.setPassword(userRequestDTO.getPassword());

        userRepository.save(user);
    }

    public void remove(Long id) {
        userRepository.deleteById(id);
    }
}