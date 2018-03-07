package nl.centralpoint.tests;

import nl.centralpoint.basetest.BaseTest;
import nl.centralpoint.pages.MainPage;
import nl.centralpoint.pages.ResultPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;

import static com.codeborne.selenide.Selenide.open;

public class PrijsFilterTest extends BaseTest {

    public final static String URL = "https://www.centralpoint.nl/monitoren/";

    @Test
    public void prijsFilterTest() {
        MainPage mainPage = open(URL, MainPage.class);
        mainPage.selectFilter("Prijs");
        mainPage.openFilter();
        mainPage.setPriceRange("min", "1000");
        mainPage.setPriceRange("max", "5000");
        ResultPage resultPage = mainPage.clickZoekenButton();
        Assert.assertTrue(Collections.min(resultPage.countPrices())>1000);
    }
}
