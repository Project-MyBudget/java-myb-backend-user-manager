package br.com.mybudget.usermanager.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class BudgetRequestDTO {

	private int idUser;
	private double budget;
	private double valueSaved;
	private double spendingLimitEconomy;
	
}
