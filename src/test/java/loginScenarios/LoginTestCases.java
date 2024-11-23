package loginScenarios;

import framework.BrowserUtils;
import io.qameta.allure.*;
import org.openqa.selenium.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Story("Login Scenarios")
@Epic("EPIC-101: Login to the Application")
public class LoginTestCases {

    String browserName="";
    WebDriver driver=null;

    public LoginTestCases()
    {

    }

    public LoginTestCases(String browserName)
    {
        this.browserName=browserName;
    }

    //Purpose of Data Provider is to provide the data to the test method
    @DataProvider(name = "loginData")
    public Object[][] getData()
    {
        Object[][] o1=new Object[][]{
                {"student","Password123"},
                {"admin","Password123"},
                {"admin","admin123"},
                {"student","admin123"}
        };

        return o1;
    }

    @BeforeClass //Before executing the test cases in the current class, this method will be executed
    public void beforeClass()
    {
        BrowserUtils.killBrowsers();
        if(browserName.isBlank() || browserName.isEmpty())
            browserName="Chrome";

        driver= BrowserUtils.getDriver(browserName);
    }

    @Severity(SeverityLevel.TRIVIAL)
    @Link(name = "Practise Test Automation", url = "https://practicetestautomation.com/practice-test-login/")
    //loginToTheApplication is a test method ---> It is associated with @Test Annotation
    @Test(description = "Performing Login Scenarios",dataProvider = "loginData") //Represents a test case
    public void loginToTheApplication(String userName, String password)
    {
        //From selenium 4.11 --> It has inbuilt selenium manager, which will download all the browser realted files
        //and sets the path of the driver automatically

        //Launching the Login URL
        driver.get("https://practicetestautomation.com/practice-test-login/");

        Allure.addAttachment("URL", "https://practicetestautomation.com/practice-test-login/");

        //In Selenium --> Element means a textbox, radio button, checkbox etc....
        //driver.findElement ---> In the Current Browser, find for the particular webelement in the current web page

        //Syntax of finding an element on the basis of id:
        //driver.findElement(By.id(value));

        WebElement txt_UserName=driver.findElement(By.id("username"));
        txt_UserName.sendKeys(userName); //.sendKeys() is used to enter the value in the textbox

        Allure.addAttachment("Username", "Entered Username: "+userName);

        WebElement txt_Password=driver.findElement(By.id("password"));
        txt_Password.sendKeys(password);

        Allure.addAttachment("Password", "Entered Password: "+password);

        WebElement btn_Submit=driver.findElement(By.id("submit"));
        btn_Submit.click(); //.click() is used to click on the button

        Allure.addAttachment("Button", "Clicked on the Submit Button");

//        (TakesScreenshot) --> Capture the screenshot
//        (TakesScreenshot)driver).getScreenshotAs(OutputType.FILE) --> Captures the screenshot of the browser and stores it in the form of a file

        Allure.addAttachment("Login_Screen", "image/png", ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES).toString());
    }

    @AfterClass //After executing the test cases in the current class, this method will be executed
    public void afterClass()
    {
        driver.quit();
    }
}
