package com.facebook.genericPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.System.Logger;
import java.nio.channels.SelectableChannel;
import java.security.Identity;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.BreakClear;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

public class CommonMethods extends MasterPage {

	public CommonMethods() throws Exception {
		super();
	}

	// get Text to Web Element
	public void getWebElementText(String xpathkey) {
		String getTextOfWebElementString = driver.findElement(By.xpath(or.getProperty(xpathkey))).getText();
		System.out.println(getTextOfWebElementString);
	}

	// click method
	public void clickWebElement(String xpathkey) {
		driver.findElement(By.xpath(or.getProperty(xpathkey))).click();
	}

	// clear method
	public void clearData(String xpathkey) {
		driver.findElement(By.xpath(or.getProperty(xpathkey))).clear();
	}

	// Enter method
	public void enterData(String xpathkey, String testData) {
		driver.findElement(By.xpath(or.getProperty(xpathkey))).sendKeys(td.getProperty(testData));
	}

	// Mouse Hover
	public void moveToElement(String xpathkey) {
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath(or.getProperty(xpathkey)))).build().perform();
	}

	// Click List of Web element
	public void clickListElement(String xpathkey, String testdata) {
		List<WebElement> listofElements = driver.findElements(By.xpath(or.getProperty(xpathkey)));
		for (int i = 0; i < listofElements.size(); i++) {
			if (listofElements.get(i).getText().equalsIgnoreCase(td.getProperty(testdata))) {
				listofElements.get(i).click();
			}
		}
	}

	// select dropdown value using visible text
	public void selectDropdownValue(String xpathkey, String testdata) {
		WebElement ele = driver.findElement(By.xpath(or.getProperty(xpathkey)));
		Select webEle = new Select(ele);
		webEle.selectByVisibleText(td.getProperty(testdata));

	}

	// Read Excel Data
	public void readExcelData(String xpathkey, int rowNo, int coloumnNo, String excelSheetName) {
		File src = new File(".\\src\\com.facebook.resources\\Excel Test Data.xlsx"));
		FileInputStream fis = new FileInputStream(src);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet(prop.getProperty(excelSheetName));
		System.out.println(sh.getSheetName());
		String abc = sh.getRow(rowNo).getCell(coloumnNo).getStringCellValue();
		driver.findElement(By.xpath(or.getProperty(xpathkey))).sendKeys(abc);
	}

	// Handling Explicit wait - visibilityOfElementLocated
	public void handleExplicitWait_visibilityOfElementLocated(String xpathkey, String testdata) {
		WebDriverWait wt = new WebDriverWait(driver, 30);
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(or.getProperty(xpathkey))))
				.sendKeys(td.getProperty(testdata));
	}

	// Handling Explicit wait - elementToBeClickable
	public void handleExplicitWait_elementToBeClickable(String xpathkey) {
		WebDriverWait wt = new WebDriverWait(driver, 30);
		wt.until(ExpectedConditions.elementToBeClickable(By.xpath(or.getProperty(xpathkey)))).click();
	}
	
	// Handle log file
	public void handleLogger(String logClassName, String loggerText) {
		Logger logger = Logger.getLogger(logClassName);
		PropertyConfigurator.configure(prop.getProperty("log4JPropertiesFileLoc"));
	}
	
	// Capture Screenshot
	public void captureScreenshot(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			// Create ref of TakesScreenshot Interface and type casring
			TakesScreenshot ts = (TakesScreenshot) driver; //Typecasting of 2 interfaces
			
			// Use getScreenshotAs() to capture the screenshot in file format
			// getScreenshotAs() method return tyoe = FILE
			File sourceFile = ts.getScreenshotAs(OutputType.FILE);
			
			//copy the file to specific location
			File destFolder = new File("./screenshots/" + result.getName() + ".png");
			FileUtils.copyFile(sourceFile, destFolder);
			System.out.println(result.getName() + "method() failed, screenshot captured");
		}
	}
}
