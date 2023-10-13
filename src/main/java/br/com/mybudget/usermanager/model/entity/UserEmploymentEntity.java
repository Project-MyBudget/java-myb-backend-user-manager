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
@Table(name = "TB_USER_EMPLOYMENT")
public class UserEmploymentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long employmentId;

	@OneToOne
	@JoinColumn(name = "ID_USER", referencedColumnName = "ID")
	private UserEntity user;

	@Column(name = "JOB_NAME", nullable = false)
	private String userJobName;

	@Column(name = "SALARY", nullable = false)
	private double userSalary;

}
