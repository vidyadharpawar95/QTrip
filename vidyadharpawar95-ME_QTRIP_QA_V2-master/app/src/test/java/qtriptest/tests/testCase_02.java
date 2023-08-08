package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HomePage;
import java.net.MalformedURLException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class testCase_02 {
    static RemoteWebDriver driver;
    public String lastUsername;
    static ExtentReports report;
    static ExtentTest test;
    static ReportSingleton rs1;

    // Method to help us log our Unit Tests
    public static void logStatus(String type, String message, String status) {
        System.out.println(String.format("%s |  %s  |  %s | %s",
                String.valueOf(java.time.LocalDateTime.now()), type, message, status));
    }

    // Iinitialize webdriver for our Unit Tests
    @BeforeSuite(alwaysRun = true, enabled = true)
    public static void createDriver() throws MalformedURLException {
        logStatus("driver", "Initializing driver", "Started");
        driver = DriverSingleton.getDriverInstance("chrome");
        rs1 = ReportSingleton.getInstanceOfSingleTonReportClass();
        test = rs1.getTest();
        logStatus("driver", "Initializing driver", "Success");
    }

    @Test(dataProvider = "DatasetsforQTrip", dataProviderClass = DP.class, enabled = true,
            description = "verify Search and Filter flow", priority = 2,
            groups = {"Search and Filter flow"})
    public void TestCase02(String CityName, String Category_Filter, String DurationFilter,
            String filterdResult, String unfilteredResult) throws InterruptedException {

        HomePage home = new HomePage(driver);
        home.navigateToHomePage();
        home.searchCity(CityName);
        Assert.assertTrue(home.isCityNotFound() || home.isCityFound());
        home.selectCity();

        AdventurePage adventure = new AdventurePage(driver);
        adventure.setCatrgoryFilter(Category_Filter);
        adventure.setDurationFilter(DurationFilter);
        adventure.verifyAdventureContents(filterdResult);
        adventure.verifyAdventureContents(unfilteredResult);
        test.log(LogStatus.PASS, "Test Case 2 Passed");

    }

    @AfterSuite
    
        public void tearDown() {
            if (driver != null) {
                 ReportSingleton.getInstanceOfSingleTonReportClass().getReport().flush();
                driver.quit();
            }
        }
        // if (rs1 != null) {
        //     rs1.closeReport();
        // }
    }

