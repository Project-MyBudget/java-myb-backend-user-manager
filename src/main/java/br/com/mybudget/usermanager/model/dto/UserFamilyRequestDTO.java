package br.com.mybudget.usermanager.model.dto;

import br.com.mybudget.usermanager.enums.UserMaritalStatusEnum;
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
public class UserFamilyRequestDTO {

	private long familyId;
	private int userChildrenNumber;
	private UserMaritalStatusEnum userCivilStatus;
	private double userFamilyIncome;
	private UserDTO user;
	
}
