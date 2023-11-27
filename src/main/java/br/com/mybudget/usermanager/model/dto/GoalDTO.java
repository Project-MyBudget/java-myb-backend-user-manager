package br.com.mybudget.usermanager.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class GoalDTO {
    private Long goalId;
    private String progress;
    private String description;
    private Date estimatedDate;
    private Long userId;
}
