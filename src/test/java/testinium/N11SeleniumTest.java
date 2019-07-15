package testinium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import pages.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static pages.PageObject.sleep;

public class N11SeleniumTest {

    @Test
    public void n11SeleniumTest() {

        WebDriver driver = new ChromeDriver();
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        SearchPage searchPage = new SearchPage(driver);
        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        BasketPage basketPage = new BasketPage(driver);

        driver.get("https://www.n11.com");
        assertTrue(mainPage.isAnasayfaAcildiMi());

        //-------------

        mainPage.loginButon.click();
        loginPage.loginUser("sevgialkan011@gmail.com", "Sevgi96");

        assertTrue(mainPage.userLoginName.isDisplayed());
        assertEquals("Sevgi Alkan", mainPage.userLoginName.getText());

        //-------------
        mainPage.searchText("bilgisayar");

        searchPage.scrollToElement(searchPage.nextPage);
        searchPage.nextPage.click();

        searchPage.scrollToElement(searchPage.searchResult2ndPage);
        assertEquals("active", searchPage.searchResult2ndPage.getAttribute("class").trim());
        assertEquals("2", searchPage.searchResult2ndPage.getText());

        //-------------

        WebElement selectedProduct = searchPage.getRandomProduct();
        searchPage.scrollToElement(selectedProduct);
        sleep(3L);
        selectedProduct.click();

        //-------------

        String selectedProductPrice = normalizePrice(productDetailPage.productDetailPrice.getAttribute("content"));
        productDetailPage.btnAddBasket.click();
        sleep(2L);
        mainPage.basketButton.click();
        sleep(2L);
        String selectedProductPriceBasket = normalizePrice(basketPage.basketProductPrice.getAttribute("value"));

        assertEquals(selectedProductPrice, selectedProductPriceBasket);

        //-------------

        basketPage.quantityPlus.click();
        assertEquals("2", basketPage.quantity.getAttribute("value"));

        basketPage.deleteButton.click();
        assertTrue(mainPage.emptyBasketText.isDisplayed());

        driver.close();
    }

    private String normalizePrice(String price) {
        return price
                .replace(".", "")
                .replace(",", "");
    }


}
