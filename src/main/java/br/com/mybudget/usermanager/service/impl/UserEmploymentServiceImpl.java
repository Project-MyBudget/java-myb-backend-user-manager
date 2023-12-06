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
	public ApiResponseDTO addEmployment(UserEmploymentRequestDTO request, UserEntity userEntity, boolean isUpdate) {
		try {
			UserEmploymentEntity userEmploymentEntity = UserEmploymentEntity.builder()
					.user(userEntity)
					.jobName(request.getJobName())
					.salary(request.getSalary())
					.workStartDate(request.getWorkStartDate())
					.build();

			if (isUpdate) {
				log.info("[UPDATE USER] Updating user in employment...");
				userEmploymentEntity.setIdEmployment(request.getIdEmployment());
			}

			log.info("[REGISTER EMPLOYMENT] Register Employment.");
			userEmploymentEntity = userEmploymentRepository.save(userEmploymentEntity);

			log.info("[INFO] User Employment register Success - [ID EMPLOYMENT]: {}", userEmploymentEntity.getIdEmployment());
			return new ApiResponseDTO(HttpStatus.CREATED.name(),
					"Caracteristicas do emprego do usuario registrado com sucesso!");
		} catch (Exception ex) {
			log.error("[ERROR] Error in register user employment - {}", ex.getMessage());
		}
		return null;
	}

	@Override
	public UserEmploymentRequestDTO findEmploymentByUser(Long userId) {
		try {
			UserEmploymentEntity employment = userEmploymentRepository.findEmploymentByUserId(userId);
			return UserEmploymentRequestDTO.builder()
					.jobName(employment.getJobName())
					.salary(employment.getSalary())
					.workStartDate(employment.getWorkStartDate())
					.idEmployment(employment.getIdEmployment())
					.build();
		} catch (Exception ex) {
			log.error("[ERROR] Error to getting employmento to user: {} ", ex.getMessage());
			log.error(ex.getMessage(), ex);
		}

		return null;
	}
}
