package sit707_week2;

public class Main {
    public static void main(String[] args) {
        SeleniumOperations.officeworks_registration_page(
                "https://www.officeworks.com.au/app/identity/create-account");

        SeleniumOperations.demo_automation_registration_page(
                "https://demo.automationtesting.in/Register.html");
    }
}