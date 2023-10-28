package br.com.mybudget.usermanager.repository;

import br.com.mybudget.usermanager.model.entity.BudgetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<BudgetEntity, Long> {
}
