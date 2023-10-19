package br.com.mybudget.usermanager.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class UserEmploymentRequestDTO {

	private long idEmployment;	
	private double salary;
	private String jobName;
	private UserDTO user;
	private Date workStartDate;
}
