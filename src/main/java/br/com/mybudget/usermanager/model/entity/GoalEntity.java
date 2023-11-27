package br.com.mybudget.usermanager.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_GOALS")
public class GoalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_GOALS")
    private long idGoal;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "PROGRESS", nullable = false)
    private String progress;

    @Column(name = "ESTIMATED_DATE", nullable = false)
    private Date estimatedDate;

    @ManyToOne
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER")
    private UserEntity user;
}
