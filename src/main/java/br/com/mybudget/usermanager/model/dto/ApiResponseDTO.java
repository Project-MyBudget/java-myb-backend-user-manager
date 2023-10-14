package br.com.mybudget.usermanager.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponseDTO {
	private String status;
	private String message;
}
