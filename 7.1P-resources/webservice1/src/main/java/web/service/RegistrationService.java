package web.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Business logic to handle registration functions.
 */
public class RegistrationService {

	public static boolean register(String firstName, String lastName, String email, String dob) {
		if (isBlank(firstName) || isBlank(lastName) || isBlank(email) || isBlank(dob)) {
			return false;
		}

		return isValidIsoDate(dob.trim());
	}

	private static boolean isBlank(String value) {
		return value == null || value.trim().isEmpty();
	}

	private static boolean isValidIsoDate(String dob) {
		try {
			LocalDate.parse(dob, DateTimeFormatter.ISO_LOCAL_DATE);
			return dob.matches("\\d{4}-\\d{2}-\\d{2}");
		} catch (DateTimeParseException e) {
			return false;
		}
	}
}
