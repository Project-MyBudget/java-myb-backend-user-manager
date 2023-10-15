package br.com.mybudget.usermanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mybudget.usermanager.model.dto.ApiResponseDTO;
import br.com.mybudget.usermanager.model.dto.UserDTO;
import br.com.mybudget.usermanager.model.dto.UserEmploymentRequestDTO;
import br.com.mybudget.usermanager.model.dto.UserFamilyRequestDTO;
import br.com.mybudget.usermanager.model.entity.UserEntity;
import br.com.mybudget.usermanager.service.UserEmploymentService;
import br.com.mybudget.usermanager.service.UserFamilyService;
import br.com.mybudget.usermanager.service.UserService;

@RestController
@RequestMapping("/mybudget")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserFamilyService userFamilyService;
	
	@Autowired
	private UserEmploymentService userEmploymentService;
	
	
	/**
	 * Register data user
	 * 
	 * @param requestRegisterUser
	 * @return
	 */
	@PostMapping(value = "/user/register", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ApiResponseDTO> register(@RequestBody UserDTO requestRegisterUser) {
		return userService.addUser(requestRegisterUser);
	}
	
	/**
	 * Register data user employment
	 * 
	 * @param requestRegisterUser
	 * @return
	 */
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
	
	/**
	 * Register data user family
	 * 
	 * @param requestRegisterUser
	 * @return
	 */
	@PostMapping(value = "/family/register/{userId}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ApiResponseDTO> addFamily(@RequestBody UserFamilyRequestDTO requestRegisterUser,
			@PathVariable long userId) {
		ApiResponseDTO response = userFamilyService.addFamily(requestRegisterUser,	userService.findByIdUser(userId));

		if (response != null) {
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.badRequest().body(
				new ApiResponseDTO(HttpStatus.BAD_REQUEST.name(), "Não foi possivel registrar os dados da familia"));
	}
	
	/**
	 * Tries to retrive a user from the database's application.
	 * 
	 * @author Marcos Vinicius
	 * @param PathVariable long id
	 * @return ResponseEntity<UserEntity>
	 */
	@GetMapping(value = "/user/id/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<UserEntity> getUserById(@PathVariable long id) {
		UserEntity response = userService.findByIdUser(id);
		
		if (response != null) {
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.notFound().build();
		//TODO: Implement Optional<UserEntity>.
	}
}
