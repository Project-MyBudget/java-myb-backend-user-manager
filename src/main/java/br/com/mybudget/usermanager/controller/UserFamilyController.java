package br.com.mybudget.usermanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mybudget.usermanager.model.dto.UserRegisterResponseDTO;
import br.com.mybudget.usermanager.model.dto.UserFamilyDTO;
import br.com.mybudget.usermanager.service.UserFamilyService;
import br.com.mybudget.usermanager.service.UserService;

@RestController
@RequestMapping("/mybudget")
public class UserFamilyController {

	@Autowired
	private UserFamilyService userFamilyService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * Register data user family
	 * 
	 * @param requestRegisterUser
	 * @return
	 */
	@PostMapping(value = "/family/register/{userId}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<UserRegisterResponseDTO> registerUserFamily(@RequestBody UserFamilyDTO requestRegisterUser, @PathVariable long userId) {
		return userFamilyService.registerUserFamily(requestRegisterUser, userService.findByIdUser(userId));
	}
}
