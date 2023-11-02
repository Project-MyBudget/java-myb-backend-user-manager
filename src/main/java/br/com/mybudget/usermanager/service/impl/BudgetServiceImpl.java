package br.com.mybudget.usermanager.service.impl;

import br.com.mybudget.usermanager.enums.UserMaritalStatusEnum;
import br.com.mybudget.usermanager.enums.UserVariableEconomyEnum;
import br.com.mybudget.usermanager.model.dto.ApiResponseDTO;
import br.com.mybudget.usermanager.model.dto.BudgetRequestDTO;
import br.com.mybudget.usermanager.model.entity.BudgetEntity;
import br.com.mybudget.usermanager.model.entity.UserEntity;
import br.com.mybudget.usermanager.repository.BudgetRepository;
import br.com.mybudget.usermanager.service.BudgetService;
import br.com.mybudget.usermanager.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class BudgetServiceImpl implements BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<ApiResponseDTO> saveBudget(BudgetRequestDTO request) throws Exception {
        try {
            log.info("[BUDGET] Saving changes for budget: {} ", request.getIdBudget());
            boolean isVerified = this.verifySpendingLimit(request.getIdUser(), request.getSalary(), request.getSpendingLimitEconomy());
            if (!isVerified) {
                return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                        .body(new ApiResponseDTO(HttpStatus.PRECONDITION_FAILED.toString(), "Nós não indicamos ter uma econômia comprometendo mais que o limite estabelecido para sua renda!"));
            }

            BudgetEntity budgetEntity = BudgetEntity
                    .builder()
                    .spendingLimitEconomy(request.getSpendingLimitEconomy())
                    .valueSaved(request.getValueSaved())
                    .id(request.getIdBudget())
                    .user(UserEntity.builder().idUser(request.getIdUser()).build())
                    .budget(request.getBudget())
                    .build();
            budgetRepository.saveAndFlush(budgetEntity);
            return  ResponseEntity.ok(new ApiResponseDTO(HttpStatus.OK.toString(), "Atualização realizada com suceso!"));
        } catch (Exception ex) {
            log.error("[BUDGET ERROR] Error to save new changes in Budget - ID BUDGET: {} ", request.getIdBudget());
            throw new Exception("Erro desconhecido, tente mais tarde!");
        }
    }

    private boolean verifySpendingLimit(long idUser, double salary, double spendingLimitEconomy) {
        log.info("[VERIFY SPENDING LIMIT] Validating spending limit economy ");
        UserEntity userEntity = userService.findByIdUser(idUser);
        boolean hasFamily = !Objects.equals(userEntity.getCivilStatus(), UserMaritalStatusEnum.SINGLE.getMaritalStatus());
        double maxEconomyPercent = UserVariableEconomyEnum.calcMaxEconomyValue(hasFamily, salary);
        if (salary > spendingLimitEconomy) {
            return (salary * maxEconomyPercent) >= spendingLimitEconomy;
        }

        log.info("[VERIFY SPENDING LIMIT - ERROR] Savings with spending limits are greater.");
        return false;
    }
}