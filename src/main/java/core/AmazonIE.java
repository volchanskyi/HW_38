package core;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonIE {
    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
	Logger logger = Logger.getLogger("");
	logger.setLevel(Level.WARNING);

	String driverPath = "./resources/webdrivers/pc/IEDriverServer32.exe";
	String url = "https://www.amazon.com/";
	String email_address = "ivan.volchanskyi@gmail.com";
	String password = "";
	String lUrl = "/gp/flex/sign-out.html/ref=nav_youraccount_signout?ie=UTF8&action=sign-out&path=%2Fgp%2Fyourstore%2Fhome&signIn=1&useRedirectOnSuccess=1";

	if (!System.getProperty("os.name").contains("Windows")) {
	    throw new IllegalArgumentException("Internet Explorer is available only on Windows");
	}

	DesiredCapabilities IEDesiredCapabilities = DesiredCapabilities.internetExplorer();
	IEDesiredCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
		true);
	IEDesiredCapabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "");
	IEDesiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	IEDesiredCapabilities.setJavascriptEnabled(true);
	IEDesiredCapabilities.setCapability("enablePersistentHover", false);

	System.setProperty("webdriver.ie.driver", driverPath);
	driver = new InternetExplorerDriver(IEDesiredCapabilities);
	// driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	WebDriverWait exWait = new WebDriverWait(driver, 10);
	driver.get(url);
	Thread.sleep(1000);

	// WebElements declaration
	exWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox"))).click();

	Actions builder = new Actions(driver);

	builder.moveToElement(driver.findElement(By.xpath("//*[@id='nav-link-accountList']/span[2]"))).clickAndHold()
		.build().perform();

	WebElement sInBtn = exWait.until(
		ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='nav-flyout-ya-signin']/a/span")));
	sInBtn.click();

	WebElement appEmail = exWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ap_email")));
	WebElement appPwd = exWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ap_password")));
	WebElement siBtn = exWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signInSubmit")));

	appEmail.clear();

	appEmail.sendKeys(email_address);

	appPwd.clear();

	appPwd.sendKeys(password);

	siBtn.click();

	WebElement useSearch = exWait
		.until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));
	useSearch.clear();
	useSearch.sendKeys("iphone");
	useSearch.sendKeys(Keys.RETURN);

	// Thread.sleep(1000);
	// WebElement hRat =
	// driver.findElement(By.xpath("//*[@id='leftNavContainer']/ul[20]/div/li[1]/span/a/span"));
	// hRat.click();

	Select ddmSortByPriceL = new Select(exWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort"))));

	ddmSortByPriceL.selectByVisibleText("Price: Low to High");
	exWait.until(ExpectedConditions.elementToBeSelected(By.xpath("//*[@id='sort']/option[3]")));
	Select ddmSortByPriceH = new Select(exWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort"))));
	ddmSortByPriceH.selectByVisibleText("Price: High to Low");
	exWait.until(ExpectedConditions.elementToBeSelected(By.xpath("//*[@id='sort']/option[4]")));

	// ddmSortBy.deselectByVisibleText("Price: Low to High");

	// get iPhones8 from the search

	// List<WebElement> allIphonesList = new ArrayList<WebElement>();
	// List<WebElement> collectedIphones =
	// driver.findElements(By.cssSelector(".a-size-medium.s-inline.s-access-title.a-text-normal"));
	//
	// allIphonesList.addAll(collectedIphones);

	// Check checkbox
	// for(int i=0; i<2; i++) {
	// chkBoxPersist.click();
	// System.out.println(chkBoxPersist.isSelected());
	// }

	// action.moveToElement(driver.findElement(By.id("nav-link-accountList"))).moveToElement(driver.findElement(By.id("nav-item-signout"))).click().build().perform();

	exWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='nav-logo']"))).click();

	String aMusic = exWait
		.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table/tbody/tr[1]/td[1]/a/span")))
		.getText();

	// mouseOverNavPanel.perform();
	builder.moveToElement(driver.findElement(By.xpath("//*[@id='nav-link-accountList']/span[2]"))).clickAndHold()
		.build().perform();
	exWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='nav-item-signout']/span")))
		.click();

	driver.quit();

	System.out.println("Browser: IE");
	System.out.println("aMusic: " + aMusic);
	// System.out.println("checkL: " + checkL);
	// System.out.println("collectedIphones: " + collectedIphones);

    }

    private static void enableChkBox(WebElement param) {
	if (!param.isSelected()) {
	    System.out.println("Checkbox is toggled Off. Turning it On");
	    param.click();
	}
    }

}
