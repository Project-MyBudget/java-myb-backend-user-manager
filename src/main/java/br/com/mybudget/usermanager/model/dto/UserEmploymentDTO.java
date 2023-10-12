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
public class UserEmploymentDTO {

	private long employmentId;	
	private double userSalary;
	private String userJobName;
	private UserDTO user;
}
