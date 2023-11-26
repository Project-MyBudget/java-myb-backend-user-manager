package br.com.mybudget.usermanager.controller;

import br.com.mybudget.usermanager.model.dto.ApiResponseDTO;
import br.com.mybudget.usermanager.model.dto.BudgetEnvelopeDTO;
import br.com.mybudget.usermanager.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mybudget")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @PutMapping(value = "/budget/update")
    public ResponseEntity<ApiResponseDTO> updateBudget(@RequestBody BudgetEnvelopeDTO request) throws Exception {
        return budgetService.saveBudget(request);
    }

    @GetMapping(value = "/budget/economies/{idUser}")
    public ResponseEntity<BudgetEnvelopeDTO> findEconomiesByUser(@PathVariable Long idUser) throws Exception {
        BudgetEnvelopeDTO response = budgetService.findEconomiesByUser(idUser);

        if (response.getIdBudget() > 0) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
