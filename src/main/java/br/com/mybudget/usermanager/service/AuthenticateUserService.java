package br.com.mybudget.usermanager.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.mybudget.usermanager.model.dto.UserAuthenticateRequestDTO;

@Service
public interface AuthenticateUserService {
	ResponseEntity<Object> authenticateUser(UserAuthenticateRequestDTO request);
}
