package br.com.mybudget.usermanager.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserAuthenticateResponseDTO {
	private long id;
	private String name;
	private char status;
	private String email;
}
