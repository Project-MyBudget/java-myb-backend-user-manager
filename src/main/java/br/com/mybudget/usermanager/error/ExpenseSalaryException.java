package br.com.mybudget.usermanager.error;

import br.com.mybudget.usermanager.model.dto.ApiResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ExpenseSalaryException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String message;

    public ExpenseSalaryException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public ResponseEntity<ApiResponseDTO> getResponseEntity() {
        return ResponseEntity.status(httpStatus).body(new ApiResponseDTO(httpStatus.toString(), message));
    }
}
