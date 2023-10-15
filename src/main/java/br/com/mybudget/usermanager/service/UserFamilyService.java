package br.com.mybudget.usermanager.service;

import org.springframework.stereotype.Service;

import br.com.mybudget.usermanager.model.dto.ApiResponseDTO;
import br.com.mybudget.usermanager.model.dto.UserFamilyRequestDTO;
import br.com.mybudget.usermanager.model.entity.UserEntity;

@Service
public interface UserFamilyService {
	ApiResponseDTO addFamily(UserFamilyRequestDTO requestRegisterUserFamily, UserEntity userEntity);
}
