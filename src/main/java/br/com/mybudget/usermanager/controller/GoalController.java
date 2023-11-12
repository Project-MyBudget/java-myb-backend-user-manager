package br.com.mybudget.usermanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mybudget.usermanager.model.dto.ApiResponseDTO;
import br.com.mybudget.usermanager.model.dto.GoalDTO;
import br.com.mybudget.usermanager.service.GoalService;

@RestController
@RequestMapping("/mybudget")
public class GoalController {
	
	@Autowired
	private GoalService goalService;
	
	@GetMapping(value="/goal/{idGoal}", produces = "application/json")
	public ResponseEntity<List<GoalDTO>> getGoalByUserId(@PathVariable long idGoal) {
		
		return goalService.getAllGoalsByIdUser(idGoal);	
	}
	
	@PostMapping(value="/goal", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ApiResponseDTO> saveGoals(@RequestBody List<GoalDTO> newGoals) {
		
		return goalService.saveGoals(newGoals);
	}
	
	@PutMapping(value="/goal", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ApiResponseDTO> updateGoal(@RequestBody GoalDTO goalDTO) {
	
		return goalService.updateGoal(goalDTO);
	}
	
}
