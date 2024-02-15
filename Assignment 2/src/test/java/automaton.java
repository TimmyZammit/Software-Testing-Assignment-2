
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.*;

public class automaton {

    private WebDriver driver;
    private WebDriverWait wait;
    private enum State {
        INITIAL, PAGE_LOADED, MALTA_CLICKED, LOGIN_CLICKED, LOGGED_IN, SEARCH_ENTERED, RESULTS_DISPLAYED, ITEM_SELECTED, WISHLIST_CLICKED, FAVOURITES_CLICKED, ITEM_DELETED, FAVOURITES_RECHECKED
    }

    private State currentState;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        currentState = State.INITIAL;
    }

    @Test
    public void testGreensWebsite() {
        driver.get("https://www.greens.com.mt");
        transition(State.PAGE_LOADED);

        if (currentState == State.PAGE_LOADED) {
            clickItemByHref("SM");
            transition(State.MALTA_CLICKED);
        }

        if (currentState == State.MALTA_CLICKED) {
            clickItem("linkLogin");
            transition(State.LOGIN_CLICKED);
        }

        if (currentState == State.LOGIN_CLICKED) {
            loginUser("timothy.zammit.21@um.edu.mt", "unisucks");
            transition(State.LOGGED_IN);
        }

        if (currentState == State.LOGGED_IN) {
            searchForItem("absinthe");
            transition(State.SEARCH_ENTERED);
        }

        if (currentState == State.SEARCH_ENTERED) {
            clickItem("btnSearch");
            transition(State.RESULTS_DISPLAYED);
        }

        if (currentState == State.RESULTS_DISPLAYED) {
            clickFirstItem("product-image");
            transition(State.ITEM_SELECTED);
        }

        if (currentState == State.ITEM_SELECTED) {
            clickItem("cphBody_btnWishlist");
            transition(State.WISHLIST_CLICKED);
        }

        if (currentState == State.WISHLIST_CLICKED) {
            delay(10000);
            clickItemByHref("/wishlist");
            transition(State.FAVOURITES_CLICKED);
        }

        if (currentState == State.FAVOURITES_CLICKED) {
            assertItemExists("product-image");
        }

    }

    private void clickItem(String id) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
        element.click();
    }

    private void clickItemByClass(String className) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.className(className)));
        element.click();
    }

    private void clickItemByHref(String hrefContains) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, '"+hrefContains+"')]"))
        );
        element.click();
    }

    private void loginUser(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtUsername"))).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtPassword"))).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("cphBody_btnLogin"))).click();
    }

    private void searchForItem(String searchQuery) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtSearch"))).sendKeys(searchQuery);
    }

    private void clickFirstItem(String className) {
        WebElement firstItem = wait.until(ExpectedConditions.elementToBeClickable(By.className(className)));
        firstItem.click();
    }

    private void assertItemExists(String className) {
        boolean itemExists = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(className))) != null;
        assertTrue(itemExists);
    }

    private void transition(State nextState) {
        currentState = nextState;
    }

    private void delay(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            // Handle the interruption appropriately
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
