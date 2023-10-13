package br.com.mybudget.usermanager.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.mybudget.usermanager.model.dto.ResponseStatusLogDTO;
import br.com.mybudget.usermanager.model.dto.UserDTO;
import br.com.mybudget.usermanager.model.dto.UserFamilyDTO;
import br.com.mybudget.usermanager.model.entity.UserEntity;
import br.com.mybudget.usermanager.model.entity.UserFamilyEntity;
import br.com.mybudget.usermanager.repository.impl.UserFamilyRepository;
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
	 * @return {@link ResponseStatusLogDTO}
	 */
	@Transactional(rollbackOn = Exception.class)
	@Override
	public ResponseStatusLogDTO registerUserFamily(UserDTO requestRegisterUserFamily, UserEntity userEntity) {
		try {
			UserFamilyEntity userFamilyEntity = UserFamilyEntity.builder().user(userEntity)
					.userChildrenNumber(requestRegisterUserFamily.getUserFamily().getUserChildrenNumber())
					.userCivilStatus(
							requestRegisterUserFamily.getUserFamily().getUserCivilStatus().getMaritinalStatus())
					.userFamilyIncome(requestRegisterUserFamily.getUserFamily().getUserFamilyIncome()).build();

			userFamilyEntity = userFamilyRepository.saveAndFlush(userFamilyEntity);

			if (userFamilyEntity != null) {
				log.info("[INFO] User Family register Sucess - [ID USER FAMILY]: " + userFamilyEntity.getFamilyId());
				return new ResponseStatusLogDTO(201, "Caracteristicas da familia do usuario registrado com sucesso!",
						userFamilyEntity.getFamilyId());
			}

			log.error("[ERROR] Error in register user family");
			return new ResponseStatusLogDTO(500, "Não foi possivel registrar as caracteristicas da familia do usuario",
					null);

		} catch (Exception ex) {
			log.error("[ERROR] Error in register user family - " + ex);
			return new ResponseStatusLogDTO(500, "Não foi possivel registrar as caracteristicas da familia do usuario",
					null);
		}
	}

	/**
	 * 
	 * returns the response if the user's family data was registered successfully
	 * 
	 * @return {@link ResponseStatusLogDTO}
	 */
	@Override
	public ResponseEntity<ResponseStatusLogDTO> registerUserFamily(UserFamilyDTO requestRegisterUserFamily,
			UserEntity userEntity) {
		try {
			UserFamilyEntity userFamilyEntity = UserFamilyEntity.builder()
					.user(userEntity)
					.userChildrenNumber(requestRegisterUserFamily.getUserChildrenNumber())
					.userCivilStatus(requestRegisterUserFamily.getUserCivilStatus().getMaritinalStatus())
					.userFamilyIncome(requestRegisterUserFamily.getUserFamilyIncome())
					.build();

			userFamilyEntity = userFamilyRepository.saveAndFlush(userFamilyEntity);

			if (userFamilyEntity != null) {
				log.info("[INFO] User Family register Sucess - [ID USER FAMILY]: " + userFamilyEntity.getFamilyId());
				return ResponseEntity.status(HttpStatus.CREATED)
						.body(new ResponseStatusLogDTO(201,
								"Caracteristicas da familia do usuario registrado com sucesso!",
								userFamilyEntity.getFamilyId()));
			}

			log.error("[ERROR] Error in register user family");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseStatusLogDTO(500,
					"Não foi possivel registrar as caracteristicas da familia do usuario", null));

		} catch (Exception ex) {
			log.error("[ERROR] Error in register user family - " + ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseStatusLogDTO(500,
					"Não foi possivel registrar as caracteristicas da familia do usuario", null));
		}
	}
}
