package greensPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Locale;

public class greensMainPage {
    private WebDriver driver;

    public greensMainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLocation(String loc){

        if(loc.equalsIgnoreCase("malta")){
            loc = "SM";
        }
        else if(loc.equalsIgnoreCase("gozo")){
            loc = "GZ";
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement categoryLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, 'https://www.greens.com.mt/home?loc="+loc+"')]"))
        );
        categoryLink.click();
    }

    public void clickCategory(String categoryName) {
        String processedCategoryName = categoryConversion(categoryName);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait up to 10 seconds
        WebElement categoryLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(@href, '/products?cat=" + processedCategoryName + "')]"))
        );

        categoryLink.click();
    }

    public String categoryConversion(String categoryName){
        return categoryName.toLowerCase()
                .replace(" ", "")
                .replace("&", "and")
        ;
    }

    public void searchFor(String searchTerm) {
        WebElement searchBox = driver.findElement(By.id("txtSearch"));
        searchBox.sendKeys(searchTerm);
        WebElement searchSubmit = driver.findElement(By.id("btnSearch"));
        searchSubmit.click();
    }
}
