package loginScenarios;

import org.testng.annotations.Factory;

public class LoginFactory {

    //Below concept is called as a factory design pattern in TestNG
    @Factory //Factory is used to run the same test case with different set of actions at a class level
    public Object[] loginFactory()
    {
        return new Object[]
        {
            new LoginTestCases("CHROME"),
            new LoginTestCases("FIREFOX"),
            new LoginTestCases("EDGE")
        };
    }
}
