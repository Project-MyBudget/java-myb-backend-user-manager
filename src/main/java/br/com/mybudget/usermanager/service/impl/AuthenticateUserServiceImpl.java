package br.com.mybudget.usermanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.mybudget.usermanager.model.dto.ApiResponseDTO;
import br.com.mybudget.usermanager.model.dto.UserAuthenticateRequestDTO;
import br.com.mybudget.usermanager.model.dto.UserAuthenticateResponseDTO;
import br.com.mybudget.usermanager.model.entity.UserEntity;
import br.com.mybudget.usermanager.repository.UserRepository;
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
	public ResponseEntity<Object> authenticateUser(UserAuthenticateRequestDTO request) {
		try {
			log.info("[AUTH USER] Received request to authenticate user {} ", request.getEmail());
			UserEntity userEntity = repository.findByEmail(request.getEmail());
 
			if (userEntity == null) {
				log.info("[ERROR] Email not found in database.");
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ApiResponseDTO(HttpStatus.NOT_FOUND.name(), "Usuário não encontrado!"));
			}

			boolean isValidaPwd = cryptoDataService.decryptData(userEntity.getPassword()).get(0) != null;

			if (isValidaPwd) {
				log.info("[ERROR] Password not valid.");
				return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(
						new ApiResponseDTO(HttpStatus.PRECONDITION_FAILED.name(), "Senha incorreta, tente novamente!"));
			}

			log.info("[AUTH USER] User success authenticated.");

			UserAuthenticateResponseDTO response = UserAuthenticateResponseDTO.builder().email(userEntity.getEmail())
					.id(userEntity.getId()).name(userEntity.getFirstName()).status(userEntity.getStatus()).build();
			return ResponseEntity.accepted().body(response);
		} catch (Exception e) {
			log.error("[ERROR] Generic error: {} ", e.getMessage());
			return ResponseEntity.badRequest()
					.body(new ApiResponseDTO(HttpStatus.BAD_REQUEST.name(), "Erro ao realizar autenticação."));

		}
	}

}
