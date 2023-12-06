package br.com.mybudget.usermanager.repository;

import br.com.mybudget.usermanager.model.entity.BudgetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.transaction.Transactional;

@Repository
public interface BudgetRepository extends JpaRepository<BudgetEntity, Long> {
    @Query(value = "SELECT * FROM TB_BUDGET budget WHERE budget.ID_USER = ?1", nativeQuery = true)
    List<BudgetEntity> findEconomiesByUserId(Long idUser);
    
	@Modifying
	@Transactional
	@Query(value = "UPDATE TB_BUDGET SET VALUE_SAVED = ?2, BUDGET = 0.0 WHERE ID_USER = ?1", nativeQuery = true)
	int updateValueSaved(long idUser, double valueSaved);
	
}
