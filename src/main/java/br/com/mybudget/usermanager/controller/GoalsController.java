package br.com.mybudget.usermanager.controller;

import br.com.mybudget.usermanager.error.ApiResponseException;
import br.com.mybudget.usermanager.model.dto.ApiResponseDTO;
import br.com.mybudget.usermanager.model.dto.GoalDTO;
import br.com.mybudget.usermanager.model.dto.GoalsEnvelopeDTO;
import br.com.mybudget.usermanager.service.GoalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mybudget")
public class GoalsController {

    @Autowired
    private GoalsService goalsService;

    @PostMapping(value = "/goals/save")
    public ResponseEntity<ApiResponseDTO> createGoals(@RequestBody GoalsEnvelopeDTO request) {
        try {
            goalsService.createGoals(request);
            return ResponseEntity.ok(new ApiResponseDTO(HttpStatus.CREATED.toString(), "Meta criada com sucesso."));
        } catch (ApiResponseException ex) {
            return ex.getResponseEntity();
        }
    }

    @DeleteMapping(value = "/goals/delete/{goalId}")
    public ResponseEntity<ApiResponseDTO> deleteGoal(@PathVariable Long goalId) {
        try {
            goalsService.deleteGoal(goalId);
            return ResponseEntity.ok(new ApiResponseDTO(HttpStatus.OK.toString(), "Despesa deletada com sucesso."));
        } catch (ApiResponseException ex) {
            return ex.getResponseEntity();
        }
    }

    @GetMapping(value = "/goals/{userId}")
    public ResponseEntity<GoalsEnvelopeDTO> findAllGoals(@PathVariable Long userId) {
        GoalsEnvelopeDTO response = goalsService.findAllGoals(userId);
        return ResponseEntity.ok(response);
    }
}
