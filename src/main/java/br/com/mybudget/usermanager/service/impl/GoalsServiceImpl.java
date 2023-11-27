package br.com.mybudget.usermanager.service.impl;

import br.com.mybudget.usermanager.error.ApiResponseException;
import br.com.mybudget.usermanager.model.dto.GoalDTO;
import br.com.mybudget.usermanager.model.dto.GoalsEnvelopeDTO;
import br.com.mybudget.usermanager.model.entity.GoalEntity;
import br.com.mybudget.usermanager.model.entity.UserEntity;
import br.com.mybudget.usermanager.repository.GoalsRepository;
import br.com.mybudget.usermanager.service.GoalsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class GoalsServiceImpl implements GoalsService {

    @Autowired
    private GoalsRepository repository;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void createGoals(GoalsEnvelopeDTO request) {
        try {
            for (GoalDTO goal : request.getGoals()) {
                log.info("[GOAL] Creating goal {} ", goal.getDescription());
                GoalEntity goalEntity = GoalEntity.builder()
                        .idGoal(goal.getGoalId())
                        .progress(goal.getProgress())
                        .description(goal.getDescription())
                        .estimatedDate(goal.getEstimatedDate())
                        .user(UserEntity.builder().idUser(goal.getUserId()).build())
                        .build();
                repository.saveAndFlush(goalEntity);
            }

        } catch (Exception ex) {
            log.error("[ERROR] Error to creating new goals to user. More details: {} ",  ex.getMessage());
            log.error(ex.getMessage(), ex);
            throw new ApiResponseException(HttpStatus.BAD_REQUEST, "Erro ao criar metas.");
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void deleteGoal(Long goalId) {
        try {
            log.info("[GOAL] Deleting goal {} ", goalId);
            if (goalId <= 0) {
                log.info("[GOAL] Goal id not exists!");
                throw new RuntimeException("Goal id is zero. Not exists in database");
            }

            repository.deleteById(goalId);
        } catch (Exception ex) {
            log.error("[ERROR] Error to deleting new goals: {}. More details: {} ", goalId, ex.getMessage());
            log.error(ex.getMessage(), ex);
            throw new ApiResponseException(HttpStatus.BAD_REQUEST, "Erro ao deletar meta.");
        }
    }

    @Override
    public GoalsEnvelopeDTO findAllGoals(Long userId) {
        GoalsEnvelopeDTO response = new GoalsEnvelopeDTO();
        response.setGoals(new ArrayList<>());
        try {
            log.info("[GOALS] Getting goals to user {} ", userId);
            List<GoalEntity> goals = repository.findAllByUserId(userId);
            for (GoalEntity goal : goals) {
                response.getGoals().add(
                        GoalDTO.builder()
                                .goalId(goal.getIdGoal())
                                .description(goal.getDescription())
                                .userId(goal.getUser().getIdUser())
                                .estimatedDate(goal.getEstimatedDate())
                                .progress(goal.getProgress())
                                .build()
                );
            }
        } catch (Exception ex) {
            log.error("[ERROR] Error to find all goals to user: {} ", userId);
            log.error(ex.getMessage(), ex);
        }
        log.info("[GOALS] Goals {} ", response);
        return response;
    }
}
