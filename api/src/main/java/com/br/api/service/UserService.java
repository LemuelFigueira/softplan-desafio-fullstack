package com.br.api.service;

import com.br.api.dto.request.UserRequestDTO;
import com.br.api.dto.response.UserResponseDTO;
import com.br.api.mapper.UserMapper;
import com.br.api.model.User;
import com.br.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public Map<String, Object> getAll(int page, int size) {
        var pageRequest = PageRequest.of(page, size, Sort.by("id").ascending());
        var queryUsers = userRepository.findAll(pageRequest);

        var users = new ArrayList<UserResponseDTO>();

        queryUsers.getContent().forEach(user -> users.add(UserMapper.entityToResponse(user)));

        Map<String, Object> retorno = new HashMap<>();

        retorno.put("users", users);
        retorno.put("totalPages", queryUsers.getTotalPages());
        retorno.put("totalElements", queryUsers.getTotalElements());
        retorno.put("isLast", queryUsers.isLast());
        retorno.put("isFirst", queryUsers.isFirst());

        return retorno;

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
        var user = getById(id);
        var now = LocalDateTime.now();
        user.setDeletedAt(now);
        userRepository.save(user);
    }
}