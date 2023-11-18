package br.com.mybudget.usermanager.model.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExpenseTypeResponseDTO implements Serializable {
    private Long id;
    private String description;
    private String type;
}
