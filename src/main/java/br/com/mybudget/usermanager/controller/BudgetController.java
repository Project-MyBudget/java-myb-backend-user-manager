package br.com.mybudget.usermanager.controller;

import br.com.mybudget.usermanager.model.dto.ApiResponseDTO;
import br.com.mybudget.usermanager.model.dto.BudgetRequestDTO;
import br.com.mybudget.usermanager.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mybudget")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @PutMapping(value = "/budget/update")
    public ResponseEntity<ApiResponseDTO> updateBudget(@RequestBody BudgetRequestDTO request) throws Exception {
        return budgetService.saveBudget(request);
    }

}
