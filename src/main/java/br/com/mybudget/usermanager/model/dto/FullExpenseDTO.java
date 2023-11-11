package br.com.mybudget.usermanager.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FullExpenseDTO {
	private Long idExpense;
	private Long idExpenseType;
	private String expenseDescription;
	private String expenseType;
	private String dateReference;
	private double value;
}
