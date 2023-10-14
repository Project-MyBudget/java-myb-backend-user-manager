package br.com.mybudget.usermanager.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.mybudget.usermanager.model.dto.UserRegisterResponseDTO;
import br.com.mybudget.usermanager.model.dto.UserRequestDTO;
import br.com.mybudget.usermanager.model.dto.UserEmploymentRequestDTO;
import br.com.mybudget.usermanager.model.entity.UserEmploymentEntity;
import br.com.mybudget.usermanager.model.entity.UserEntity;
import br.com.mybudget.usermanager.repository.impl.UserEmploymentRepository;
import br.com.mybudget.usermanager.service.UserEmploymentService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserEmploymentServiceImpl implements UserEmploymentService {

	@Autowired
	private UserEmploymentRepository userEmploymentRepository;

	/**
	 * 
	 * returns the response if the user's employments data was registered
	 * successfully
	 * 
	 * @return {@link UserRegisterResponseDTO}
	 */
	@Transactional(rollbackOn = Exception.class)
	@Override
	public UserRegisterResponseDTO registerUserEmployment(UserRequestDTO requestRegisterUserEmployment,
			UserEntity userEntity) {
		try {
			UserEmploymentEntity userEmploymentEntity = UserEmploymentEntity.builder().user(userEntity)
					.userJobName(requestRegisterUserEmployment.getUserEmployment().getUserJobName())
					.userSalary(requestRegisterUserEmployment.getUserEmployment().getUserSalary()).build();

			userEmploymentEntity = userEmploymentRepository.saveAndFlush(userEmploymentEntity);

			if (userEmploymentEntity != null) {
				log.info("[INFO] User Employment register Sucess - [ID USER EMPLOYMENT]: "
						+ userEmploymentEntity.getEmploymentId());
				return new UserRegisterResponseDTO(201, "Caracteristicas do emprego do usuario registrado com sucesso!",
						userEntity.getUserId(), null, userEmploymentEntity.getEmploymentId());
			}

			log.error("[ERROR] Error in register user employment");
			return new UserRegisterResponseDTO(500,
					"Não foi possivel registrar as caracteristicas do emprego do usuario", userEntity.getUserId(), null,
					null);

		} catch (Exception ex) {
			log.error("[ERROR] Error in register user employment - " + ex);
			return new UserRegisterResponseDTO(500,
					"Não foi possivel registrar as caracteristicas do emprego do usuario", userEntity.getUserId(), null,
					null);
		}
	}

	/**
	 * 
	 * returns the response if the user's employments data was registered
	 * successfully
	 * 
	 * @return {@link UserRegisterResponseDTO}
	 */
	@Override
	public ResponseEntity<UserRegisterResponseDTO> registerUserEmployment(
			UserEmploymentRequestDTO requestRegisterUserEmployment, UserEntity userEntity) {
		try {
			UserEmploymentEntity userEmploymentEntity = UserEmploymentEntity.builder().user(userEntity)
					.userJobName(requestRegisterUserEmployment.getUserJobName())
					.userSalary(requestRegisterUserEmployment.getUserSalary()).build();

			userEmploymentEntity = userEmploymentRepository.saveAndFlush(userEmploymentEntity);

			if (userEmploymentEntity != null) {
				log.info("[INFO] User Employment register Sucess - [ID USER EMPLOYMENT]: "
						+ userEmploymentEntity.getEmploymentId());
				return ResponseEntity.status(HttpStatus.CREATED)
						.body(new UserRegisterResponseDTO(201,
								"Caracteristicas do emprego do usuario registrado com sucesso!", userEntity.getUserId(),
								null, userEmploymentEntity.getEmploymentId()));
			}

			log.error("[ERROR] Error in register user employment");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new UserRegisterResponseDTO(500,
							"Não foi possivel registrar as caracteristicas do emprego do usuario",
							userEntity.getUserId(), null, null));

		} catch (Exception ex) {
			log.error("[ERROR] Error in register user employment - " + ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new UserRegisterResponseDTO(500,
							"Não foi possivel registrar as caracteristicas do emprego do usuario",
							userEntity.getUserId(), null, null));
		}
	}
}
