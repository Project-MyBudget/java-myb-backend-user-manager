package br.com.mybudget.usermanager.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserBudgetEconomyRequestDTO {
	private Long idUser;
	private Double budget;
	private Double spendingLimitEconomy;
}
