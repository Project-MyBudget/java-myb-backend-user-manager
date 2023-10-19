package br.com.mybudget.usermanager.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name = "TB_BUDGET")
public class BudgetEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
	private long id;
	
	@Column(name = "BUDGET", nullable = false, columnDefinition = "DOUBLE NOT NULL DEFAULT 0.0")
	private double budget;
	
	@Column(name = "VALUE_SAVED", nullable = true, columnDefinition = "DOUBLE")
	private double valueSaved;
	
	@Column(name = "SPENDING_LIMIT_ECONOMY", nullable = true, columnDefinition = "DOUBLE")
	private double spendingLimitEconomy;
	
	@OneToMany
	@JoinColumn(name = "ID_USER", referencedColumnName = "ID")
	private UserEntity user;
}