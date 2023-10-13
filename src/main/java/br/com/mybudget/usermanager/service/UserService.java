package br.com.mybudget.usermanager.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.mybudget.usermanager.model.dto.UserRegisterResponseDTO;
import br.com.mybudget.usermanager.model.dto.UserRequestDTO;
import br.com.mybudget.usermanager.model.entity.UserEntity;

@Service
public interface UserService {
	ResponseEntity<List<UserRegisterResponseDTO>> registerUser(UserRequestDTO requestRegisterUser);
	UserEntity findByIdUser(long id);
}
