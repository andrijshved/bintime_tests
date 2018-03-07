package nl.centralpoint.basetest;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    @BeforeMethod
    public void init() {
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
    }
}
