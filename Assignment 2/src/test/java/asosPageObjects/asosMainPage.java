package asosPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class asosMainPage {
    private WebDriver driver;

    public asosMainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCategory(String categoryName) {
        WebElement categoryLink = driver.findElement(By.xpath("//a[contains(@href, '/"+categoryName+"/')]"));
        categoryLink.click();
    }

    public void searchFor(String searchTerm) {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(searchTerm);
        searchBox.submit();
    }
}
