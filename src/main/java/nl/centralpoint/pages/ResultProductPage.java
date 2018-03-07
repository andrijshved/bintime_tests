package nl.centralpoint.pages;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class ResultProductPage {

    public static final int TIMEOUT = 5000;

    private SelenideElement productTitle = $(".title h1");
    private SelenideElement productCode = $(".title span");

    public void waitForUpload() {
        if (productTitle.is(exist)) productTitle.waitUntil(visible, TIMEOUT);
        if (productCode.is(exist)) productCode.waitUntil(visible, TIMEOUT);
    }

    public String getResult() {
        String result = "null";
        if (productCode.is(exist)) {
            result = productCode.getText();
        }
        return result;
    }
}
