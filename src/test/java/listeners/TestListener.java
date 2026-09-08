package listeners;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import testbase.BaseTest;
import utilities.ExtentManager;

public class TestListener implements ITestListener {

private static ExtentReports extent =
        ExtentManager.getExtentReports();

private static ThreadLocal<ExtentTest> test =
        new ThreadLocal<>();


@Override
public void onTestStart(ITestResult result) {

    ExtentTest extentTest =
            extent.createTest(result.getName());

    test.set(extentTest);
}


@Override
public void onTestSuccess(ITestResult result) {

    test.get().pass("Test Passed");
}


@Override
public void onTestFailure(ITestResult result) {

    // Mark test as failed in Extent Report
    test.get().fail(result.getThrowable());

    // Get driver from BaseTest
    WebDriver driver = BaseTest.getDriver();

    if (driver != null) {

        TakesScreenshot screenshot =
                (TakesScreenshot) driver;

        File source =
                screenshot.getScreenshotAs(OutputType.FILE);

        String testName = result.getName();

        File destination = new File(
                "test-output/screenshots/"
                        + testName
                        + "_"
                        + System.currentTimeMillis()
                        + ".png"
        );

        try {

            Files.createDirectories(
                    destination.getParentFile().toPath()
            );

            Files.copy(
                    source.toPath(),
                    destination.toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );

            System.out.println(
                    "Screenshot captured: "
                            + destination.getAbsolutePath()
            );

            // Attach screenshot to Extent Report
            test.get().fail(
                    "Screenshot captured",
                    MediaEntityBuilder
                            .createScreenCaptureFromPath(
                                    destination.getAbsolutePath()
                            )
                            .build()
            );

        } catch (IOException e) {

            e.printStackTrace();

            test.get().warning(
                    "Unable to capture screenshot: "
                            + e.getMessage()
            );
        }
    }
}


@Override
public void onTestSkipped(ITestResult result) {

    test.get().skip("Test Skipped");
}


@Override
public void onFinish(ITestContext context) {

    extent.flush();

    test.remove();
}


}
