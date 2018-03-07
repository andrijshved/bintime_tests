package nl.centralpoint.tests;

import nl.centralpoint.basetest.BaseTest;
import nl.centralpoint.pages.ResultProductPage;
import nl.centralpoint.pages.SearchMainPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

public class ProdIdTest extends BaseTest{

    public final static String URL = "https://www.centralpoint.nl/";

    @Test(dataProvider = "prodIdList")
    public void prodIdTest(String productId) {
        SearchMainPage searchMainPage = open(URL, SearchMainPage.class);
        searchMainPage.inputProduct(productId);
        ResultProductPage resultProductPage = searchMainPage.clickSearch();
        resultProductPage.waitForUpload();
        Assert.assertTrue(resultProductPage.getResult().contains(productId));
    }

    @DataProvider(name = "prodIdList")
    public Object[][] getData() {
        return new Object[][] {
                {"J153289"},
                {"MQ3D2ZD/A"},
                {"L36852-H2436-M101"},
                {"1WZ03EA#ABH"},
                {"875839-425"},
                {"C5J91A#B19"},
                {"FM32SD45B/10"},
                {"204446-101"},
                {"GV-N710D3-1GL"},
                {"02G-P4-6150-KR"}
        };
    }
}
