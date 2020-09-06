package Amplify;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AmplifyWeb {

	WebDriver driver;

	@Test
	public void Amplify() throws InterruptedException {
		System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://test.amplifypro.life/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

		Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@class='button secondary']")).click();

		Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@class='button tertiary login_button']")).click();
		driver.findElement(By.xpath("//form[@class='login_form']//input[@placeholder='Email Address']"))
				.sendKeys("sandygarrepally@gmail.com");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("SANTHOSH2404!!s");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//form[@class='login_form']//input[@class='button']")).click();

		// Validate arrival at the Welcome Page
		String ExpectedSuccessfulMessage = "WELCOME SANDEEP";
		if (ExpectedSuccessfulMessage != null) {
			WebElement ActualSuccessfulMessage = driver
					.findElement(By.xpath("//h1[contains(text(),'Welcome Sandy')]"));
			Assert.assertEquals(ActualSuccessfulMessage.getText(), ExpectedSuccessfulMessage);
			System.out.println("Welcome Page Successfully validated -->" + ActualSuccessfulMessage.getText());

		}
		driver.close();
	}
}
