package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserUtils {

    public static void killBrowsers()
    {
        try {
            Runtime.getRuntime().exec("TASKKILL -f -im chromedriver.exe /T");
            Runtime.getRuntime().exec("TASKKILL -f -im geckodriver.exe /T");
            Runtime.getRuntime().exec("TASKKILL -f -im msedgedriver.exe /T");
        }

        catch (Exception e2)
        {
            e2.printStackTrace();
        }
    }

    public static WebDriver getDriver(String browserName)
    {
        return switch (browserName.toUpperCase())
        {
            case "CHROME" -> {
                WebDriver driver=new ChromeDriver();
                driver.manage().window().maximize();

                yield driver;
            }

            case "FIREFOX" -> {
                WebDriver driver=new FirefoxDriver();
                driver.manage().window().maximize();

                yield driver;
            }

            case "EDGE" -> {
                WebDriver driver=new EdgeDriver();
                driver.manage().window().maximize();

                yield driver;
            }

            default -> {
                throw new RuntimeException("Invalid Browser Name");
            }
        };
    }
}
