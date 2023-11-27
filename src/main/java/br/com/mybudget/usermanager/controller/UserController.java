package br.com.mybudget.usermanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mybudget.usermanager.model.dto.ApiResponseDTO;
import br.com.mybudget.usermanager.model.dto.UserAuthenticateRequestDTO;
import br.com.mybudget.usermanager.model.dto.UserBudgetEconomyRequestDTO;
import br.com.mybudget.usermanager.model.dto.UserDTO;
import br.com.mybudget.usermanager.model.dto.UserEmploymentRequestDTO;
import br.com.mybudget.usermanager.model.entity.UserEntity;
import br.com.mybudget.usermanager.service.AuthenticateUserService;
import br.com.mybudget.usermanager.service.UserEmploymentService;
import br.com.mybudget.usermanager.service.UserService;

@RestController
@RequestMapping("/mybudget")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserEmploymentService userEmploymentService;
	
	@Autowired
	private AuthenticateUserService authenticateService;

	@PostMapping(value = "/user/register", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ApiResponseDTO> register(@RequestBody UserDTO requestRegisterUser) {
		return userService.addUser(requestRegisterUser);
	}

	@PostMapping(value = "/employment/register/{userId}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ApiResponseDTO> addEmployment(
			@RequestBody UserEmploymentRequestDTO requestRegisterUser, @PathVariable long userId) {

		ApiResponseDTO response = userEmploymentService.addEmployment(requestRegisterUser,	userService.findByIdUser(userId));

		if (response != null) {
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.badRequest().body(
				new ApiResponseDTO(HttpStatus.BAD_REQUEST.name(), "Não foi possivel registrar os dados do emprego"));
	}

	@GetMapping(value = "/user/id/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<UserEntity> getUserById(@PathVariable long id) {
		UserEntity response = userService.findByIdUser(id);
		
		if (response != null) {
			return ResponseEntity.ok(response);
		}

		return ResponseEntity.notFound().build();
	}
	
	@PostMapping(value = "/user/authenticate", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> authenticate(@RequestBody UserAuthenticateRequestDTO request) {
		return authenticateService.authenticateUser(request);
	}
	
	@PutMapping(value = "/user/economies", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ApiResponseDTO> updateBudgetEconomies(@RequestBody UserBudgetEconomyRequestDTO request) {
		
		return userService.updateBudgetAndEconomies(request);
	}
}
