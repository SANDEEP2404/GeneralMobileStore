package Amplify;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import io.appium.java_client.AppiumDriver;

public class GeneralStoreApp {

	public AppiumDriver<WebElement> driver;

	@BeforeTest
	public void setup() {

		try {

			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
			// capabilities.setCapability("automationName", "UiAutomator2");
			capabilities.setCapability("deviceName", "Sandeepemulator");
			capabilities.setCapability("platformVersion", "9");
			capabilities.setCapability("app", "F:\\appium test\\General-Store.apk");
			capabilities.setCapability("platformName", "Android");
			
			driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			Thread.sleep(3000);
			System.out.println("Apk installed in Virtual device");

		} catch (Exception exp) {
			System.out.println("Cause is " + exp.getCause());
			System.out.println("Message is " + exp.getMessage());
			exp.printStackTrace();
		}

	}

	@Test
	public void bb() throws InterruptedException {

		driver.findElementById("com.androidsample.generalstore:id/spinnerCountry").click();
		driver.findElementByXPath(
				"/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[7]")
				.click();
		driver.findElementById("com.androidsample.generalstore:id/spinnerCountry").click();
		driver.findElementByXPath(
				"/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[4]")
				.click();

		driver.findElementById("com.androidsample.generalstore:id/nameField").sendKeys("Hello");
		driver.hideKeyboard();
		driver.findElementById("com.androidsample.generalstore:id/radioFemale").click();
		driver.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();

		driver.findElementByXPath("//android.widget.TextView[@text='Products']").click();
		driver.findElementById("com.androidsample.generalstore:id/productAddCart").click();
		Thread.sleep(3000);
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"
				+ "/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.RelativeLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.TextView[2]")
				.click();

		driver.findElementById("com.androidsample.generalstore:id/appbar_btn_cart").click();

		// Validate that the Total Purchase Amount is equal to the two prices
		// added together.
		double twoitems_totalamount = 0;
		try {
			WebElement f = driver.findElementByXPath(
					"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.RelativeLayout/android.widget.RelativeLayout/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.TextView");
			System.out.println("Air Jordan 4 Retro " + f.getText().trim().replace("$", ""));
			String oneitem = f.getText().trim().replace("$", "");

			WebElement f2 = driver.findElementByXPath(
					"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.RelativeLayout/android.widget.RelativeLayout/android.support.v7.widget.RecyclerView/android.widget.RelativeLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.TextView");
			System.out.println("Air Jordan 1 Mid SE " + f2.getText().trim().replace("$", ""));
			String twoitem = f2.getText().trim().replace("$", "");

			twoitems_totalamount = twoitems_totalamount + Double.parseDouble(oneitem) + Double.parseDouble(twoitem);
			System.out.println("two prices added amount " + twoitems_totalamount);
		} catch (NumberFormatException exe) {
			System.out.println(exe);
		}
		double Total_purchase_Amount = 0;
		WebElement totalpurchase = driver.findElementById("com.androidsample.generalstore:id/totalAmountLbl");
		System.out.println("Total Purchase Amount: " + totalpurchase.getText().trim().replace("$", ""));
		String total = totalpurchase.getText().trim().replace("$", "");
		Total_purchase_Amount = Total_purchase_Amount + Double.parseDouble(total);

		if (twoitems_totalamount == Total_purchase_Amount) {

			System.out.println("Validation Success---> Two items added amount " + twoitems_totalamount
					+ " and Total Purchase Amount " + Total_purchase_Amount + " is Equal");

		} else {

			System.out.println("Two amounts are not Equal");
		}

		driver.closeApp();
		

	}
}
