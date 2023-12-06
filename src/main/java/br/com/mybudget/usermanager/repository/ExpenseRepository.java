package br.com.mybudget.usermanager.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.mybudget.usermanager.model.entity.ExpensesEntity;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpensesEntity, Long> {
	
	@Query(value = "SELECT exp.ID_EXPENSES, exp.VALUE FROM TB_EXPENSES exp WHERE exp.ID_USER = ?1 AND exp.ID_EXP_TYPE = ?2 AND exp.STATUS = 'A'", nativeQuery = true)
	List<Object[]> findIdExpenseByIdUserAndIdExpenseType(Long idUser, Long idExp);
	
	@Query(value = "SELECT * FROM TB_EXPENSES exp WHERE exp.ID_USER = ?1", nativeQuery = true)
	List<ExpensesEntity> findAllExpenseByIdUser(Long idUser);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE TB_EXPENSES SET STATUS = 'I' WHERE DATE_REFERENCE >= DATE_SUB(NOW(), INTERVAL 1 MONTH) AND STATUS <> 'I'", nativeQuery = true)
	int updateStatusExpenseLastMonth();
	
}
