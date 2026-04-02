package sit707_week2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Selenium operations for SIT707/SIT333 week 2 task
 * 
 * Officeworks:
 * - Fill the registration form
 * - Intentionally fail ONLY the password requirement
 * - Click Create account
 * - Save screenshot
 * 
 * Alternative site:
 * - Fill a demo registration page
 * - Click Submit
 * - Save screenshot
 */
public class SeleniumOperations {

    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(15);

    public static void sleep(int sec) {
        try {
            Thread.sleep(sec * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    private static WebDriver createDriver() {
        ChromeOptions options = new ChromeOptions();

        // Good for Fedora/Linux. If a browser binary is found, use it.
        String browserBinary = firstExistingPath(
                "/usr/bin/google-chrome",
                "/usr/bin/google-chrome-stable",
                "/usr/bin/chromium",
                "/usr/bin/chromium-browser");

        if (browserBinary != null) {
            options.setBinary(browserBinary);
            System.out.println("Using browser binary: " + browserBinary);
        } else {
            System.out.println("No explicit browser binary found. Relying on Selenium Manager / PATH.");
        }

        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");

        System.out.println("Fire up chrome/chromium browser.");
        WebDriver driver = new ChromeDriver(options);
        System.out.println("Driver info: " + driver);
        return driver;
    }

    private static String firstExistingPath(String... paths) {
        for (String p : paths) {
            if (Files.exists(Paths.get(p))) {
                return p;
            }
        }
        return null;
    }

    private static WebElement waitForAnyVisible(WebDriver driver, WebDriverWait wait, By... locators) {
        return wait.until(d -> {
            for (By locator : locators) {
                List<WebElement> elements = d.findElements(locator);
                for (WebElement e : elements) {
                    try {
                        if (e.isDisplayed()) {
                            return e;
                        }
                    } catch (Exception ignored) {
                    }
                }
            }
            return null;
        });
    }

    private static void typeIntoField(WebDriver driver, WebDriverWait wait, String value, By... locators) {
        WebElement element = waitForAnyVisible(driver, wait, locators);
        scrollIntoView(driver, element);
        element.clear();
        element.sendKeys(value);
    }

    private static void clickElement(WebDriver driver, WebDriverWait wait, By... locators) {
        WebElement element = waitForAnyVisible(driver, wait, locators);
        scrollIntoView(driver, element);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    private static void clickIfPresent(WebDriver driver, By... locators) {
        for (By locator : locators) {
            try {
                List<WebElement> elements = driver.findElements(locator);
                for (WebElement e : elements) {
                    if (e.isDisplayed()) {
                        try {
                            e.click();
                        } catch (Exception ex) {
                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", e);
                        }
                        return;
                    }
                }
            } catch (Exception ignored) {
            }
        }
    }

    private static void selectDropdown(WebDriver driver, WebDriverWait wait, String textOrValue, By... locators) {
        WebElement element = waitForAnyVisible(driver, wait, locators);
        scrollIntoView(driver, element);

        Select select = new Select(element);
        try {
            select.selectByVisibleText(textOrValue);
            return;
        } catch (Exception ignored) {
        }

        try {
            select.selectByValue(textOrValue);
            return;
        } catch (Exception ignored) {
        }

        throw new RuntimeException("Could not select dropdown value: " + textOrValue);
    }

    private static void scrollIntoView(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'nearest'});", element);
    }

    private static void takeScreenshot(WebDriver driver, String fileName) {
        try {
            Path folder = Paths.get("screenshots");
            Files.createDirectories(folder);

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path dest = folder.resolve(fileName);
            Files.copy(src.toPath(), dest, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Screenshot saved: " + dest.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String uniqueEmail(String prefix) {
        return prefix + "." + System.currentTimeMillis() + "@example.com";
    }

    public static void officeworks_registration_page(String url) {
        WebDriver driver = null;

        try {
            driver = createDriver();
            WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);

            driver.get(url);
            sleep(3);

            // Optional cookie/consent buttons if they appear
            clickIfPresent(driver,
                    By.xpath("//button[contains(., 'Accept')]"),
                    By.xpath("//button[contains(., 'I agree')]"),
                    By.xpath("//button[contains(., 'Allow')]"),
                    By.cssSelector("[aria-label='Close']"));

            String email = uniqueEmail("shashwat.s223938355.officeworks");
            String weakPassword = "abc123"; // intentionally weak so validation fails

            // First name
            typeIntoField(driver, wait, "Shashwat",
                    By.id("firstname"),
                    By.name("firstname"),
                    By.cssSelector("input[id='firstname']"),
                    By.xpath("//input[contains(@aria-label,'First name')]"),
                    By.xpath("//*[contains(normalize-space(),'First name')]/following::input[1]"));

            // Last name
            typeIntoField(driver, wait, "Suthar",
                    By.id("lastname"),
                    By.name("lastname"),
                    By.cssSelector("input[id='lastname']"),
                    By.xpath("//input[contains(@aria-label,'Last name')]"),
                    By.xpath("//*[contains(normalize-space(),'Last name')]/following::input[1]"));

            // Phone number
            typeIntoField(driver, wait, "0400000000",
                    By.id("phone"),
                    By.id("phoneNumber"),
                    By.name("phone"),
                    By.name("phoneNumber"),
                    By.cssSelector("input[type='tel']"),
                    By.xpath("//input[contains(@aria-label,'Phone')]"),
                    By.xpath("//*[contains(normalize-space(),'Phone number')]/following::input[1]"),
                    By.xpath("//*[contains(normalize-space(),'Phone')]/following::input[1]"));

            // Email address
            typeIntoField(driver, wait, email,
                    By.id("email"),
                    By.name("email"),
                    By.cssSelector("input[type='email']"),
                    By.xpath("//input[contains(@aria-label,'Email')]"),
                    By.xpath("//*[contains(normalize-space(),'Email address')]/following::input[1]"));

            // Password
            typeIntoField(driver, wait, weakPassword,
                    By.id("password"),
                    By.name("password"),
                    By.cssSelector("input[type='password']"),
                    By.xpath("//input[contains(@aria-label,'Password')]"),
                    By.xpath("//*[normalize-space()='Password']/following::input[1]"));

            // Confirm password
            typeIntoField(driver, wait, weakPassword,
                    By.id("confirmPassword"),
                    By.id("confirm-password"),
                    By.name("confirmPassword"),
                    By.name("confirm-password"),
                    By.xpath("//input[contains(@aria-label,'Confirm password')]"),
                    By.xpath("//*[contains(normalize-space(),'Confirm password')]/following::input[1]"));

            sleep(1);

            // Create account button
            clickElement(driver, wait,
                    By.xpath("//button[normalize-space()='Create account']"),
                    By.xpath("//span[normalize-space()='Create account']/ancestor::button[1]"),
                    By.cssSelector("button[type='submit']"));

            // Give the page time to show validation
            sleep(3);

            takeScreenshot(driver, "officeworks_after_submit.png");

        } catch (Exception e) {
            System.out.println("Officeworks automation failed:");
            e.printStackTrace();

            if (driver != null) {
                takeScreenshot(driver, "officeworks_error.png");
            }
        } finally {
            sleep(2);
            if (driver != null) {
                driver.quit();
            }
        }
    }

    public static void demo_automation_registration_page(String url) {
        WebDriver driver = null;

        try {
            driver = createDriver();
            WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);

            driver.get(url);
            sleep(3);

            String email = uniqueEmail("shashwat.s223938355.demo");

            // First name
            typeIntoField(driver, wait, "Shashwat",
                    By.cssSelector("input[placeholder='First Name']"),
                    By.cssSelector("input[ng-model='FirstName']"));

            // Last name
            typeIntoField(driver, wait, "Suthar",
                    By.cssSelector("input[placeholder='Last Name']"),
                    By.cssSelector("input[ng-model='LastName']"));

            // Address
            typeIntoField(driver, wait, "Geelong, Victoria, Australia",
                    By.cssSelector("textarea[ng-model='Adress']"),
                    By.tagName("textarea"));

            // Email
            typeIntoField(driver, wait, email,
                    By.cssSelector("input[type='email']"),
                    By.cssSelector("input[ng-model='EmailAdress']"));

            // Phone
            typeIntoField(driver, wait, "0400000001",
                    By.cssSelector("input[type='tel']"),
                    By.cssSelector("input[ng-model='Phone']"));

            // Gender
            clickElement(driver, wait,
                    By.cssSelector("input[value='Male']"));

            // Hobbies
            clickElement(driver, wait,
                    By.cssSelector("input[value='Cricket']"));

            // Skills
            selectDropdown(driver, wait, "Java",
                    By.id("Skills"),
                    By.cssSelector("select[ng-model='Skill']"));

            // Country
            try {
                selectDropdown(driver, wait, "India",
                        By.id("countries"),
                        By.cssSelector("select[ng-model='country']"));
            } catch (Exception ignored) {
                // Some versions of the page handle country differently.
            }

            // Date of birth
            try {
                selectDropdown(driver, wait, "1999",
                        By.id("yearbox"));
            } catch (Exception ignored) {
            }

            try {
                selectDropdown(driver, wait, "May",
                        By.cssSelector("select[placeholder='Month']"));
            } catch (Exception ignored) {
            }

            try {
                selectDropdown(driver, wait, "15",
                        By.id("daybox"));
            } catch (Exception ignored) {
            }

            // Password
            typeIntoField(driver, wait, "Shashwat@123",
                    By.id("firstpassword"),
                    By.cssSelector("input[type='password']"));

            // Confirm password
            typeIntoField(driver, wait, "Shashwat@123",
                    By.id("secondpassword"),
                    By.xpath("(//input[@type='password'])[2]"));

            sleep(1);

            // Submit
            clickElement(driver, wait,
                    By.id("submitbtn"),
                    By.xpath("//button[normalize-space()='Submit']"),
                    By.xpath("//input[@value='Submit']"));

            sleep(3);

            takeScreenshot(driver, "demo_registration_after_submit.png");

        } catch (Exception e) {
            System.out.println("Demo site automation failed:");
            e.printStackTrace();

            if (driver != null) {
                takeScreenshot(driver, "demo_registration_error.png");
            }
        } finally {
            sleep(2);
            if (driver != null) {
                driver.quit();
            }
        }
    }
}