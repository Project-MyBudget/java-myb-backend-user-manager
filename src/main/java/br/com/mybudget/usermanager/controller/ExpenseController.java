package br.com.mybudget.usermanager.controller;

import br.com.mybudget.usermanager.error.ExpenseSalaryException;
import br.com.mybudget.usermanager.model.dto.ExpensesTypeEnvelopeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mybudget.usermanager.model.dto.ApiResponseDTO;
import br.com.mybudget.usermanager.model.dto.ExpenseEnvelopeDTO;
import br.com.mybudget.usermanager.model.dto.UserExpensesEnvelopeResponseDTO;
import br.com.mybudget.usermanager.service.ExpenseService;

@RestController
@RequestMapping("/mybudget")
public class ExpenseController {
	
	@Autowired
	private ExpenseService expenseService;
	 
	@PostMapping(value = "/expenses/register", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ApiResponseDTO> registerOrUpdateExpense(@RequestBody ExpenseEnvelopeDTO expenseEnvelopeDTO) {
		try {
			return expenseService.saveOrUpdateExpense(expenseEnvelopeDTO);
		} catch (ExpenseSalaryException ex) {
			return ex.getResponseEntity();
		}
	}
	
	@GetMapping(value = "/expenses/{idUser}", produces = "application/json")
	public ResponseEntity<UserExpensesEnvelopeResponseDTO> getAllExpensesByUserId(@PathVariable Long idUser) {
		return expenseService.findAllExpenseByIdUser(idUser);
	}

	@GetMapping(value = "/find-expenses", produces = "application/json")
	public ResponseEntity<ExpensesTypeEnvelopeDTO> getExpenses() {
		ExpensesTypeEnvelopeDTO response = expenseService.getExpenses();

		if (response == null) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(response);
	}
}