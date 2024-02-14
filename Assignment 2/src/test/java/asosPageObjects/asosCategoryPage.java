package asosPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class asosCategoryPage {
    private WebDriver driver;

    public asosCategoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectFirstProduct() {
        List<WebElement> products = driver.findElements(By.cssSelector("article._2qG85dG")); // Update this selector based on the actual site structure
        if (!products.isEmpty()) {
            products.get(0).click();
        }
    }

    public int getProductCount() {
        return driver.findElements(By.cssSelector("article._2qG85dG")).size(); // Update this selector based on the actual site structure
    }
}
