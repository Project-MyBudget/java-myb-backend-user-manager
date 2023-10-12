package br.com.mybudget.usermanager.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.mybudget.usermanager.enums.UserMaritalStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "TB_USER_FAMILY")
public class UserFamilyEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long familyId;

    @OneToOne
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID")
    private UserEntity user;

    @Column(name = "CHILDREN_NUMBER", nullable = false)
    private int userChildrenNumber;

    @Column(name = "CIVIL_STATUS", nullable = false)
    private UserMaritalStatusEnum userCivilStatus;

    @Column(name = "FAMILY_INCOME", nullable = false)
    private double userFamilyIncome;
}
