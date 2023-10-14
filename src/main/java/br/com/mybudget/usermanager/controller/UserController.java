package br.com.mybudget.usermanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mybudget.usermanager.model.dto.UserRegisterResponseDTO;
import br.com.mybudget.usermanager.model.dto.UserRequestDTO;
import br.com.mybudget.usermanager.model.entity.UserEntity;
import br.com.mybudget.usermanager.service.UserService;

@RestController
@RequestMapping("/mybudget")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	/**
	 * Register data user
	 * 
	 * @param requestRegisterUser
	 * @return
	 */
	@PostMapping(value = "/user/register", consumes = "application/json", produces = "application/json")
	public ResponseEntity<UserRegisterResponseDTO> registerUser(@RequestBody UserRequestDTO requestRegisterUser) {
		return userService.registerUser(requestRegisterUser);
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
