package br.com.mybudget.usermanager.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
    @Column(name = "ID")
	private long id;
	
	
	@Column(name = "ID_BUDGET", nullable = false, columnDefinition = "INT NOT NULL")
	private long idBudget;
	
	@Column(name = "VALUE", nullable = false, columnDefinition = "DOUBLE NOT NULL")
	private double value;
	
	@Column(name = "DATE_CREATED", nullable = false, columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP")
	private Date dateCreated;
	
	@Column(name = "LAST_UPDATED", nullable = false, columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date lastUpdated;
	
	
	@ManyToOne
	private BudgetEntity budget;
	
	@ManyToMany
	@JoinColumn(name = "ID_EXPENSES_TYPES", referencedColumnName = "ID")
	private ExpensesTypeEntity expenseType;
	
}
