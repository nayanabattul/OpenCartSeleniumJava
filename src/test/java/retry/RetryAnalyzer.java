package retry;

import java.util.concurrent.atomic.AtomicInteger;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    // Tracks retry count for the current test
    private int retryCount = 0;

    // Maximum retries allowed for each test
    private static final int MAX_RETRY_COUNT = 2;

    // Tracks total retries across the entire suite
    private static final AtomicInteger TOTAL_RETRIES = new AtomicInteger(0);

    @Override
    public boolean retry(ITestResult result) {

        if (retryCount < MAX_RETRY_COUNT) {

            retryCount++;

            // Increment suite-level retry count
            TOTAL_RETRIES.incrementAndGet();

            return true;
        }

        return false;
    }

    // Get total retries performed in the suite
    public static int getTotalRetries() {
        return TOTAL_RETRIES.get();
    }

    // Reset retry count before a new suite execution
    public static void resetTotalRetries() {
        TOTAL_RETRIES.set(0);
    }
}