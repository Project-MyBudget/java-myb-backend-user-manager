package br.com.mybudget.usermanager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.mybudget.usermanager.model.dto.ApiResponseDTO;
import br.com.mybudget.usermanager.model.dto.ExpenseEnvelopeDTO;
import br.com.mybudget.usermanager.model.dto.ExpenseRequestDTO;
import br.com.mybudget.usermanager.model.dto.FullExpenseDTO;
import br.com.mybudget.usermanager.model.dto.UserExpensesEnvelopeResponseDTO;
import br.com.mybudget.usermanager.model.entity.ExpensesEntity;
import br.com.mybudget.usermanager.model.entity.ExpensesTypeEntity;
import br.com.mybudget.usermanager.model.entity.UserEntity;
import br.com.mybudget.usermanager.repository.ExpenseRepository;
import br.com.mybudget.usermanager.service.ExpenseService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
	private ExpenseRepository expenseRepository;

	@Override
	public ResponseEntity<ApiResponseDTO> saveOrUpdateExpense(ExpenseEnvelopeDTO expenseEnvelopeDTO) {

		Long idUser = expenseEnvelopeDTO.getIdUser();

		for (ExpenseRequestDTO expenseRequestDTO : expenseEnvelopeDTO.getExpenses()) {
			Long idExpense = expenseRepository.findIdExpenseByIdUserAndIdExpenseType(idUser, expenseRequestDTO.getId());

			ExpensesEntity expense = ExpensesEntity.builder().dateReference(expenseRequestDTO.getDateReference())
					.value(expenseRequestDTO.getValue()).userEntity(UserEntity.builder().idUser(idUser).build())
					.expenseType(ExpensesTypeEntity.builder().id(expenseRequestDTO.getId()).build()).build();
			if (idExpense != null) {
				expense.setId(idExpense);
			}
			expenseRepository.saveAndFlush(expense);

			log.info("[SAVE EXPENSE] SAVING A NEW EXPENSE. ID_USER = " + idUser);
		}
		return ResponseEntity.ok(new ApiResponseDTO("200", "Despesas atualizadas com sucesso!"));
	}

	@Override
	public ExpenseEnvelopeDTO findExpenseById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<UserExpensesEnvelopeResponseDTO> findAllExpenseByIdUser(long idUser) {
		
		List<ExpensesEntity> userExpensesEntities = expenseRepository.findAllExpenseByIdUser(idUser);
		List<FullExpenseDTO> userExpensesDTOs = new ArrayList<>();
		
		for (ExpensesEntity expenseEntity : userExpensesEntities) {
			userExpensesDTOs.add(FullExpenseDTO
									.builder()
										.idExpenseType(expenseEntity.getExpenseType().getId())
										.descriptionExpenseType(expenseEntity.getExpenseType().getDescription())
										.expenseType(expenseEntity.getExpenseType().getType())
										.expenseRequestDTO(ExpenseRequestDTO
																.builder()
																	.id(expenseEntity.getId())
																	.value(expenseEntity.getValue())
																	.dateReference(expenseEntity.getDateReference())
																	.build()
										).build()
			);
		}
		
		System.out.println("Teste");
		UserExpensesEnvelopeResponseDTO response = UserExpensesEnvelopeResponseDTO.builder().idUser(idUser).expenses(userExpensesDTOs).build();
		
		if (userExpensesEntities.size() == 0) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(response);
				
	}
	
	

}
