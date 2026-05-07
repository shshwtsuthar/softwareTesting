package web.service;

import org.junit.Assert;
import org.junit.Test;

public class LoginServiceUnitTest {

	@Test
	public void loginShouldSucceedWhenAllThreeValuesAreCorrect() {
		Assert.assertTrue(LoginService.login("ahsan", "ahsan_pass", "2000-01-01"));
	}

	@Test
	public void loginShouldFailWhenUsernameIsWrong() {
		Assert.assertFalse(LoginService.login("wrong_user", "ahsan_pass", "2000-01-01"));
	}

	@Test
	public void loginShouldFailWhenPasswordIsWrong() {
		Assert.assertFalse(LoginService.login("ahsan", "wrong_password", "2000-01-01"));
	}

	@Test
	public void loginShouldFailWhenDobIsWrong() {
		Assert.assertFalse(LoginService.login("ahsan", "ahsan_pass", "2000-01-02"));
	}

	@Test
	public void loginShouldFailWhenDobFormatIsInvalid() {
		Assert.assertFalse(LoginService.login("ahsan", "ahsan_pass", "01-01-2000"));
	}

	@Test
	public void loginShouldFailWhenDobDateIsImpossible() {
		Assert.assertFalse(LoginService.login("ahsan", "ahsan_pass", "2000-02-30"));
	}

	@Test
	public void loginShouldFailWhenUsernameIsMissing() {
		Assert.assertFalse(LoginService.login("", "ahsan_pass", "2000-01-01"));
		Assert.assertFalse(LoginService.login(null, "ahsan_pass", "2000-01-01"));
	}

	@Test
	public void loginShouldFailWhenPasswordIsMissing() {
		Assert.assertFalse(LoginService.login("ahsan", "", "2000-01-01"));
		Assert.assertFalse(LoginService.login("ahsan", null, "2000-01-01"));
	}

	@Test
	public void loginShouldFailWhenDobIsMissing() {
		Assert.assertFalse(LoginService.login("ahsan", "ahsan_pass", ""));
		Assert.assertFalse(LoginService.login("ahsan", "ahsan_pass", null));
	}

	@Test
	public void loginShouldAllowWhitespaceAroundUsernameAndDob() {
		Assert.assertTrue(LoginService.login("  ahsan  ", "ahsan_pass", "  2000-01-01  "));
	}
}
