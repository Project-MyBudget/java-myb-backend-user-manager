package br.com.mybudget.usermanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mybudget.usermanager.model.dto.ResponseStatusLogDTO;
import br.com.mybudget.usermanager.model.dto.UserEmploymentDTO;
import br.com.mybudget.usermanager.service.UserEmploymentService;
import br.com.mybudget.usermanager.service.UserService;

@RestController
@RequestMapping("/mybudget")
public class UserEmploymentController {

	@Autowired
	private UserEmploymentService userEmploymentService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * Register data user employment
	 * 
	 * @param requestRegisterUser
	 * @return
	 */
	@PostMapping(value = "/employment/register/{userId}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ResponseStatusLogDTO> registerUserEmployment(@RequestBody UserEmploymentDTO requestRegisterUser, @PathVariable long userId) {
		return userEmploymentService.registerUserEmployment(requestRegisterUser, userService.findByIdUser(userId));
	}
}
