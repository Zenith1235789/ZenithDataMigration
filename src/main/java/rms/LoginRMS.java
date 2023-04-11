package rms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginRMS {
	static WebDriver driver;
	static String url = "http://client1.rms.qc1/";
	static By userNameField = By.cssSelector(
			"div.main-waraper div.salesRM-card div.login-part form.ng-untouched.ng-pristine.ng-invalid div.user-pass div.credential:nth-child(1) div.d-flex.image-input.align-items-center:nth-child(2) div.credential:nth-child(2) > input.form-control.ng-untouched.ng-pristine.ng-invalid");
	static By passwordField = By.cssSelector("#id_password");
	static By loginButton = By.xpath(
			"/html[1]/body[1]/app-root[1]/app-login[1]/section[1]/div[1]/div[1]/div[2]/form[1]/div[2]/button[1]");

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		Thread.sleep(3000);

		driver.findElement(userNameField).sendKeys("admin");
		driver.findElement(passwordField).sendKeys("Admin@123");
		driver.findElement(loginButton).click();
		Thread.sleep(3000);
		driver.close();

	}

}
