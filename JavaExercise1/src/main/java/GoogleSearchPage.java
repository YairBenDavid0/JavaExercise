import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class GoogleSearchPage {
    protected WebDriver driver;

    @FindBy(id = "APjFqb")
    protected WebElement searchField;

    @FindBy(css = "input[aria-label*='חיפוש ב']")
    protected WebElement searchButton;


    @FindBy(css = "div.yuRUbf a")
    protected List<WebElement> mainSearchResults;

    protected WebDriverWait wait;

    protected void waitAndClick(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement)).click();
    }

    public GoogleSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5);
    }

    public void enterSearchText(String searchText) {
        wait.until(ExpectedConditions.visibilityOf(searchField));
        searchField.sendKeys(searchText);
    }

    public void clickSearchButton() {
        waitAndClick(searchButton);
    }

    public void printAllSearchResultLinks() {
        for (WebElement link : mainSearchResults) {
            System.out.println(link.getAttribute("href"));
        }
    }

    public void verifyFirstResultContainsJava() {
        String firstLink = mainSearchResults.get(0).getAttribute("href");
        Assert.assertTrue(firstLink.contains("java"));
    }

    public void verifyLastResultNotContainsInterview() {
        String lastLink = mainSearchResults.get(mainSearchResults.size() - 1).getAttribute("href");
        Assert.assertFalse(lastLink.contains("interview"));
    }

    public String getFirstSearchResultLinkText() {
        wait.until(ExpectedConditions.visibilityOf(mainSearchResults.get(0)));
        WebElement firstLink = mainSearchResults.get(0);
        return firstLink.getAttribute("innerHTML");
    }

    public boolean isFirstSearchResultContaining(String text) {
        WebElement firstLink = mainSearchResults.get(0);
        return firstLink.getText().contains(text);
    }

    public boolean isLastSearchResultContaining(String text) {
        WebElement lastLink = mainSearchResults.get(3);
        return !lastLink.getText().contains(text);
    }
}
