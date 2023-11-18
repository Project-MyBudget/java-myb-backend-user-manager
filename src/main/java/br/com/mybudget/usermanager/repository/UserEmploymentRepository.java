package br.com.mybudget.usermanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.mybudget.usermanager.model.entity.UserEmploymentEntity;

@Repository
public interface UserEmploymentRepository extends JpaRepository<UserEmploymentEntity, Long>{

    @Query(value = "SELECT emp.SALARY FROM TB_EMPLOYMENTS emp WHERE emp.ID_USER = ?1", nativeQuery = true)
    double findSalaryByUserId(Long userId);

}
