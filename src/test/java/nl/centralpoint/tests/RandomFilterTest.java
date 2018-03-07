package nl.centralpoint.tests;

import nl.centralpoint.basetest.BaseTest;
import nl.centralpoint.pages.MainPage;
import nl.centralpoint.pages.ResultPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.*;

public class RandomFilterTest extends BaseTest{

    public final static String URL = "https://www.centralpoint.nl/notebooks-laptops/";

    @Test
    public void randomFilterTest() {
        MainPage mainPage = open(URL, MainPage.class);
        mainPage.selectRandomFilter();
        mainPage.openFilter();
        mainPage.chooseFilterValue();
        ResultPage resultPage = mainPage.clickZoekenButton();
        resultPage.checkResults();
        Assert.assertEquals(mainPage.getActualResult(), resultPage.getActualResult());
    }
}
