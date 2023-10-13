package br.com.mybudget.usermanager.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.mybudget.usermanager.model.dto.ResponseStatusLogDTO;
import br.com.mybudget.usermanager.model.dto.UserDTO;
import br.com.mybudget.usermanager.model.entity.UserEntity;

@Service
public interface UserService {
	ResponseEntity<List<ResponseStatusLogDTO>> registerUser(UserDTO requestRegisterUser);
	UserEntity findByIdUser(long id);
}
