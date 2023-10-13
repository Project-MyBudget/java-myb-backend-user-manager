package br.com.mybudget.usermanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.mybudget.usermanager.model.dto.UserAuthenticateRequestDTO;
import br.com.mybudget.usermanager.model.dto.UserAuthenticateResponseDTO;
import br.com.mybudget.usermanager.model.entity.UserEntity;
import br.com.mybudget.usermanager.repository.impl.UserRepository;
import br.com.mybudget.usermanager.service.AuthenticateUserService;
import br.com.mybudget.usermanager.service.CryptoDataService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AuthenticateUserServiceImpl implements AuthenticateUserService {

	@Autowired
	private CryptoDataService cryptoDataService;

	@Autowired
	private UserRepository repository;

	@Override
	public ResponseEntity<UserAuthenticateResponseDTO> authenticateUser(UserAuthenticateRequestDTO request) {
		try {
			log.info("[AUTH USER] Received request to authenticate user {} ", request.getEmail());
			UserEntity userEntity = repository.findByUserEmail(request.getEmail());
			
			if (userEntity == null) {
				log.info("[ERROR] Email not found in database.");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}

			boolean isValidaPwd = cryptoDataService.decryptData(userEntity.getUserPassword()).get(0) != null;
			
			if (isValidaPwd) {
				log.info("[ERROR] Password not valid.");
				return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(null);
			}

			log.info("[AUTH USER] User success authenticated.");
			
			UserAuthenticateResponseDTO response = UserAuthenticateResponseDTO.builder()
					.email(userEntity.getUserEmail()).id(userEntity.getUserId()).name(userEntity.getUserFirstName())
					.status(userEntity.getUserStatus()).build();
			return ResponseEntity.accepted().body(response);
		} catch (Exception e) {
			log.error("[ERROR] Generic error: {} ", e.getMessage());
			return ResponseEntity.badRequest().body(null);

		}
	}

}
