package com.frame.listeners;


import com.frame.TestBase.ESCTestBase;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.util.*;


public class TestListener implements ITestListener {


    @Override
    public void onFinish(ITestContext testContext) {
        // super.onFinish(testContext);
        ArrayList<ITestResult> testsToBeRemoved = new ArrayList<ITestResult>();
        // collect all id's from passed test
        Set<Integer> passedTestIds = new HashSet<Integer>();
        for (ITestResult passedTest : testContext.getPassedTests().getAllResults()) {

            System.out.println(passedTest.getEndMillis());
            passedTestIds.add(getId(passedTest));
        }

        Set<Integer> failedTestIds = new HashSet<Integer>();
        for (ITestResult failedTest : testContext.getFailedTests().getAllResults()) {

            // id = class + method + dataprovider
            int failedTestId = getId(failedTest);
            if (failedTestIds.contains(failedTestId) || passedTestIds.contains(failedTestId)) {
                //testsToBeRemoved.add(failedTest);
                System.out.println("delete ===="+failedTest.getName());
            } else {
                failedTestIds.add(failedTestId);
            }
        }
        Set<Integer> skipedTestIds=new HashSet<Integer>();

        for (ITestResult skipedTest : testContext.getSkippedTests().getAllResults()) {

            // id = class + method + dataprovider
            int skipedTestId = getId(skipedTest);

            if (failedTestIds.contains(skipedTestId) || passedTestIds.contains(skipedTestId)) {
                testsToBeRemoved.add(skipedTest);
                //System.out.println("delete ===="+skipedTest.getName());
            } else {
            	skipedTestIds.add(skipedTestId);
            }
        }
   //      finally delete all tests that are marked
        for (Iterator<ITestResult> iterator = testContext.getSkippedTests().getAllResults().iterator(); iterator
                .hasNext();) {
            ITestResult testResult = iterator.next();
            if (testsToBeRemoved.contains(testResult)) {

                System.out.println("Remove repeat Fail Test: " + testResult.getName());
                iterator.remove();
            }
        }
    }

    private int getId(ITestResult result) {
        int id = result.getTestClass().getName().hashCode();
        id = id + result.getMethod().getMethodName().hashCode();
        id = id + (result.getParameters() != null ? Arrays.hashCode(result.getParameters()) : 0);
        return id;
    }

    public void onTestStart(ITestResult result) {
    	Reporter.log(result.getName() + " Started");
    }

    public void onTestSuccess(ITestResult result) {
    	Reporter.log(result.getName() + " Success");
    	//写入数据库，写入改测试用例执行成功的结果

    }

    public void onTestFailure(ITestResult result) {
		Reporter.log(result.getName() + " Failure");
//        Retry retry = (Retry) result.getMethod().getRetryAnalyzer();
//        retry.resetRetrycount();
		//result.getInstance();
        ESCTestBase t = (ESCTestBase)result.getInstance();
        String filename = result.getName()+".png";
        String screenShot = t.driver.getScreenShot(filename);
        //这里是extentreport的失败截图
		Reporter.log("<a href=snapshot/" + screenShot + " target=_blank><img src=snapshot/" + screenShot
				+ " style=width:30px;height:30px img/>失败截图</a>", true);
		/*try {
			//这里是allure测试报告的失败截图
			Allure.addAttachment("失败截图", new FileInputStream(new File("report/snapshot/"+screenShot)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    }

    public void onTestSkipped(ITestResult result) {
    	Reporter.log(result.getName() + " Skipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onStart(ITestContext context) {

    }
}
