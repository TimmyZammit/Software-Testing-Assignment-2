package test.asos;

import asosPageObjects.asosCategoryPage;
import asosPageObjects.asosMainPage;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

public class asosSteps {
    private WebDriver driver;

    private asosPageObjects.asosMainPage asosMainPage;
    private asosCategoryPage categoryPage;

    @Given("I am a user of the website")
    public void i_am_a_user_of_the_website() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        asosMainPage = new asosMainPage(driver);
    }

    @When("I visit the ASOS website")
    public void i_visit_the_asos_website() {
        driver.get("https://www.asos.com/");
    }

    @And("I click on the {string} category")
    public void i_click_on_the_category(String category) {
        asosMainPage.clickCategory(category);
        categoryPage = new asosCategoryPage(driver);
    }

    @Then("I should be taken to {string} category")
    public void i_should_be_taken_to_category(String category) {
        assertTrue(driver.findElements(By.id(category+"-floor")).size()!=0);
    }

    @And("the category should show at least {int} products")
    public void the_category_should_show_at_least_products(int numProducts) {
        int productCount = categoryPage.getProductCount();
        assertTrue(productCount >= numProducts);
    }

    @When("I search for a product using the term {string}")
    public void i_search_for_a_product_using_the_term(String searchTerm) {
        asosMainPage.searchFor(searchTerm);
        // Assuming search results load on the same page or a new page object is created for search results.
    }

    @After
    public void cleanup(){
        if (driver != null) {
            driver.quit();
        }
    }

}

