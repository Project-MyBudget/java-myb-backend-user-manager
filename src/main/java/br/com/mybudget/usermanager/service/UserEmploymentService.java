package br.com.mybudget.usermanager.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.mybudget.usermanager.model.dto.UserRegisterResponseDTO;
import br.com.mybudget.usermanager.model.dto.UserRequestDTO;
import br.com.mybudget.usermanager.model.dto.UserEmploymentRequestDTO;
import br.com.mybudget.usermanager.model.entity.UserEntity;

@Service
public interface UserEmploymentService {
	UserRegisterResponseDTO registerUserEmployment(UserRequestDTO requestRegisterUserEmployment, UserEntity userEntity);
	ResponseEntity<UserRegisterResponseDTO> registerUserEmployment(UserEmploymentRequestDTO requestRegisterUserEmployment, UserEntity userEntity);
}
