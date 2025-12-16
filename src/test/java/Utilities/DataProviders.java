package Utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "loginData")
	public String[][] getData() throws IOException {
		String path = ".\\Test_folder\\Automation.xlsx";
		ExcelUtility xcelfile = new ExcelUtility(path);
		int totalrows = xcelfile.getRowCount("Sheet1");
		int totalcol = xcelfile.getCellCount("Sheet1", 1);
		String loginData[][] = new String[totalrows][totalcol];
		for (int i = 1; i < totalrows; i++) {
			for (int j = 0; j < totalcol; j++) {
				loginData[i - 1][j] = xcelfile.getCellData("Sheet1", i, j);
			}
		}
		return loginData;

	}

}
