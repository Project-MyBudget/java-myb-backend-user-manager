package br.com.mybudget.usermanager.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.mybudget.usermanager.model.dto.UserRegisterResponseDTO;
import br.com.mybudget.usermanager.model.dto.UserDTO;
import br.com.mybudget.usermanager.model.entity.UserEntity;
import br.com.mybudget.usermanager.repository.UserRepository;
import br.com.mybudget.usermanager.service.UserEmploymentService;
import br.com.mybudget.usermanager.service.UserFamilyService;
import br.com.mybudget.usermanager.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserEmploymentService userEmploymentService;

	@Autowired
	private UserFamilyService userFamilyService;

	/**
	 * 
	 * This method will take the general registration that the user entered and will
	 * check if he filled in his family and employment details
	 * 
	 * @return retun list the response request register data user
	 */
	@Override
	@Transactional(rollbackOn = Exception.class)
	public ResponseEntity<UserRegisterResponseDTO> registerUser(UserDTO requestRegisterUser) {
		try {
			Long userFamilyId = null;
			Long userEmploymentId = null;

			UserRegisterResponseDTO response = new UserRegisterResponseDTO();

			UserEntity userEntity = convertToEntity(requestRegisterUser);

			userEntity = userRepository.saveAndFlush(userEntity);

			if (userEntity != null && userEntity.getId() > 0) {
				if (requestRegisterUser.getEmployment() != null) {
					response = userFamilyService.registerUserFamily(requestRegisterUser, userEntity);
					userFamilyId = response.getUserFamilyId();
				}

				if (requestRegisterUser.getEmployment() != null) {
					response = userEmploymentService.registerUserEmployment(requestRegisterUser, userEntity);
					userEmploymentId = response.getUserEmploymentId();
				}

				log.info("[INFO] User register Sucess - [ID]: " + userEntity.getId());
				response = new UserRegisterResponseDTO(201, "Usuario registrado com sucesso!", userEntity.getId(),
						userFamilyId, userEmploymentId);
				return ResponseEntity.status(HttpStatus.CREATED).body(response);
			}

			log.error("[ERROR] Error in register user");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UserRegisterResponseDTO(500,
					"Não foi possivel registrar o usuario", userEntity.getId(), userFamilyId, userEmploymentId));

		} catch (Exception ex) {
			log.error("[ERROR] Error in register user - " + ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new UserRegisterResponseDTO(500, "Não foi possivel registrar o usuario", null, null, null));
		}
	}

	/**
	 * 
	 * Find user by id
	 * 
	 * @return {@link UserEntity}
	 */
	@Override
	public UserEntity findByIdUser(long id) {
		try {
			Optional<UserEntity> userEntity = userRepository.findById(id);

			if (userEntity != null) {
				log.info("[INFO] Sucess find user by id [ID]: " + id);
				return userEntity.get();
			}

			log.error("[ERROR] Error in find user by [ID]: " + id);
			return null;
		} catch (Exception ex) {
			log.error("[ERROR] Error in find user by [ID]: " + id + " : [ERROR] " + ex);
			return null;
		}
	}

	/**
	 * Convert UserDTO in User Entity
	 * 
	 * @param userDto
	 * @return
	 */
	private static UserEntity convertToEntity(UserDTO userDto) {
		return UserEntity.builder().firstName(userDto.getFirstName()).lastName(userDto.getLastName())
				.dateOfBirth(userDto.getDateOfBirth()).gender(userDto.getGender())
				.phoneNumber(userDto.getPhoneNumber()).email(userDto.getEmail())
				.status(userDto.getStatus()).password(userDto.getPassword()).build();
	}
}
