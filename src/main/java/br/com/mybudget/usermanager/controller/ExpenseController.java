package br.com.mybudget.usermanager.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mybudget.usermanager.model.dto.ExpenseEnvelopeDTO;

@RestController
@RequestMapping("/mybudget")
public class ExpenseController {
	
	@PostMapping(value = "/expenses/register", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Void> registerOrUpdateExpense(@RequestBody ExpenseEnvelopeDTO expenseEnvelopeDTO) {
		
		Optional<ExpenseEnvelopeDTO> optional = Optional.ofNullable(expenseEnvelopeDTO);
		
		if (optional.isPresent()) {
			System.out.println("Está presente: " + optional.get());
		}
		System.out.println("Não está presente!");
		
		return null;
	}
}