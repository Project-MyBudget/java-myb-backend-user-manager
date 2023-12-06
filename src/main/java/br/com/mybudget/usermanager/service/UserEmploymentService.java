package br.com.mybudget.usermanager.service;

import org.springframework.stereotype.Service;

import br.com.mybudget.usermanager.model.dto.ApiResponseDTO;
import br.com.mybudget.usermanager.model.dto.UserEmploymentRequestDTO;
import br.com.mybudget.usermanager.model.entity.UserEntity;

@Service
public interface UserEmploymentService {
	ApiResponseDTO addEmployment(UserEmploymentRequestDTO requestRegisterUserEmployment, UserEntity userEntity, boolean isUpdate);
	UserEmploymentRequestDTO findEmploymentByUser(Long userId);
}
