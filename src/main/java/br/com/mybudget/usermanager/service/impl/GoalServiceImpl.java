package br.com.mybudget.usermanager.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.mybudget.usermanager.model.dto.ApiResponseDTO;
import br.com.mybudget.usermanager.model.dto.GoalDTO;
import br.com.mybudget.usermanager.model.entity.GoalEntity;
import br.com.mybudget.usermanager.model.entity.UserEntity;
import br.com.mybudget.usermanager.repository.GoalRepository;
import br.com.mybudget.usermanager.service.GoalService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class GoalServiceImpl implements GoalService {
	
	@Autowired
	private GoalRepository goalRepository;
	
	@Override
	public ResponseEntity<List<GoalDTO>> getAllGoalsByIdUser(long id) {
		
		List<GoalEntity> goalsEntities = goalRepository.findAllGoalsById(id);
		List<GoalDTO> goalsDTOs = new ArrayList<>();
		
		if (goalsEntities.size() != 0) {
			for (GoalEntity goalEntity : goalsEntities) {
				goalsDTOs.add(GoalDTO
							.builder()
								.idGoals(goalEntity.getIdGoals())
								.description(goalEntity.getDescription())
								.progress(goalEntity.getProgress())
								.estimatedDate(goalEntity.getEstimatedDate())
							.build()
				);
			}
			
			log.info("[GOAL RESOURCE] Retrieving all the requested Goals.");
			return ResponseEntity.ok(goalsDTOs);
		} else {
			log.info("[GOAL RESOURCE] Could not find any Goal.");
			return ResponseEntity.notFound().build();
		}

	}

	@Override
	public ResponseEntity<ApiResponseDTO> saveGoals(List<GoalDTO> goalsDTO) {
		
		List<GoalEntity> goalsEntities = new LinkedList<>();
		
		for (GoalDTO currentGoalDTO : goalsDTO) {
			if (currentGoalDTO.getDescription() == null || currentGoalDTO.getProgress() == null) {
				continue;
			}
			
			GoalEntity goalEntity = GoalEntity
					.builder()
						.description(currentGoalDTO.getDescription())
						.progress(currentGoalDTO.getProgress())
						.estimatedDate(currentGoalDTO.getEstimatedDate())
						.userEntity(UserEntity.builder().idUser(currentGoalDTO.getIdUser()).build())
					.build();
			goalsEntities.add(goalEntity);
		}
		
		goalRepository.saveAllAndFlush(goalsEntities);
		
		log.info("[GOAL RESOURCE] The requested goals have been saved.");
		return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDTO("200", "Goals persistidas com sucesso!"));
	}

	@Override
	public ResponseEntity<ApiResponseDTO> updateGoal(GoalDTO goalDTO) {
		
		Optional<GoalEntity> optionalGoal = goalRepository.findById(goalDTO.getIdGoals());
		
		
		if (optionalGoal.isPresent()) {
			
			if (optionalGoal.get().getIdGoals() == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseDTO("400", "Meta está incorreta."));
			}
			
			GoalEntity goalEntity = GoalEntity
										.builder()
											.idGoals(goalDTO.getIdGoals())
											.description(goalDTO.getDescription())
											.progress(goalDTO.getProgress())
											.estimatedDate(goalDTO.getEstimatedDate())
											.userEntity(UserEntity.builder().idUser(goalDTO.getIdUser()).build())
										.build();
			
			log.info("[GOAL RESOURCE] The requested goal has been found and updated.");
			goalRepository.saveAndFlush(goalEntity);
			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseDTO("200", "Meta atualizada com sucesso."));
		} else {
			
			log.info("[GOAL RESOURCE] The requested goal has not been found.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseDTO("404", "Não foi possível localizar esta meta."));
		}
	}
}
