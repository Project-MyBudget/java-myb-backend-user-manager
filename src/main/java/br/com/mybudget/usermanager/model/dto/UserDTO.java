package br.com.mybudget.usermanager.model.dto;

import java.sql.Date;

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

	private long id;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private String gender;
	private String phoneNumber;
	private String email;
	private char status;
	private String password;
	private UserEmploymentRequestDTO employment;
	private UserFamilyRequestDTO family;
}
