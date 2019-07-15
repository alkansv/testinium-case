package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends PageObject {

    @FindBy(className = "btnSignIn")
    public WebElement loginButon;

    @FindBy(css = "a[class*=menuLink]")
    public WebElement userLoginName;

    @FindBy(id = "searchData")
    private WebElement searchBox;

    @FindBy(className = "searchBtn")
    private WebElement searchButton;

    @FindBy(className = "myBasket")
    public WebElement basketButton;

    @FindBy(xpath = "//h2[contains(text(),'Sepetiniz Boş')]")
    public WebElement emptyBasketText;

    @FindBy(className = "hLogoT")
    private WebElement headerText;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAnasayfaAcildiMi() {
        return headerText.isDisplayed();
    }

    public void searchText(String searchText) {
        searchBox.sendKeys(searchText);
        searchButton.click();
    }

}
