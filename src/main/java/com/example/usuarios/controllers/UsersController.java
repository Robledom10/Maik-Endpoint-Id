package com.example.usuarios.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usuarios.dto.MessageResponseDto;
import com.example.usuarios.dto.UsersRequestDto;
import com.example.usuarios.dto.UsersResponseDto;
import com.example.usuarios.services.UsersService;

import lombok.RequiredArgsConstructor;

@RestController
// COntext path
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;

    @PostMapping
    public ResponseEntity<MessageResponseDto> createUser(@RequestBody UsersRequestDto usersRequestDto) {
        MessageResponseDto response = new MessageResponseDto();

        try {
            response = usersService.createUsers(usersRequestDto);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            response.setMessage("Hubo un error al crear el usuario");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<List<UsersResponseDto>> getUsers() {
        try {
            List<UsersResponseDto> response = usersService.getUsers();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersResponseDto> getUserById(@PathVariable Long id) {
        try {
            UsersResponseDto response = usersService.getUserById(id);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
@PutMapping("/{id}")
    public ResponseEntity<MessageResponseDto> updateUser(
            @PathVariable Long id,
            @RequestBody UsersRequestDto usersRequestDto) {

        MessageResponseDto response = new MessageResponseDto();

        try {
            response = usersService.updateUser(id, usersRequestDto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setMessage("Hubo un error al actualizar el usuario");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    // 🆕 ✅ Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponseDto> deleteUser(@PathVariable Long id) {

        MessageResponseDto response = new MessageResponseDto();

        try {
            response = usersService.deleteUser(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setMessage("Hubo un error al eliminar el usuario");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
