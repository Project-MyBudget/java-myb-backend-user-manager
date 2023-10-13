package br.com.mybudget.usermanager.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.mybudget.usermanager.model.dto.UserAuthenticateRequestDTO;
import br.com.mybudget.usermanager.model.dto.UserAuthenticateResponseDTO;

@Service
public interface AuthenticateUserService {
	ResponseEntity<UserAuthenticateResponseDTO> authenticateUser(UserAuthenticateRequestDTO request);
}
