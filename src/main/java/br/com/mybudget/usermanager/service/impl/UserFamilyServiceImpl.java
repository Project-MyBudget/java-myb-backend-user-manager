package br.com.mybudget.usermanager.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.mybudget.usermanager.model.dto.UserRegisterResponseDTO;
import br.com.mybudget.usermanager.model.dto.UserRequestDTO;
import br.com.mybudget.usermanager.model.dto.UserFamilyRequestDTO;
import br.com.mybudget.usermanager.model.entity.UserEntity;
import br.com.mybudget.usermanager.model.entity.UserFamilyEntity;
import br.com.mybudget.usermanager.repository.impl.UserFamilyRepository;
import br.com.mybudget.usermanager.service.UserFamilyService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserFamilyServiceImpl implements UserFamilyService {

	@Autowired
	private UserFamilyRepository userFamilyRepository;

	/**
	 * 
	 * returns the response if the user's family data was registered successfully
	 * 
	 * @return {@link UserRegisterResponseDTO}
	 */
	@Transactional(rollbackOn = Exception.class)
	@Override
	public UserRegisterResponseDTO registerUserFamily(UserRequestDTO requestRegisterUserFamily, UserEntity userEntity) {
		try {
			UserFamilyEntity userFamilyEntity = UserFamilyEntity.builder().user(userEntity)
					.userChildrenNumber(requestRegisterUserFamily.getUserFamily().getUserChildrenNumber())
					.userCivilStatus(
							requestRegisterUserFamily.getUserFamily().getUserCivilStatus().getMaritinalStatus())
					.userFamilyIncome(requestRegisterUserFamily.getUserFamily().getUserFamilyIncome()).build();

			userFamilyEntity = userFamilyRepository.saveAndFlush(userFamilyEntity);

			if (userFamilyEntity != null) {
				log.info("[INFO] User Family register Sucess - [ID USER FAMILY]: " + userFamilyEntity.getFamilyId());
				return new UserRegisterResponseDTO(201, "Caracteristicas da familia do usuario registrado com sucesso!",
						userEntity.getUserId(), userFamilyEntity.getFamilyId(), null);
			}

			log.error("[ERROR] Error in register user family");
			return new UserRegisterResponseDTO(500,
					"Não foi possivel registrar as caracteristicas da familia do usuario", userEntity.getUserId(), null,
					null);

		} catch (Exception ex) {
			log.error("[ERROR] Error in register user family - " + ex);
			return new UserRegisterResponseDTO(500,
					"Não foi possivel registrar as caracteristicas da familia do usuario", userEntity.getUserId(), null,
					null);
		}
	}

	/**
	 * 
	 * returns the response if the user's family data was registered successfully
	 * 
	 * @return {@link UserRegisterResponseDTO}
	 */
	@Override
	public ResponseEntity<UserRegisterResponseDTO> registerUserFamily(UserFamilyRequestDTO requestRegisterUserFamily,
			UserEntity userEntity) {
		try {
			UserFamilyEntity userFamilyEntity = UserFamilyEntity.builder().user(userEntity)
					.userChildrenNumber(requestRegisterUserFamily.getUserChildrenNumber())
					.userCivilStatus(requestRegisterUserFamily.getUserCivilStatus().getMaritinalStatus())
					.userFamilyIncome(requestRegisterUserFamily.getUserFamilyIncome()).build();

			userFamilyEntity = userFamilyRepository.saveAndFlush(userFamilyEntity);

			if (userFamilyEntity != null) {
				log.info("[INFO] User Family register Sucess - [ID USER FAMILY]: " + userFamilyEntity.getFamilyId());
				return ResponseEntity.status(HttpStatus.CREATED)
						.body(new UserRegisterResponseDTO(201,
								"Caracteristicas da familia do usuario registrado com sucesso!", userEntity.getUserId(),
								userFamilyEntity.getFamilyId(), null));
			}

			log.error("[ERROR] Error in register user family");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new UserRegisterResponseDTO(500,
							"Não foi possivel registrar as caracteristicas da familia do usuario",
							userEntity.getUserId(), null, null));

		} catch (Exception ex) {
			log.error("[ERROR] Error in register user family - " + ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new UserRegisterResponseDTO(500,
							"Não foi possivel registrar as caracteristicas da familia do usuario",
							userEntity.getUserId(), null, null));
		}
	}
}
