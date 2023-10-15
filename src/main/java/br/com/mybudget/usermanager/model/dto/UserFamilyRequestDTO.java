package br.com.mybudget.usermanager.model.dto;

import br.com.mybudget.usermanager.enums.UserMaritalStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class UserFamilyRequestDTO {

	private long familyId;
	private int childrenNumber;
	private UserMaritalStatusEnum civilStatus;
	private double familyIncome;
	private UserDTO user;
	
}
