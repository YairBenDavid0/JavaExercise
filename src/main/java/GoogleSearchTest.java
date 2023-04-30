import org.testng.Assert;
import org.testng.annotations.Test;
public class GoogleSearchTest extends BaseTest {
    @Test
    public void testSearch() {
        searchPage.enterSearchText("Java");
        searchPage.clickSearchButton();
        searchPage.printAllSearchResultLinks();
        searchPage.verifyFirstResultContainsJava();
        searchPage.verifyLastResultNotContainsInterview();
    }
}


