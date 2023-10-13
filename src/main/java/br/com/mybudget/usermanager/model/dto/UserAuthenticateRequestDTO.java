package br.com.mybudget.usermanager.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAuthenticateRequestDTO {
	private String email;
	private String password;
}