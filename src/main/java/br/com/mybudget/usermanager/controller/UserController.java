package br.com.mybudget.usermanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mybudget.usermanager.model.dto.ResponseStatusLogDTO;
import br.com.mybudget.usermanager.model.dto.UserDTO;
import br.com.mybudget.usermanager.service.UserService;

@RestController
@RequestMapping("/mybudget")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * Register user
	 * 
	 * @param requestRegisterUser
	 * @return
	 */
	@PostMapping(value = "/user/register", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ResponseStatusLogDTO> registerUser(@RequestBody UserDTO requestRegisterUser) {
		return userService.registerUser(requestRegisterUser);
	}
}
