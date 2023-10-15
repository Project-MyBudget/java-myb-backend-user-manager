package br.com.mybudget.usermanager.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class UserEmploymentRequestDTO {

	private long id;	
	private double salary;
	private String jobName;
	private UserDTO user;
}
