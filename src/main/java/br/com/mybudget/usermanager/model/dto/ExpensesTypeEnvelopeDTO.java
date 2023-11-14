package br.com.mybudget.usermanager.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpensesTypeEnvelopeDTO {
    private List<ExpenseTypeResponseDTO> expenses;
}
