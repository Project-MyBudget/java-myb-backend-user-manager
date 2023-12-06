package br.com.mybudget.usermanager.schedule;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.mybudget.usermanager.repository.BudgetRepository;
import br.com.mybudget.usermanager.repository.ExpenseRepository;
import br.com.mybudget.usermanager.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserTaskSchedule {

	private boolean isExecuteValueSaved = false;
	private boolean isExecuteStatusExpenses = false;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BudgetRepository budgetRepository;

	@Autowired
	private ExpenseRepository expeseRepository;

	@Scheduled(fixedRate = 4 * 60 * 60 * 1000)
	public void updateValueSaved() {
		try {
			log.info("[INFO] Starting task to update profit.");

			LocalDate dateNow = LocalDate.now();

			if (dateNow.getDayOfMonth() == 6) {

				if (!isExecuteValueSaved) {
					List<Object[]> valuesSavedUsers = userRepository.getValueSavedUsersLastMonth();

					for (Object[] valueSavedUser : valuesSavedUsers) {
						Long idUser = Long.valueOf((Integer) valueSavedUser[0]);
						Double valueSaved = (Double) valueSavedUser[1];

						int rowsAffected = budgetRepository.updateValueSaved(idUser, valueSaved);

						if (rowsAffected > 0) {

							log.info(
									"[UPDATE VALUE SAVED] Updating user savings values. [ID USER]: {} [VALUE SAVED]: {} [ROWS AFFECTED]: {}",
									idUser, valueSaved, rowsAffected);
						} else {
							log.info(
									"[UPDATE VALUE SAVED] No values ​​have been updated. [ID USER]: {} [VALUE SAVED]: {} [ROWS AFFECTED]: {}",
									idUser, valueSaved, rowsAffected);
						}
					}

					isExecuteValueSaved = true;
				} else {
					log.info("[INFO] The task was already executed on the first day of the month {}", dateNow);
				}
			} else {
				log.info("[INFO] It's not the first day of the month yet. {}", dateNow);
				isExecuteValueSaved = false;
			}
		} catch (Exception e) {
			log.error("[ERROR] Error when performing saved values ​​update task: {}", e.getMessage());
		}
	}

	@Scheduled(fixedRate = 4 * 60 * 60 * 1000)
	public void updateStatusExpenses() {
		try {
			log.info("[INFO] Starting task to update status expenses.");

			LocalDate dateNow = LocalDate.now();

			if (dateNow.getDayOfMonth() == 6) {

				if (!isExecuteStatusExpenses) {

					int rowsAffected = expeseRepository.updateStatusExpenseLastMonth();

					if (rowsAffected > 0) {
						log.info(
								"[UPDATE EXPENSES] Success in updating the status of users expenses for the last 30 days [ROWS AFFECTED]: {}",
								rowsAffected);
					} else {
						log.info("[UPDATE EXPENSES] No expense status has been updated");
					}
					isExecuteStatusExpenses = true;
				} else {
					log.info("[INFO] The task was already executed on the first day of the month {}", dateNow);
				}
			} else {
				log.info("[INFO] It's not the first day of the month yet. {}", dateNow);
				isExecuteStatusExpenses = false;
			}
		} catch (Exception e) {
			log.error("[ERROR] Error when performing saved values ​​update task: {}", e.getMessage());
		}
	}
}
