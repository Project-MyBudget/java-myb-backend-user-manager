package br.com.mybudget.usermanager.model.dto;

import java.sql.Date;

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
public class UserDTO {

	private long idUser;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private String gender;
	private int childrenNumber;
	private String phoneNumber;
	private String email;
	private char status;
	private String password;
	private boolean isUpdatePassword;
	private UserMaritalStatusEnum civilStatus;
	private UserEmploymentRequestDTO employment;
}
