package web.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Business logic to handle login functions.
 */
public class LoginService {

	private static final String VALID_USERNAME = "ahsan";
	private static final String VALID_PASSWORD = "ahsan_pass";
	private static final String VALID_DOB = "2000-01-01";

	/**
	 * Returns true only when username, password, and dob are valid.
	 * Expected dob format: yyyy-MM-dd.
	 */
	public static boolean login(String username, String password, String dob) {
		if (isBlank(username) || isBlank(password) || isBlank(dob)) {
			return false;
		}

		String cleanUsername = username.trim();
		String cleanDob = dob.trim();

		if (!isValidIsoDate(cleanDob)) {
			return false;
		}

		return VALID_USERNAME.equals(cleanUsername)
				&& VALID_PASSWORD.equals(password)
				&& VALID_DOB.equals(cleanDob);
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
