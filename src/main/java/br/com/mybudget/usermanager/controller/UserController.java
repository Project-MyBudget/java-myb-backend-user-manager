package br.com.mybudget.usermanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mybudget.usermanager.model.dto.ResponseStatusLogDTO;
import br.com.mybudget.usermanager.model.dto.UserDTO;
import br.com.mybudget.usermanager.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
		Long userId = userService.registerUser(requestRegisterUser);

		if (userId > 0) {
			log.info("[INFO] Success in registering user");
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new ResponseStatusLogDTO(201, "O cliente foi registrado com sucesso!"));
		}
		log.error("[ERROR] Error in registering user");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseStatusLogDTO(500, "Não foi possivel registrar o cliente"));
	}
}
