package br.com.mybudget.usermanager.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.mybudget.usermanager.model.dto.ApiResponseDTO;
import br.com.mybudget.usermanager.model.dto.GoalDTO;

public interface GoalService {
	
	ResponseEntity<List<GoalDTO>> getAllGoalsByIdUser(long idUser);
	
	ResponseEntity<ApiResponseDTO> saveGoals(List<GoalDTO> goalDTO);
	
	ResponseEntity<ApiResponseDTO> updateGoal(GoalDTO goalDTO);
	
}
