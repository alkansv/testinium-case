package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasketPage extends PageObject {
	
	@FindBy(xpath="//span[@class='spinnerUp spinnerArrow']")
	public WebElement quantityPlus;
	
	@FindBy(xpath="//input[@class='quantity']")
	public WebElement quantity;
	
	@FindBy(xpath="//input[@class='productPrice']")
	public WebElement basketProductPrice;
	
	@FindBy(xpath="//span[@class='removeProd svgIcon svgIcon_trash']")
	public WebElement deleteButton;
	
	public BasketPage(WebDriver driver) {
		super(driver);
	}
}
