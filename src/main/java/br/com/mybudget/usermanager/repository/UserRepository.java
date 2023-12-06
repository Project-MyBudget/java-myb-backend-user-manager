package br.com.mybudget.usermanager.repository;

import java.util.List;

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
	
	@Query(value = "SELECT u.ID_USER AS 'ID', (e.SALARY + b.BUDGET) - SUM(ex.VALUE) AS 'VALUE SAVED' FROM TB_USER u \r\n"
			+ "LEFT JOIN TB_EMPLOYMENTS e ON u.ID_USER = e.ID_USER \r\n"
			+ "LEFT JOIN TB_BUDGET b ON b.ID_USER = u.ID_USER\r\n"
			+ "LEFT JOIN TB_EXPENSES ex ON ex.ID_USER = u.ID_USER\r\n"
			+ "WHERE ex.DATE_REFERENCE >= DATE_SUB(NOW(), INTERVAL 1 MONTH) GROUP BY u.ID_USER", nativeQuery = true)
	List<Object[]> getValueSavedUsersLastMonth();
	
}
