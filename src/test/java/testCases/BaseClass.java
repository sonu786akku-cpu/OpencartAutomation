package testCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static WebDriver driver;
	public Properties p;
	public Logger logger;

	@SuppressWarnings("deprecation")
	@BeforeClass(groups = { "Sanity", "Regression", "Master" })
	@Parameters({ "browser", "os" })
	public void setup(String br, String os) throws IOException {
		FileInputStream file = new FileInputStream("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);

		logger = LogManager.getLogger(this.getClass());
		if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			if (os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			} else if (os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			} else if (os.equalsIgnoreCase("linux")) {
				capabilities.setPlatform(Platform.LINUX);
			} else {
				System.out.println("No matching platform found");
				return;
			}
			switch (br.toLowerCase()) {
			case "chrome":
				capabilities.setBrowserName("chrome");
				break;
			case "edge":
				capabilities.setBrowserName("Microsoft Edge");
				break;
			case "firefox":
				capabilities.setBrowserName("firefox");
				break;
			default:
				System.out.println("no brower is matching");
				return;
			}
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		}
		if (p.getProperty("execution_env").equalsIgnoreCase("local")) {
		switch (br) {
		case "chrome":
			driver =
			new ChromeDriver();
			break;
		default:
			System.out.println("Invalid browser");
			return;
		}
	}


		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(p.getProperty("appURL"));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	@AfterClass(groups = { "Sanity", "Regression", "Master" })
	public void teardown() {
		driver.quit();
	}

	public String RandomAlpha() {
		@SuppressWarnings("deprecation")
		String random = RandomStringUtils.randomAlphabetic(3);
		return random;

	}

	@SuppressWarnings("deprecation")
	public String randomNumber() {
		String randomNumber = RandomStringUtils.randomNumeric(10);
		return randomNumber;
	}

	@SuppressWarnings("deprecation")
	public String randomAlphaNumeric() {
		String alpha = RandomStringUtils.randomAlphabetic(3);
		String numeric = RandomStringUtils.randomNumeric(3);
		return alpha + numeric;
	}

	public String captureScreenShot(String tname) {
		String timeStamp = new SimpleDateFormat("YYMMddmmhhss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String targetPath = System.getProperty("user.dir") + "\\Screenshot\\" + tname + "_" + timeStamp;
		File targetFile = new File(targetPath);
		sourceFile.renameTo(targetFile);
		return targetPath;

	}

}
