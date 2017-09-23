package core;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.*;

public class FacebookChrome {

    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {

	Logger logger = Logger.getLogger("");
	logger.setLevel(Level.OFF);

	String driverPath = "./resources/webdrivers/pc/chromedriver.exe";

	String url = "https://facebook.com/";
	String email_address = "testusera056@gmail.com";
	String password = "passwordForUser056";

	if (System.getProperty("os.name").toUpperCase().contains("MAC"))
	    driverPath = "./resources/webdrivers/mac/chromedriver";
	else if (System.getProperty("os.name").toUpperCase().contains("WINDOWS"))
	    driverPath = "./resources/webdrivers/pc/chromedriver.exe";
	else
	    throw new IllegalArgumentException("Unknown OS");

	System.setProperty("webdriver.chrome.driver", driverPath);
	System.setProperty("webdriver.chrome.silentOutput", "true");
	ChromeOptions option = new ChromeOptions();
	option.addArguments("disable-infobars");
	option.addArguments("--disable-notifications");
	if (System.getProperty("os.name").toUpperCase().contains("MAC"))
	    option.addArguments("-start-fullscreen");
	else if (System.getProperty("os.name").toUpperCase().contains("WINDOWS"))
	    option.addArguments("--start-maximized");
	else
	    throw new IllegalArgumentException("Unknown OS");
	driver = new ChromeDriver(option);
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	driver.get(url);

	Thread.sleep(1000); // Pause in milleseconds (1000 – 1 sec)

	String title = driver.getTitle();
	String copyright = driver.findElement(By.xpath("//*[@id=\'pageFooter\']/div[3]/div/span")).getText();

	driver.findElement(By.id("email")).sendKeys(email_address);

	driver.findElement(By.id("pass")).sendKeys(password);
	driver.findElement(By.id("loginbutton")).click();

	Thread.sleep(1000);
	driver.findElement(By.xpath("//*[@id='u_0_a']/div[1]/div[1]/div/a/span")).click();

	Thread.sleep(1000);
	String friends = driver.findElement(By.xpath("//div[2]/ul/li[3]/a/span[1]")).getText();
	if (friends.isEmpty()) {
	    friends = "0";
	}

	Thread.sleep(1000);
	driver.findElement(By.id("userNavigationLabel")).click();
	driver.findElement(By.partialLinkText("Log Out")).click();

	Thread.sleep(1000);
	driver.quit();

	System.out.println("Browser: Chrome");
	System.out.println("Title of the page: " + title);
	System.out.println("Copyright: " + copyright);
	System.out.println("Friends: You have " + friends + " friends");
    }
}
