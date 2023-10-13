package br.com.mybudget.usermanager.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.mybudget.usermanager.model.dto.UserRegisterResponseDTO;
import br.com.mybudget.usermanager.model.dto.UserDTO;
import br.com.mybudget.usermanager.model.dto.UserFamilyDTO;
import br.com.mybudget.usermanager.model.entity.UserEntity;

@Service
public interface UserFamilyService {
	UserRegisterResponseDTO registerUserFamily(UserDTO requestRegisterUserFamily, UserEntity userEntity);
	ResponseEntity<UserRegisterResponseDTO> registerUserFamily(UserFamilyDTO requestRegisterUserFamily, UserEntity userEntity);
}
