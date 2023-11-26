package br.com.mybudget.usermanager.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.mybudget.usermanager.model.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
	UserEntity findByEmail(String email);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE TB_BUDGET set BUDGET = ?1, SPENDING_LIMIT_ECONOMY = ?2 WHERE ID_USER = ?3", nativeQuery = true)
	int updateBudgetAndSpendingLimitEconomyByIdUser(double budget, double spendingLimitEconomy, long idUser);
}
