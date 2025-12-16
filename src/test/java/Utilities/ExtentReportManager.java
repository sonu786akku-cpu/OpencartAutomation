package Utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCases.BaseClass;

public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;

	String repname;

	@Override
	public void onStart(ITestContext testContext) {
		/*
		 * SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss"); Date dt =
		 * new Date(); String CurrentDateStamp = df.format(dt);
		 */

		String dateStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repname = "Test-Report" + dateStamp + ".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repname);
		
		sparkReporter.config().setDocumentTitle("OpenCart Automation Report");
		sparkReporter.config().setReportName("openCart Functional Test ");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "opencart");
		extent.setSystemInfo("userName", System.getProperty("user.name"));
		extent.setSystemInfo("Environment ", "QA");

		String operatingSystem = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operatingsystem", operatingSystem);
		String Browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("browser", Browser);

		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName() + "Passed Successfully");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName() + "Test Failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
	try {	
		String imagePath =new BaseClass().captureScreenShot(result.getName());
		test.addScreenCaptureFromPath(imagePath);
	}catch(Exception e1) {
		e1.printStackTrace();
	}
}
	public void onTestSkip(ITestResult result) {
		test = extent.createTest(result.getClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName() + "Test skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());

	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
		String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repname;
		File ExtentReport = new File(pathOfExtentReport);

		try {
			Desktop.getDesktop().browse(ExtentReport.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
