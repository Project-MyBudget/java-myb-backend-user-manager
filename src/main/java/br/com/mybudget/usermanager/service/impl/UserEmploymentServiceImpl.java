package br.com.mybudget.usermanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import br.com.mybudget.usermanager.model.dto.ApiResponseDTO;
import br.com.mybudget.usermanager.model.dto.UserEmploymentRequestDTO;
import br.com.mybudget.usermanager.model.entity.UserEmploymentEntity;
import br.com.mybudget.usermanager.model.entity.UserEntity;
import br.com.mybudget.usermanager.repository.UserEmploymentRepository;
import br.com.mybudget.usermanager.service.UserEmploymentService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserEmploymentServiceImpl implements UserEmploymentService {

	@Autowired
	private UserEmploymentRepository userEmploymentRepository;

	@Override
	public ApiResponseDTO addEmployment(UserEmploymentRequestDTO requestRegisterUserEmployment, UserEntity userEntity) {
		try {
			UserEmploymentEntity userEmploymentEntity = UserEmploymentEntity.builder()
					.user(userEntity)
					.jobName(requestRegisterUserEmployment.getJobName())
					.salary(requestRegisterUserEmployment.getSalary())
					.workStartDate(requestRegisterUserEmployment.getWorkStartDate())
					.build();

			log.info("[REGISTER EMPLOYMENT] Register Employment.");
			userEmploymentEntity = userEmploymentRepository.saveAndFlush(userEmploymentEntity);

			log.info("[INFO] User Employment register Success - [ID EMPLOYMENT]: {}", userEmploymentEntity.getIdEmployment());
			return new ApiResponseDTO(HttpStatus.CREATED.name(),
					"Caracteristicas do emprego do usuario registrado com sucesso!");
		} catch (Exception ex) {
			log.error("[ERROR] Error in register user employment - {}", ex.getMessage());
		}
		return null;
	}
}
