package br.com.mybudget.usermanager.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GoalDTO {
	
	private Long idGoals;
	private String description;
	private String progress;
	private Date estimatedDate;
	private Long idUser;
}
