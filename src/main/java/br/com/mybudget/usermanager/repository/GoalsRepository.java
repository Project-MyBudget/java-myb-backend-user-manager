package br.com.mybudget.usermanager.repository;

import br.com.mybudget.usermanager.model.entity.GoalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalsRepository extends JpaRepository<GoalEntity, Long> {
    @Query(value = "SELECT * FROM TB_GOALS WHERE ID_USER = ?1", nativeQuery = true)
    List<GoalEntity> findAllByUserId(Long userId);
}
