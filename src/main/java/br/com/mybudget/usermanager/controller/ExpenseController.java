package br.com.mybudget.usermanager.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mybudget.usermanager.model.dto.ApiResponseDTO;
import br.com.mybudget.usermanager.model.dto.ExpenseEnvelopeDTO;
import br.com.mybudget.usermanager.service.ExpenseService;

@RestController
@RequestMapping("/mybudget")
public class ExpenseController {
	
	@Autowired
	private ExpenseService expenseService;
	 
	@PostMapping(value = "/expenses/register", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ApiResponseDTO> registerOrUpdateExpense(@RequestBody ExpenseEnvelopeDTO expenseEnvelopeDTO) {
		
		
		return expenseService.saveOrUpdateExpense(expenseEnvelopeDTO);
	}
}