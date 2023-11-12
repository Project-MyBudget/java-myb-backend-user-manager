package br.com.mybudget.usermanager.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
@Table(name = "TB_GOALS")
public class GoalEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_GOALS", nullable = false)
	private Long idGoals;
	
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;
	
	@Column(name = "PROGRESS", nullable = false)
	private String progress;
	
	@Column(name = "ESTIMATED_DATE", nullable = false)
	private Date estimatedDate;
	
	@ManyToOne
	@JoinColumn(name = "ID_USER", nullable = false)
	private UserEntity userEntity;
}
