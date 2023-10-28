package br.com.mybudget.usermanager.model.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExpenseEnvelopeDTO {
	
	private Long idUser;
	private List<ExpenseRequestDTO> expenses;
}
