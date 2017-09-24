package core;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FacebookIE {

    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {

	Logger logger = Logger.getLogger("");
	logger.setLevel(Level.OFF);

	String driverPath = "./resources/webdrivers/pc/IEDriverServer32.exe";

	String url = "https://facebook.com/";
	String email_address = "testusera056@gmail.com";
	String password = "passwordForUser056";

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
	driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	driver.manage().window().maximize();

	driver.get(url);

	Thread.sleep(1000); // Pause in milleseconds (1000 – 1 sec)

	String title = driver.getTitle();
	String copyright = driver.findElement(By.xpath("//*[@id=\'pageFooter\']/div[3]/div/span")).getText();

	driver.findElement(By.id("email")).clear();

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
	// Thread.sleep(1000);
	driver.findElement(By.partialLinkText("Log Out")).click();

	Thread.sleep(1000);
	driver.quit();

	System.out.println("Browser: IE");
	System.out.println("Title of the page: " + title);
	System.out.println("Copyright: " + copyright);
	System.out.println("Friends: You have " + friends + " friends");
    }
}
