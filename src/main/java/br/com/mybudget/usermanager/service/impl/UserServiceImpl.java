package br.com.mybudget.usermanager.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.mybudget.usermanager.model.dto.UserRegisterResponseDTO;
import br.com.mybudget.usermanager.model.dto.UserRequestDTO;
import br.com.mybudget.usermanager.model.entity.UserEntity;
import br.com.mybudget.usermanager.repository.impl.UserRepository;
import br.com.mybudget.usermanager.service.UserEmploymentService;
import br.com.mybudget.usermanager.service.UserFamilyService;
import br.com.mybudget.usermanager.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserEmploymentService userEmploymentService;
	
	@Autowired
	private UserFamilyService userFamilyService;

	/**
	 * 
	 * This method will take the general registration that 
	 * the user entered and will check if he filled in his family 
	 * and employment details
	 * 
	 * @return retun list the response request register data user
	 */
	@Transactional(rollbackOn = Exception.class)
	@Override
	public ResponseEntity<List<UserRegisterResponseDTO>> registerUser(UserRequestDTO requestRegisterUser) {
		
		List<UserRegisterResponseDTO> responses = new ArrayList<UserRegisterResponseDTO>();
		
		try {
			UserEntity userEntity = convertToEntity(requestRegisterUser);

			userEntity = userRepository.saveAndFlush(userEntity);

			if (userEntity != null && userEntity.getUserId() > 0) {					
				if (requestRegisterUser.getUserFamily() != null) {
					responses.add(userFamilyService.registerUserFamily(requestRegisterUser, userEntity));
				}
				
				if(requestRegisterUser.getUserEmployment() != null) {
					responses.add(userEmploymentService.registerUserEmployment(requestRegisterUser, userEntity));
				}
				
				log.info("[INFO] User register Sucess - [ID]: " + userEntity.getUserId());
				responses.add(new UserRegisterResponseDTO(201, "Usuario registrado com sucesso!",userEntity));
				return ResponseEntity.status(HttpStatus.CREATED).body(responses);
			}
			
			log.error("[ERROR] Error in register user");
			responses.add(new UserRegisterResponseDTO(500, "Não foi possivel registrar o usuario", null));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responses);
			
		} catch (Exception ex) {
			log.error("[ERROR] Error in register user - " + ex);
			responses.add(new UserRegisterResponseDTO(500, "Não foi possivel registrar o usuario", null));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responses);
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
			
			if(userEntity != null) {
				log.info("[INFO] Sucess find user by id [ID]: " + id);
				return userEntity.get();
			}
			
			log.error("[ERROR] Error in find user by [ID]: " + id);
			return null;
		}catch(Exception ex) {
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
	private static UserEntity convertToEntity(UserRequestDTO userDto) {
		return UserEntity.builder()
				.userFirstName(userDto.getUserFirstName())
				.userLastName(userDto.getUserLastName())
				.userDateOfBirth(userDto.getUserDateOfBirth())
				.userGender(userDto.getUserGender())
				.userPhoneNumber(userDto.getUserPhoneNumber())
				.userEmail(userDto.getUserEmail())
				.userStatus(userDto.getUserStatus())
				.userPassword(userDto.getUserPassword())
				.build();
	}
}
