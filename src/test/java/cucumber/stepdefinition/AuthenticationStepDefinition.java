package cucumber.stepdefinition;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class AuthenticationStepDefinition {

    public WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, 10);

    @Given("Registration page is open")
    public void registration_page_is_open() {
        String nextUrl = "http://qa.cilsy.id:8080/en/login?back=my-account#account-creation";
        wait.until(ExpectedConditions.urlToBe(nextUrl));
    }

    @When("User input Email {string}")
    public void user_input_email(String email) {
        driver.findElement(By.xpath("//*[@id=\"email_create\"]")).sendKeys(email);
    }

    @And("User click button create an account")
    public void user_click_button_create_an_account() {
        driver.findElement(By.id("SubmitCreate")).click();
    }

    @Then("User directing to register page")
    public void user_directing_to_register_page() {
        String nextUrl = "http://qa.cilsy.id:8080/en/login?back=my-account#account-creation";
        wait.until(ExpectedConditions.urlToBe(nextUrl));
    }

    @When("User input personal data")
    public void user_input_personal_data() {
        driver.findElement(By.id("id_gender1")).click();

        driver.findElement(By.name("customer_firstname")).sendKeys("Zidan");
        driver.findElement(By.name("customer_lastname")).sendKeys("Muhammad");
        driver.findElement(By.name("passwd")).sendKeys("zidan123");

        driver.findElement(By.id("days")).click();
        driver.findElement(By.xpath("//*[@id=\"days\"]/option[2]")).click();
        driver.findElement(By.id("months")).click();
        driver.findElement(By.xpath("//*[@id=\"months\"]/option[2]")).click();
        driver.findElement(By.id("years")).click();
        driver.findElement(By.xpath("//*[@id=\"years\"]/option[22]")).click();
    }

    @And("User click button Register")
    public void user_click_button_register() {
        driver.findElement(By.cssSelector("#submitAccount")).click();
    }

    @Then("Account successfully created")
    public void account_successfully_created() {
        String successUrl = "http://qa.cilsy.id:8080/en/my-account";
        wait.until(ExpectedConditions.urlToBe(successUrl));
        driver.findElement(By.xpath("//*[@id=\"center_column\"]/p[1]")).isDisplayed();
        driver.close();
    }

    @Given("Login page is open")
    public void login_page_is_open() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\zidan\\webdriver\\chromedriver.exe");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://qa.cilsy.id:8080/");
        driver.findElement(By.linkText("Sign in")).click();
    }

    @When("User input Email {string} and Password {string}")
    public void user_input_email_and_password(String email, String pass) {
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("passwd")).sendKeys(pass);
    }

    @And("User click button Login")
    public void user_click_button_login() {
        driver.findElement(By.name("SubmitLogin")).click();
    }

    @Then("User successfully log in")
    public void user_successfully_login() {
        String successUrl = "http://qa.cilsy.id:8080/en/my-account";
        wait.until(ExpectedConditions.urlToBe(successUrl));
        String text = driver.findElement(By.xpath("//*[@id=\"center_column\"]/h1")).getText();
        Assert.assertEquals("MY ACCOUNT", text);
        driver.close();
    }

    @Then("System show error")
    public void systemShowError() {
        WebElement warning = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]"));
        wait.until(ExpectedConditions.visibilityOf(warning));
        Assert.assertEquals("Authentication failed.", driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li")).getText());
        driver.close();
    }

    @Given("User already sign in")
    public void user_already_sign_in() {
        login_page_is_open();
        user_input_email_and_password("zidan@email.com", "zidan123");
        user_click_button_login();
    }

    @When("User click button Account")
    public void user_click_button_account() {
        driver.findElement(By.id("user_info_acc")).click();
    }

    @Then("Dropdown menu is showing")
    public void dropdown_menu_is_showing() {
        driver.findElement(By.className("dropdown-menu")).isDisplayed();
    }

    @When("User click button Logout")
    public void user_click_button_logout() {
        driver.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[7]/ul/li/ul/li[3]/a")).click();
    }

    @Then("User logout")
    public void user_logout() {
        WebElement signIn = driver.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[7]/ul/li/a/span"));
        signIn.isDisplayed();
        Assert.assertEquals("Sign in", signIn.getText());
        driver.close();
    }
}
