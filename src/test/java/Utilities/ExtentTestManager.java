package Utilities;

import com.aventstack.extentreports.ExtentTest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ExtentTestManager {                                     // Manages thread-safe ExtentTest instances for parallel runs.

    private static Map<Long, ExtentTest> extentTestMap = new ConcurrentHashMap<>();

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get(Thread.currentThread().getId());
    }

    public static synchronized void startTest(String testName, String description) {
        ExtentTest test = ExtentManager.getInstance().createTest(testName, description);
        extentTestMap.put(Thread.currentThread().getId(), test);
    }

    public static synchronized void endTest() {
        ExtentManager.getInstance().flush();
    }
}
