package com.facebook.genericPage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MasterPage {

	public static WebDriver driver;
	public Properties prop;
	public Properties or;
	public Properties td;

	// Constructor Implementation
	public MasterPage() throws Exception {

		// configuration properties file implementation
		FileInputStream ip = new FileInputStream(".\\src\\com\\facebook\\repository\\config.properties");
		prop = new Properties();
		prop.load(ip);

		// locators properties file implementation
		FileInputStream fs = new FileInputStream(".\\src\\com\\facebook\\repository\\locators.properties");
		or = new Properties();
		or.load(fs);

		// testdata properties file implementation
		FileInputStream ts = new FileInputStream(".\\src\\com\\facebook\\repository\\locators.properties");
		td = new Properties();
		td.load(ts);

		// launching Browsers
		/*
		 * if(prop.getProperty("browser").equalsIgnoreCase("chrome")){
		 * System.setProperty("webdriver.chrome.driver",
		 * ".\\src\\com.facebook.drivers\\chromedriver.exe"); driver= new
		 * ChromeDriver(); }else
		 * if(prop.getProperty("browser").equalsIgnoreCase("edge")) {
		 * System.setProperty("webdriver.edge.driver",
		 * ".\\src\\com.facebook.drivers\\edgedriver.exe"); driver= new EdgeDriver(); }
		 */

		// launching Browsers - chrome/firefox/edge
		if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (prop.getProperty("browser").equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else {
			System.out.println("No browser details found");
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));

	}

}
