package br.com.mybudget.usermanager.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import br.com.mybudget.usermanager.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.mybudget.usermanager.model.dto.ApiResponseDTO;
import br.com.mybudget.usermanager.model.dto.UserBudgetEconomyRequestDTO;
import br.com.mybudget.usermanager.model.dto.UserDTO;
import br.com.mybudget.usermanager.model.dto.UserEmploymentRequestDTO;
import br.com.mybudget.usermanager.model.entity.BudgetEntity;
import br.com.mybudget.usermanager.model.entity.UserEntity;
import br.com.mybudget.usermanager.repository.UserRepository;
import br.com.mybudget.usermanager.service.CryptoDataService;
import br.com.mybudget.usermanager.service.UserEmploymentService;
import br.com.mybudget.usermanager.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserEmploymentService userEmploymentService;
	
	@Autowired
	private CryptoDataService cryptoDataService;

	@Autowired
	private BudgetRepository budgetRepository;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public ResponseEntity<ApiResponseDTO> addUser(UserDTO requestRegisterUser) {
		try {
			UserEntity userEntity = convertToEntity(requestRegisterUser);

			log.info("[REGISTER USER] Encrypting new password.");
			List<String> encrypteds = cryptoDataService.encryptData(userEntity.getPassword());
			userEntity.setPassword(encrypteds.get(0));

			userEntity = userRepository.saveAndFlush(userEntity);

			if (userEntity.getIdUser() > 0) {
				
				if (requestRegisterUser.getEmployment() != null) {
					UserEmploymentRequestDTO employmentDTO = UserEmploymentRequestDTO.builder()
							.user(requestRegisterUser)
							.jobName(requestRegisterUser.getEmployment().getJobName())
							.salary(requestRegisterUser.getEmployment().getSalary())
							.workStartDate(requestRegisterUser.getEmployment().getWorkStartDate())
							.build();

					BudgetEntity budgetEntity = BudgetEntity
							.builder()
							.budget(employmentDTO.getSalary())
							.user(userEntity)
							.valueSaved(0)
							.spendingLimitEconomy(0)
							.build();

					budgetRepository.saveAndFlush(budgetEntity);
					userEmploymentService.addEmployment(employmentDTO, userEntity);
				}

				log.info("[INFO] User register Sucess - [ID]: {}", userEntity.getIdUser());
				return ResponseEntity.ok(new ApiResponseDTO(HttpStatus.CREATED.name(), "Usuario registrado com sucesso!"));
			}

			log.error("[ERROR] Error in register user");
			return ResponseEntity.internalServerError().body(new ApiResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.name(),
					"Não foi possivel registrar o usuario"));

		} catch (Exception ex) {
			log.error("[ERROR] Error in register user - {}", ex);
			return ResponseEntity.badRequest()
					.body(new ApiResponseDTO(HttpStatus.BAD_REQUEST.name(), "Não foi possivel registrar o usuario"));
		}
	}

	@Override
	public UserEntity findByIdUser(long id) {
		try {
			Optional<UserEntity> userEntity = userRepository.findById(id);

			if (userEntity.isPresent()) {
				log.info("[INFO] Sucess find user by id [ID]: {}", id);
				return userEntity.get();
			}

			log.error("[ERROR] Error in find user by [ID]: {}", id);
		} catch (Exception ex) {
			log.error("[ERROR] Error in find user by [ID]: {} : [ERROR] {}", id, ex.getMessage());
		}
		return null;
	}

	private static UserEntity convertToEntity(UserDTO userDto) {
		return UserEntity.builder()
				.firstName(userDto.getFirstName())
				.lastName(userDto.getLastName())
				.dateOfBirth(userDto.getDateOfBirth())
				.gender(userDto.getGender())
				.childrenNumber(userDto.getChildrenNumber())
				.phoneNumber(userDto.getPhoneNumber())
				.email(userDto.getEmail())
				.status(userDto.getStatus())
				.password(userDto.getPassword())
				.civilStatus(userDto.getCivilStatus().getMaritalStatus())
				.build();
	}

	@Override
	public ResponseEntity<ApiResponseDTO> updateBudgetAndEconomies(UserBudgetEconomyRequestDTO userBudgetEconomyRequestDTO) {
		int result = userRepository
				.updateBudgetAndSpendingLimitEconomyByIdUser(userBudgetEconomyRequestDTO.getSpendingLimitEconomy(), userBudgetEconomyRequestDTO.getBudget(), userBudgetEconomyRequestDTO.getIdUser());
		
		if (result > 0) {
			log.info("[INFO] Success on updating the current budget and spending limit economy.");
			return ResponseEntity.ok(new ApiResponseDTO("200", "Sucesso ao atualizar o limite e orçamento."));
		}
		log.info("[ERROR] There was an error while updating.");
		return ResponseEntity.badRequest().body(new ApiResponseDTO("400", "Erro ao tentar atualizar.")); 
	}
}
