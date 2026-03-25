package com.example.usuarios.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.usuarios.dto.MessageResponseDto;
import com.example.usuarios.dto.UsersRequestDto;
import com.example.usuarios.dto.UsersResponseDto;
import com.example.usuarios.entity.Users;
import com.example.usuarios.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service // Crea un Bean (instancia)
@RequiredArgsConstructor
public class UsersService {

    // Inyeccion de dependencias
    private final UsersRepository usersRepository; // Inyectando al repository

    /**
     * Este metodo es para crear un usuario
     * 
     * @param request datos del usuario a crear
     * @return MessageResponseDTO objeto de respuesta que contiene un mensaje
     */
    public MessageResponseDto createUsers(UsersRequestDto request) {
        MessageResponseDto response = new MessageResponseDto();

        Users user = new Users();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        usersRepository.save(user);
        /*
         * INSERT INTO users (username, email) values (request.getUsername,
         * request.getEmail)
         */

        response.setMessage("Usuario creado correctamente");
        return response;
    }

    public List<UsersResponseDto> getUsers() {
        List<Users> usersFound = usersRepository.findAll();
        List<UsersResponseDto> response = new ArrayList<>();
        UsersResponseDto user = new UsersResponseDto();

        for (Users userFound : usersFound) {
            user.setId(userFound.getId());
            user.setUsername(userFound.getUsername());
            user.setEmail(userFound.getEmail());
            response.add(user);
        }

        return response;
    }

    public UsersResponseDto getUserById(Long id) {
        Users user = usersRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return new UsersResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail());
    }
}
