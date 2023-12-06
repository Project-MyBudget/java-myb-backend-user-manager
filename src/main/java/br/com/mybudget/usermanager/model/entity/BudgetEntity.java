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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
@Entity
@Table(name = "TB_BUDGET")
public class BudgetEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_BUDGET")
	private long id;
	
	@Column(name = "BUDGET", nullable = false)
	private double budget;
	
	@Column(name = "VALUE_SAVED", nullable = true)
	private double valueSaved;
	
	@Column(name = "SPENDING_LIMIT_ECONOMY", nullable = true)
	private double spendingLimitEconomy;
	
	@OneToOne // Change
	@JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER")
	private UserEntity user;
}
