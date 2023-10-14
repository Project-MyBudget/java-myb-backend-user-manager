package br.com.mybudget.usermanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mybudget.usermanager.model.dto.UserAuthenticateRequestDTO;
import br.com.mybudget.usermanager.model.dto.UserAuthenticateResponseDTO;
import br.com.mybudget.usermanager.service.AuthenticateUserService;

// Controller for testing new functionalities (Exclude after integration modules)

@RestController
@RequestMapping("/test")
public class ControllerTesting {

	@Autowired
	private AuthenticateUserService service;

	@PostMapping(value = "/user/login", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> registerUser(@RequestBody UserAuthenticateRequestDTO request) {
		return service.authenticateUser(request);
	}

}
