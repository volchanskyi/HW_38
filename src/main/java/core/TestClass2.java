package core;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestClass2 {
    static WebDriver driver;

    public static void main(String[] args) {
	
	String driverPath = "./resources/webdrivers/pc/IEDriverServer.exe";
	String url = "https://www.amazon.com/";
	String email_address = "ivan.volchanskyi@gmail.com";
	String password = "Activation_51186";
	WebElement navLink = driver.findElement(By.xpath("//*[@id='nav-link-accountList']/span[2]"));
	WebElement navFly = driver.findElement(By.xpath("//*[@id='nav-flyout-ya-signin']/a/span"));
	WebElement appEmail = driver.findElement(By.xpath("//*[@id='ap_email']"));
	WebElement appPwd = driver.findElement(By.xpath("//*[@id='ap_password']"));
	WebElement siBtn = driver.findElement(By.id("signInButton"));
	WebElement hRat = driver.findElement(By.xpath("//*[@id='leftNavContainer']/ul[20]/div/li[1]/span/a/span"));
	WebElement useSearch = driver.findElement(By.id("twotabsearchtextbox"));
	List<WebElement> allIphonesList = new ArrayList<WebElement>(); 
	List<WebElement> collectedIphones = driver.findElements(By.cssSelector(".a-size-medium.s-inline.s-access-title.a-text-normal"));
	final String firebugPath = "C:/Users/Ivan/AppData/Roaming/Mozilla/Firefox/Profiles/x5r65ue5.default/extensions/firebug@software.joehewitt.com.xpi";
	
	FirefoxProfile profile = new FirefoxProfile();
	profile.addExtension(new File(firebugPath));

	if (System.getProperty("os.name").toUpperCase().contains("MAC"))
	    driverPath = "./resources/webdrivers/mac/geckodriver.sh";
	else if (System.getProperty("os.name").toUpperCase().contains("WINDOWS"))
	    driverPath = "./resources/webdrivers/pc/geckodriver.exe";
	else
	    throw new IllegalArgumentException("Unknown OS");

	System.setProperty("webdriver.gecko.driver", driverPath);
	driver = new FirefoxDriver(profile);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	WebDriverWait wait = new WebDriverWait(driver, 10);

	
		
//	WebElement
	driver.get(url);
	
	navLink.click();
	navFly.click();
	appEmail.clear();
	appEmail.sendKeys(email_address);
	appPwd.clear();
	appPwd.sendKeys(password);
	siBtn.click();
	hRat.click();
	useSearch.sendKeys("iphone");
	//get iPhones8 from the search
	allIphonesList.addAll(collectedIphones);
	
	
    }

}
