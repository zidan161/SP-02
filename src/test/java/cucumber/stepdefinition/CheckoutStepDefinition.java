package cucumber.stepdefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class CheckoutStepDefinition {

    public WebDriver driver = new ChromeDriver();

    @Given("User already book a room")
    public void userAlreadyBookARoom() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\zidan\\webdriver\\chromedriver.exe");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://qa.cilsy.id:8080/");
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.id("email")).sendKeys("zidan@email.com");
        driver.findElement(By.id("passwd")).sendKeys("zidan123");
        driver.findElement(By.name("SubmitLogin")).click();
        driver.navigate().to("http://qa.cilsy.id:8080/en/the-hotel-prime/2-super-delux-rooms.html");
        driver.findElement(By.name("Submit")).click();
    }

    @When("User click button Checkout on Chart Menu")
    public void userClickButtonCheckoutOnChartMenu() {
        driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a")).click();
    }

    @Then("User should directing to checkout page")
    public void userShouldDirectingToCheckoutPage() {
        driver.findElement(By.id("shopping-cart-summary-head")).isDisplayed();
    }

    @When("User click Proceed button")
    public void userClickProceedButton() {
        driver.findElement(By.xpath("//*[@id=\"collapse-shopping-cart\"]/div/div[2]/div[2]/div/a")).click();
        driver.findElement(By.xpath("//*[@id=\"collapse-guest-info\"]/div/div[4]/div/a")).click();
    }

    @And("User choose the Payment method")
    public void userChooseThePaymentMethod() {
        driver.findElement(By.id("cgv")).click();
        driver.findElement(By.cssSelector("#HOOK_PAYMENT > div:nth-child(1) > div > p > a")).click();
    }

    @Then("Confirm page open")
    public void confirm_page_open() {
        driver.findElement(By.xpath("//*[@id=\"center_column\"]/form/div/h3")).isDisplayed();
    }


    @When("User click button confirm")
    public void userClickButtonConfirm() {
        driver.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button")).click();
    }

    @Then("Room has been ordered")
    public void roomHasBeenOrdered() {
        WebElement warning = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/p[1]"));
        warning.isDisplayed();
        Assert.assertEquals("Your order on QA-Cilsy is complete.", warning.getText());
        driver.close();
    }
}
