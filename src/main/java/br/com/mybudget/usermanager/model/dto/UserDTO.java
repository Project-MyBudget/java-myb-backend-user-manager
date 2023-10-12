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

	private long userId;
	private String userFirstName;
	private String userLastName;
	private String userCpf;
	private Date userDateOfBirth;
	private String userGender;
	private String userPhoneNumber;
	private String userEmail;
	private char userStatus;
	private String userPassword;
}
