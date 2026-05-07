package web.service;

import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginServiceTest {

	private WebDriver createDriver() {
		String chromedriverPath = System.getenv().getOrDefault("CHROMEDRIVER", "/usr/bin/chromedriver");
		System.setProperty("webdriver.chrome.driver", chromedriverPath);

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		options.addArguments("--disable-gpu");
		options.addArguments("--window-size=1280,800");

		return new ChromeDriver(options);
	}

	private String loginPageUrl() {
		return Paths.get("pages", "login.html").toAbsolutePath().toUri().toString();
	}

	private String submitLoginForm(String username, String password, String dob) {
		WebDriver driver = createDriver();

		try {
			driver.navigate().to(loginPageUrl());

			WebElement usernameField = driver.findElement(By.id("username"));
			usernameField.clear();
			usernameField.sendKeys(username);

			WebElement passwordField = driver.findElement(By.id("passwd"));
			passwordField.clear();
			passwordField.sendKeys(password);

			WebElement dobField = driver.findElement(By.id("dob"));
			dobField.clear();
			if (dob != null && !dob.isEmpty()) {
				dobField.sendKeys(dob);
			}

			WebElement submitButton = driver.findElement(By.cssSelector("[type=submit]"));
			submitButton.submit();

			new WebDriverWait(driver, 5).until(new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver webDriver) {
					String title = webDriver.getTitle();
					return "success".equals(title) || "fail".equals(title);
				}
			});

			return driver.getTitle();
		} finally {
			driver.quit();
		}
	}

	@Test
	public void testLoginSuccessWithValidCredentialsAndDob() {
		Assert.assertEquals("success", submitLoginForm("ahsan", "ahsan_pass", "2000-01-01"));
	}

	@Test
	public void testLoginFailsWithWrongPassword() {
		Assert.assertEquals("fail", submitLoginForm("ahsan", "wrong_password", "2000-01-01"));
	}

	@Test
	public void testLoginFailsWithWrongDob() {
		Assert.assertEquals("fail", submitLoginForm("ahsan", "ahsan_pass", "2000-01-02"));
	}

	@Test
	public void testLoginFailsWithWrongUsername() {
		Assert.assertEquals("fail", submitLoginForm("wrong_user", "ahsan_pass", "2000-01-01"));
	}

	@Test
	public void testLoginFailsWithBlankUsername() {
		Assert.assertEquals("fail", submitLoginForm("", "ahsan_pass", "2000-01-01"));
	}
}
