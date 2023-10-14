package br.com.mybudget.usermanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mybudget.usermanager.model.entity.UserEmploymentEntity;

@Repository
public interface UserEmploymentRepository extends JpaRepository<UserEmploymentEntity, Long>{

}
