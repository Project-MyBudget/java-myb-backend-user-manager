package br.com.mybudget.usermanager.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.mybudget.usermanager.model.dto.ResponseStatusLogDTO;
import br.com.mybudget.usermanager.model.dto.UserDTO;

@Service
public interface UserService {

	ResponseEntity<ResponseStatusLogDTO> registerUser(UserDTO requestRegisterUser);
}
