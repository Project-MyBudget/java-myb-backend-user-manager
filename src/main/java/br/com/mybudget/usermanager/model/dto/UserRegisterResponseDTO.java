package br.com.mybudget.usermanager.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRegisterResponseDTO {

	private int status;
	private String message;
	private Long userId;
	private Long userFamilyId;
	private Long userEmploymentId;
}
