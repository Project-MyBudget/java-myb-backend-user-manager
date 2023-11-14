package br.com.mybudget.usermanager.service.impl;

import java.util.ArrayList;
import java.util.List;

import br.com.mybudget.usermanager.model.dto.*;
import br.com.mybudget.usermanager.repository.ExpenseTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

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

    @Autowired
    private ExpenseTypeRepository expenseTypeRepository;

    @Override
    public ResponseEntity<ApiResponseDTO> saveOrUpdateExpense(ExpenseEnvelopeDTO expenseEnvelopeDTO) {

        Long idUser = expenseEnvelopeDTO.getIdUser();

        for (ExpenseDTO expenseDTO : expenseEnvelopeDTO.getExpenses()) {
            Long idExpense = expenseRepository.findIdExpenseByIdUserAndIdExpenseType(idUser, expenseDTO.getId());

            ExpensesEntity expense = ExpensesEntity.builder().dateReference(expenseDTO.getDateReference())
                    .value(expenseDTO.getValue()).userEntity(UserEntity.builder().idUser(idUser).build())
                    .expenseType(ExpensesTypeEntity.builder().id(expenseDTO.getId()).build()).build();

            if (idExpense != null) {
                expense.setId(idExpense);
            }

            expenseRepository.saveAndFlush(expense);
            log.info("[SAVE EXPENSE] SAVING A NEW EXPENSE. ID_USER: {}  ", idUser);
        }

        return ResponseEntity.ok(new ApiResponseDTO("200", "Despesas atualizadas com sucesso!"));
    }

    @Override
    public ResponseEntity<UserExpensesEnvelopeResponseDTO> findAllExpenseByIdUser(long idUser) {
        log.info("[EXPENSES] Getting expenses to user id {} ", idUser);
        List<ExpensesEntity> expensesEntities = expenseRepository.findAllExpenseByIdUser(idUser);

        if (expensesEntities.size() == 0) {
            log.info("[EXPENSES] No founded expenses to user: {} ", idUser);
            return ResponseEntity.noContent().build();
        }

        double totalExpenses = 0;
        List<FullExpenseDTO> expenses = new ArrayList<>();

        for (ExpensesEntity expense : expensesEntities) {
            expenses.add(FullExpenseDTO
                    .builder()
                    .idExpenseType(expense.getExpenseType().getId())
                    .expenseDescription(expense.getExpenseType().getDescription())
                    .expenseType(expense.getExpenseType().getType())
                    .dateReference(expense.getDateReference())
                    .value(expense.getValue())
                    .idExpense(expense.getId())
                    .build()
            );

            totalExpenses += expense.getValue();
        }

        log.info("[EXPENSES] Expenses founded to user: {} ", expenses.toString());
        return ResponseEntity.ok(new UserExpensesEnvelopeResponseDTO(totalExpenses, expenses));
    }

    @Override
    public ExpensesTypeEnvelopeDTO getExpenses() {
        try {
            log.info("[FIND ALL EXPENSES] Finding all expenses types");
            List<ExpensesTypeEntity> expensesTypesEntities = expenseTypeRepository.findAll();
            if (!expensesTypesEntities.isEmpty()) {
                List<ExpenseTypeResponseDTO> response = new ArrayList<>();
                expensesTypesEntities.forEach(expenseType -> {
                    response.add(
                            ExpenseTypeResponseDTO.builder()
                                    .id(expenseType.getId())
                                    .description(expenseType.getDescription())
                                    .type(expenseType.getType())
                                    .build()
                    );
                });

                return new ExpensesTypeEnvelopeDTO(response);
            }

            log.info("[FIND ALL EXPENSES] Not found expenses");
        } catch (Exception ex) {
            log.error("[ERROR] Error to getting expenses {} ", ex.getMessage());
            throw ex;
        }

        return null;
    }
}
