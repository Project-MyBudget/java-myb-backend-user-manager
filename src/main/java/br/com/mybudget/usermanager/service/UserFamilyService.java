package br.com.mybudget.usermanager.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.mybudget.usermanager.model.dto.UserRegisterResponseDTO;
import br.com.mybudget.usermanager.model.dto.UserRequestDTO;
import br.com.mybudget.usermanager.model.dto.UserFamilyRequestDTO;
import br.com.mybudget.usermanager.model.entity.UserEntity;

@Service
public interface UserFamilyService {
	UserRegisterResponseDTO registerUserFamily(UserRequestDTO requestRegisterUserFamily, UserEntity userEntity);
	ResponseEntity<UserRegisterResponseDTO> registerUserFamily(UserFamilyRequestDTO requestRegisterUserFamily, UserEntity userEntity);
}
