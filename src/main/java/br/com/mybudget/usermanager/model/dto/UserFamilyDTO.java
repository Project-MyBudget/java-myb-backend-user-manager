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
public class UserFamilyDTO {

	private long familyId;
	private UserDTO user;
	private int userChildrenNumber;
	private UserMaritalStatusEnum userCivilStatus;
	private double userFamilyIncome;
	
}
