package br.com.mybudget.usermanager.service;

import br.com.mybudget.usermanager.model.dto.ApiResponseDTO;
import br.com.mybudget.usermanager.model.dto.BudgetRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface BudgetService {
    ResponseEntity<ApiResponseDTO> saveBudget(BudgetRequestDTO request) throws Exception;
}
