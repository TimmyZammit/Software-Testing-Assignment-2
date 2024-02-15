package asosPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class asosCategoryPage {
    private WebDriver driver;
    private Actions action;

    public asosCategoryPage(WebDriver driver) {
        this.driver = driver;
        this.action = new Actions(driver);
    }

    public int getProductCount() {
        return  driver.findElements(By.className("feature__image")).size()+
                driver.findElements(By.className("carousel__item")).size();
    }

    public void selectFirstProduct() {
        List<WebElement> products = driver.findElements(By.cssSelector("article._2qG85dG"));
        if (!products.isEmpty()) {
            products.get(0).click();
        }
    }

}
