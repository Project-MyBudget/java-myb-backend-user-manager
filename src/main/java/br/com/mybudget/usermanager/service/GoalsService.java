package br.com.mybudget.usermanager.service;

import br.com.mybudget.usermanager.model.dto.GoalDTO;
import br.com.mybudget.usermanager.model.dto.GoalsEnvelopeDTO;
import org.springframework.stereotype.Service;

@Service
public interface GoalsService {
    void createGoals(GoalsEnvelopeDTO request);
    void deleteGoal(Long goalId);
    GoalsEnvelopeDTO findAllGoals(Long userId);
}
