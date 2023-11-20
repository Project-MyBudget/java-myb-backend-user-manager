package br.com.mybudget.usermanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.mybudget.usermanager.model.entity.ExpensesEntity;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpensesEntity, Long> {
	
	@Query(value = "SELECT exp.ID_EXPENSES FROM TB_EXPENSES exp WHERE exp.ID_USER = ?1 AND exp.ID_EXP_TYPE = ?2 AND exp.STATUS = 'A'", nativeQuery = true)
	Long findIdExpenseByIdUserAndIdExpenseType(Long idUser, Long idExp);
	
	@Query(value = "SELECT * FROM TB_EXPENSES exp WHERE exp.ID_USER = ?1", nativeQuery = true)
	List<ExpensesEntity> findAllExpenseByIdUser(Long idUser);
	
}
