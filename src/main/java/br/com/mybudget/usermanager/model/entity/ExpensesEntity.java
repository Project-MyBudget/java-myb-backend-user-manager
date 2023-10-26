package br.com.mybudget.usermanager.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "TB_EXPENSES")
public class ExpensesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_EXPENSES")
	private long id;

	@Column(name = "DATE_REFERENCE", nullable = false, columnDefinition = "DATETIME NOT NULL")
	private String dateReference;
	
	@Column(name = "VALUE", nullable = false, columnDefinition = "DOUBLE NOT NULL")
	private double value;
	
	// @OneToOne
	@Column(name = "ID_USER", nullable = false, columnDefinition = "INT NOT NULL")
	private UserEntity userEntity;
	
	@OneToMany 
	@JoinColumn(name = "ID_EXP_TYPE", nullable = false)
	private ExpensesTypeEntity expenseType;

}
