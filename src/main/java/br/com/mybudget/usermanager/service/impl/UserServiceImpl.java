package br.com.mybudget.usermanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mybudget.usermanager.model.dto.UserDTO;
import br.com.mybudget.usermanager.model.entity.UserEntity;
import br.com.mybudget.usermanager.repository.impl.UserRepository;
import br.com.mybudget.usermanager.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;


	@Override
	public Long registerUser(UserDTO requestRegisterUser) {

		UserEntity userEntity = convertToEntity(requestRegisterUser);
		userEntity = userRepository.saveAndFlush(userEntity);

		if (userEntity != null && userEntity.getUserId() > 0) {
			log.info("[INFO] User register Sucess - [ID]: " + userEntity.getUserId());
			return userEntity.getUserId();
		}

		log.error("[ERROR] Error in register user");
		return null;
	}
	
	private static UserEntity convertToEntity(UserDTO userDto) {
		return new UserEntity(userDto.getUserId(), 
				userDto.getUserFirstName(), 
				userDto.getUserLastName(), 
				userDto.getUserCpf(), 
				userDto.getUserDateOfBirth(),
				userDto.getUserGender(),
				userDto.getUserPhoneNumber(),
				userDto.getUserEmail(),
				userDto.getUserStatus(),
				userDto.getUserPassword());
	}

}
