package br.com.mybudget.usermanager.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.mybudget.usermanager.model.dto.ApiResponseDTO;
import br.com.mybudget.usermanager.model.dto.UserBudgetEconomyRequestDTO;
import br.com.mybudget.usermanager.model.dto.UserDTO;
import br.com.mybudget.usermanager.model.entity.UserEntity;

@Service
public interface UserService {
	ResponseEntity<ApiResponseDTO> addUser(UserDTO requestRegisterUser);
	UserEntity findByIdUser(long id);
	ResponseEntity<ApiResponseDTO> updateBudgetAndEconomies(UserBudgetEconomyRequestDTO userBudgetEconomyRequestDTO);
}
