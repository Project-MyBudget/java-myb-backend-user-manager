package br.com.mybudget.usermanager.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
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

    @Column(name = "CHILDREN_NUMBER", nullable = false, columnDefinition = "INT NOT NULL DEFAULT 0")
    private int userChildrenNumber;

    @Column(name = "CIVIL_STATUS", nullable = false, columnDefinition = "CHAR(1) NOT NULL DEFAULT 'S'")
    private char userCivilStatus;

    @Column(name = "FAMILY_INCOME", nullable = false, columnDefinition = "DOUBLE NOT NULL DEFAULT 0.0")
    private double userFamilyIncome;
}
