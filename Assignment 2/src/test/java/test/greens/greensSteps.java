package test.greens;

import greensPageObjects.greensCategoryPage;
import greensPageObjects.greensMainPage;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class greensSteps {
    private WebDriver driver;

    private greensMainPage greensMainPage;
    private greensCategoryPage greensCategoryPage;

    @Given("I am a user of the website")
    public void i_am_a_user_of_the_website() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        greensMainPage = new greensMainPage(driver);
    }

    @When("I visit the Greens website")
    public void i_visit_the_greens_website() {
        driver.get("https://www.greens.com.mt");
    }

    @And("I click on the {string} location")
    public void i_click_on_the_location(String loc) {
        greensMainPage.clickLocation(loc);
    }

    @And("I click on the {string} category")
    public void i_click_on_the_category(String category) {
        greensMainPage.clickCategory(category);
        greensCategoryPage = new greensCategoryPage(driver);
    }

    @Then("I should be taken to {string} category")
    public void i_should_be_taken_to_category(String category) {
        WebElement element = driver.findElement(By.id("frmInterface"));
        String actionUrl = element.getAttribute("action");
        String expectedCategory = greensMainPage.categoryConversion(category);
        Assert.assertTrue(actionUrl.contains(expectedCategory));
    }

    @And("the category should show at least {int} products")
    public void the_category_should_show_at_least_products(int numProducts) {
        int productCount = greensCategoryPage.getProductCount();
        assertTrue(productCount >= numProducts);
    }

    @When("I click on the first product in the results")
    public void i_click_on_the_first_product_in_the_results(){
        greensCategoryPage.selectFirstProduct();
    }

    @Then("I should be taken to the details page for that product")
    public void i_should_be_taken_to_the_details_page_for_that_product(){
        Assert.assertTrue(greensCategoryPage.checkFrmInterface("details"));
    }

    @When("I search for a product using the term {string}")
    public void i_search_for_a_product_using_the_term(String searchTerm) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtSearch")));
        greensMainPage.searchFor(searchTerm);
        greensCategoryPage = new greensCategoryPage(driver);
    }

    @Then("I should see the search results")
    public void i_should_see_the_search_results() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement frmInterface = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("frmInterface")));
        wait.until(driver -> frmInterface.getAttribute("action").contains("srch="));
        Assert.assertTrue(greensCategoryPage.checkFrmInterface("srch="));
    }



    @After
    public void cleanup(){
        if (driver != null) {
            driver.quit();
        }
    }

}

