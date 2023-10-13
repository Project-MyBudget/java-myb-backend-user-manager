package br.com.mybudget.usermanager.repository.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mybudget.usermanager.model.entity.UserFamilyEntity;

@Repository
public interface UserFamilyRepository extends JpaRepository<UserFamilyEntity, Long>{

}
