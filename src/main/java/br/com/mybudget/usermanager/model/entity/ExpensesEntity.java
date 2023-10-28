package br.com.mybudget.usermanager.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "TB_EXPENSES")
public class ExpensesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_EXPENSES")
	private long id;

	@Column(name = "DATE_REFERENCE", nullable = false)
	private String dateReference;
	
	@Column(name = "VALUE", nullable = false)
	private double value;
	
	@OneToOne
	@JoinColumn(name = "ID_USER", nullable = false)
	private UserEntity userEntity;
	
	@OneToOne
	@JoinColumn(name = "ID_EXP_TYPE", nullable = false)
	private ExpensesTypeEntity expenseType;

}
