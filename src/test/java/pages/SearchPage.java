package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class SearchPage extends PageObject {

    @FindBy(css = "a[class*=navigation]")
    public WebElement nextPage;

    @FindBy(css = "a[href='https://www.n11.com/arama?q=bilgisayar&pg=2']")
    public WebElement searchResult2ndPage;

    @FindBy(xpath = "//a[@class='plink']")
    private List<WebElement> products;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getRandomProduct() {
        int productSize = products.size();
        int randomNumber = getRandomNumber(productSize);
        return products.get(randomNumber);
    }

    private int getRandomNumber(int upperBound) {
        Random rand = new Random();
        return rand.nextInt(upperBound);
    }
}
