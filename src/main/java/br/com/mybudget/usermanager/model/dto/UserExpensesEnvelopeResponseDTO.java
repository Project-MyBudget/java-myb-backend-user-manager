package br.com.mybudget.usermanager.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserExpensesEnvelopeResponseDTO {
	private double totalExpenses;
	private List<FullExpenseDTO> expenses;

}
