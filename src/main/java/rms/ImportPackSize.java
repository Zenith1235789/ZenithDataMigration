package rms;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ImportPackSize {
	String url = "https://rms-trial3.annofi.com/login?returnUrl=/";
	static WebDriver driver;
	static ReadClass readClass;

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
//		ChromeDriver driver = new ChromeDriver(options);
		driver = new ChromeDriver(options);
		ImportPackSize LoginUser = new ImportPackSize();
		LoginUser.loginUser("admin", "admin123");
		
		readClass = new ReadClass("C:\\Users\\Dell\\Desktop\\ImportPackSize.xlsx");
		int sizeOfElement = readClass.getRowCount(0);
		for(int i = 0; i<sizeOfElement;i++) {
			LoginUser.fillZone(i);
		}
	}
		

	private void fillZone(int i) throws InterruptedException {
		
		driver.findElement(By.cssSelector("#content-top-pagination > app-pack-size > div:nth-child(1) > div.d-flex.top-section > div > button")).click();
		driver.findElement(By.cssSelector("body > ngb-modal-window > div > div > div.modal-body > form > div:nth-child(1) > input")).sendKeys(readClass.getData(0, i, 0));
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("body > ngb-modal-window > div > div > div.modal-body > form > div:nth-child(2) > input")).sendKeys(readClass.getData(0, i, 1));
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("body > ngb-modal-window > div > div > div.modal-body > form > div:nth-child(3) > input")).sendKeys(readClass.getData(0, i, 2));
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("body > ngb-modal-window > div > div > div.modal-footer > button")).click();
		Thread.sleep(2000);
	}


	private void loginUser(String username, String password) throws InterruptedException {
		driver.get(url);
		driver.manage().window().maximize();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(
				"#again-new-design > div > div.salesRM-card > div.login-part > form > div.user-pass > div:nth-child(1) > div > div.credential > input"))
				.sendKeys(username);
		driver.findElement(By.cssSelector("#id_password")).sendKeys(password);
		driver.findElement(By.cssSelector(
				"#again-new-design > div > div.salesRM-card > div.login-part > form > div.login-btn > button")).click();
		Thread.sleep(3000);

		// click on master
		driver.findElement(
				By.cssSelector("#mat-expansion-panel-header-12 > span.mat-content.ng-tns-c95-28 > mat-panel-title"))
				.click();
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		javascriptExecutor.executeScript("document.querySelector('#navigation-bar >div').scrollBy(0,600)");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//*[@id=\"mat-expansion-panel-header-13\"]/span[1]/mat-panel-title")));
		driver.findElement(By.xpath(
				"//*[@id=\"mat-expansion-panel-header-13\"]/span[1]/mat-panel-title"))
				.click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Product Pack")).click();
		Thread.sleep(2000);
	}

}