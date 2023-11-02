package br.com.mybudget.usermanager.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.mybudget.usermanager.model.dto.ApiResponseDTO;
import br.com.mybudget.usermanager.model.dto.ExpenseEnvelopeDTO;
import br.com.mybudget.usermanager.model.dto.UserExpensesEnvelopeResponseDTO;

@Service
public interface ExpenseService {
	
	ResponseEntity<ApiResponseDTO> saveOrUpdateExpense(ExpenseEnvelopeDTO expenseEnvelopeDTO);
	ResponseEntity<UserExpensesEnvelopeResponseDTO> findAllExpenseByIdUser(long idUser);
}
