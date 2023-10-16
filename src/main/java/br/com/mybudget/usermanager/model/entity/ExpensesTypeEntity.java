package br.com.mybudget.usermanager.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "TB_EXPENSES_TYPE")
public class ExpensesTypeEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
	private long id;
	
	@Column(name = "DESCRIPTION", nullable = false, columnDefinition = "VARCHAR(160) NOT NULL")
	private String description;
	
	@Column(name = "EXPENSE_TYPE", nullable = true, columnDefinition = "CHAR(1) NOT NULL")
	private char expenseType;
}
