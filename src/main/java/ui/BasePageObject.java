package ui;

import Framework.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.NoSuchElementException;

/**
 * Base object used for pages and components
 * Created by silvia valencia on 3/27/2015.
 */
public abstract class BasePageObject {
    protected WebDriver driver;
    protected WebDriverWait wait;

    /**
     * Initializes the web driver, wait and web elements
     */
    public BasePageObject() {
        this.driver = DriverManager.getInstance().getWebDriver();
        this.wait = DriverManager.getInstance().getWait();
        PageFactory.initElements(driver, this);
    }

    public abstract void waitUntilPageObjectIsLoaded();

    public boolean isPresent(By by)
    {
        try {
            driver.findElement(by);
            return true;
        }catch (NoSuchElementException e)
        {
            return false;
        }
    }


}