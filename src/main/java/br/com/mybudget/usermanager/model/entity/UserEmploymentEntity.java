package br.com.mybudget.usermanager.model.entity;

import java.util.Date;

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
	@Column(name = "ID_EMPLOYMENT")
	private long idEmployment;

	@Column(name = "JOB_NAME", nullable = false, columnDefinition = "VARCHAR(60) NOT NULL")
	private String jobName;

	@Column(name = "SALARY", nullable = false, columnDefinition = "DOUBLE NOT NULL")
	private double salary;
	
	@Column(name = "WORK_START_DATE", nullable = false, columnDefinition = "DATE NOT NULL")
	private Date workStartDate;
	
	@OneToOne
	@JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER")
	private UserEntity user;

}
