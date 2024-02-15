package greensPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class greensCategoryPage {
    private WebDriver driver;
    private Actions action;

    public greensCategoryPage(WebDriver driver) {
        this.driver = driver;
        this.action = new Actions(driver);
    }

    public int getProductCount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector(".col-md-2.col-sm-3.shop-grid-item"))
        );
        return driver.findElements(By.cssSelector(".col-md-2.col-sm-3.shop-grid-item")).size();
    }

    public void selectFirstProduct() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector(".shop-grid-item a[href*='productdetails']"))
        );

        List<WebElement> productLinks = driver.findElements(
                By.cssSelector(".shop-grid-item a[href*='productdetails']")
        );

        if (!productLinks.isEmpty()) {
            productLinks.get(0).click();
        }
    }

    public boolean checkFrmInterface(String toCheck){
        WebElement element = driver.findElement(By.id("frmInterface"));
        String actionUrl = element.getAttribute("action");
        String expectedCategory = toCheck;
        return actionUrl.contains(expectedCategory);
    }


}
