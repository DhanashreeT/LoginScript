package Login.Login;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class loginScript {

	public static String baseUrl = "https://192.168.101.4:4100";
	public WebDriver driver;

	@BeforeTest
	public void loadUrl() {
		String os = System.getProperty("os.name").toLowerCase();
		System.out.println("Operating system: " + os);

		if (os.contains("linux")) {
			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver_linux");
			driver = new ChromeDriver();
		} else if (os.contains("windows")) {
			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}

		driver.get(baseUrl);
	}

	@Test
	public void enterCredentials() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 20);
		List<WebElement> logout = driver.findElements(By.xpath("//input[@name='Logout']"));
		if (logout.size() > 0) {
			logout.get(0).click();
		}
		driver.findElement(By.xpath("//input[@name='fw_username']")).sendKeys("dhanashreet");
		driver.findElement(By.xpath("//input[@name='fw_password']")).sendKeys("9096924204dd");
		driver.findElement(By.xpath("//input[@name='submit']")).click();
		WebElement loginMsg = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@align='center']/b")));
		String successMsg = loginMsg.getText();

		Assert.assertEquals(successMsg, "You have been succssfully authenticated.","success message does not match");
	}

	@AfterTest
	public void closeWindow() {
		driver.close();
		driver.quit();
	}

}
