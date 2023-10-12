package br.com.mybudget.usermanager.service;

import org.springframework.stereotype.Service;

import br.com.mybudget.usermanager.model.dto.UserDTO;

@Service
public interface UserService {

	Long registerUser(UserDTO requestRegisterUser);
}
