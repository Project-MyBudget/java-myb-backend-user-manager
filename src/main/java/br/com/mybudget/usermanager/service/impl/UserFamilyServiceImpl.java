package br.com.mybudget.usermanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import br.com.mybudget.usermanager.model.dto.ApiResponseDTO;
import br.com.mybudget.usermanager.model.dto.UserFamilyRequestDTO;
import br.com.mybudget.usermanager.model.entity.UserEntity;
import br.com.mybudget.usermanager.model.entity.UserFamilyEntity;
import br.com.mybudget.usermanager.repository.UserFamilyRepository;
import br.com.mybudget.usermanager.service.UserFamilyService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserFamilyServiceImpl implements UserFamilyService {

	@Autowired
	private UserFamilyRepository userFamilyRepository;

	/**
	 * 
	 * returns the response if the user's family data was registered successfully
	 * 
	 * @return {@link ApiResponseDTO}
	 */
	@Override
	public ApiResponseDTO addFamily(UserFamilyRequestDTO requestRegisterUserFamily, UserEntity userEntity) {
		try {
			UserFamilyEntity userFamilyEntity = UserFamilyEntity.builder()
					.user(userEntity)
					.childrenNumber(requestRegisterUserFamily.getChildrenNumber())
					.civilStatus(requestRegisterUserFamily.getCivilStatus().getMaritinalStatus())
					.familyIncome(requestRegisterUserFamily.getFamilyIncome())
					.build();

			log.info("[REGISTER FAMILY] Register Family.");
			userFamilyEntity = userFamilyRepository.saveAndFlush(userFamilyEntity);

			if (userFamilyEntity != null) {
				log.info("[INFO] User Family register Sucess - [ID FAMILY]: {}", userFamilyEntity.getId());
				return new ApiResponseDTO(HttpStatus.CREATED.name(),
						"Dados da familia do usuario registrado com sucesso!");
			}

			log.error("[ERROR] Error in register user family");

		} catch (Exception ex) {
			log.error("[ERROR] Error in register user family - {}", ex);
		}
		return null;
	}
}
