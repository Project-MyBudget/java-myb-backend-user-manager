package br.com.mybudget.usermanager.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.mybudget.usermanager.model.dto.UserRegisterResponseDTO;
import br.com.mybudget.usermanager.model.dto.UserDTO;
import br.com.mybudget.usermanager.model.dto.UserEmploymentDTO;
import br.com.mybudget.usermanager.model.entity.UserEntity;

@Service
public interface UserEmploymentService {
	UserRegisterResponseDTO registerUserEmployment(UserDTO requestRegisterUserEmployment, UserEntity userEntity);
	ResponseEntity<UserRegisterResponseDTO> registerUserEmployment(UserEmploymentDTO requestRegisterUserEmployment, UserEntity userEntity);
}
