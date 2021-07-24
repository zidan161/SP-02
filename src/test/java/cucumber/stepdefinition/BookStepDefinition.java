package cucumber.stepdefinition;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class BookStepDefinition {

    public WebDriver driver = new ChromeDriver();

    @Given("User open the website")
    public void user_open_the_website() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\zidan\\webdriver\\chromedriver.exe");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://qa.cilsy.id:8080/");
    }

    @And("Scroll to Room")
    public void scroll_to_room() {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("//*[@id=\"hotelRoomsBlock\"]"))).perform();
    }

    @When("User click button Book Now on frame")
    public void user_click_button_book_now_on_frame() {
        driver.findElement(By.xpath("//*[@id=\"hotelRoomsBlock\"]/div/div[2]/div/div[1]/div[1]/div/div[3]/a")).click();
    }

    @Then("User directing to checkout page")
    public void user_directing_to_checkout_page() {
        ArrayList<String> newTb = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(newTb.get(1));
        driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/div/div[1]/div[1]")).isDisplayed();
    }

    @When("User click button Book Now")
    public void user_click_button_book_now() {
        driver.findElement(By.name("Submit")).click();
    }

    @Then("Room has been booked")
    public void room_has_been_booked() {
        WebElement form = driver.findElement(By.cssSelector("h2:nth-child(2)"));
        Assert.assertNotNull(form);
        driver.close();
    }

    @When("User input searching data")
    public void user_input_searching_data() {
        driver.findElement(By.id("hotel_location")).sendKeys("Indonesia");
        driver.findElement(By.xpath("//*[@id=\"search_hotel_block_form\"]/div[1]/div/ul/li")).click();
        driver.findElement(By.id("id_hotel_button")).click();
        driver.findElement(By.xpath("//*[@id=\"search_hotel_block_form\"]/div[2]/div/ul/li")).click();
        driver.findElement(By.id("check_in_time")).click();
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[4]/td[6]")).click();
        driver.findElement(By.id("check_out_time")).click();
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[4]/td[7]")).click();
    }

    @And("User click button Search")
    public void user_click_button_search() {
        driver.findElement(By.name("search_room_submit")).click();
    }

    @Then("Searched hotels will displayed")
    public void searched_hotels_will_displayed() {
        WebElement searchForm = driver.findElement(By.xpath("//*[@id=\"left_column\"]/div[1]/div"));
        searchForm.isDisplayed();
        Assert.assertEquals("Search Rooms", searchForm.getText());
        driver.close();
    }

    @Given("User already on the search page")
    public void user_already_on_the_search_page() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\zidan\\webdriver\\chromedriver.exe");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://qa.cilsy.id:8080/en/6-the-hotel-prime?date_from=2021-07-23&date_to=2021-07-24");
    }

    @When("User click button Book Now on item General Room")
    public void user_click_button_book_now_on_item_general_room() {
        driver.findElement(By.xpath("//*[@id=\"category_data_cont\"]/div[1]/div/div[2]/a")).click();
    }
}
