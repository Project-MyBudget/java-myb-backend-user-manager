package br.com.mybudget.usermanager.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class BudgetRequestDTO {
	private long idUser;
	private long idBudget;
	private double budget;
	private double valueSaved;
	private double salary;
	private double spendingLimitEconomy;
	
}
