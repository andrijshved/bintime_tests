package nl.centralpoint.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ResultPage {

    public final static int TIMEOUT = 5000;
    private int actualResult;

    public void checkResults() {
        $(".title h1").waitUntil(visible, TIMEOUT);
        if ($(".title span.productCode").is(exist)) {
            this.actualResult = 1;
            $(".title .productCode").waitUntil(visible, TIMEOUT);
        } else {
            StringBuilder actual = new StringBuilder($(".title h1").getText());
            int first = actual.indexOf("(");
            StringBuilder temp = new StringBuilder(actual.substring(first).trim());
            int last = temp.lastIndexOf(" ");
            this.actualResult = Integer.parseInt(temp.substring(1, last).trim());
        }
    }

    public List<SelenideElement> getProductsResultSet() {
        List<SelenideElement> list = new ArrayList<SelenideElement>();
        List<SelenideElement> pagingTabs = $$(".paging li");
        for (int i=(pagingTabs.size()/2-1); i>=0; i--) {
            int index = list.size();
            pagingTabs.get(i).shouldBe(visible).click();
            pagingTabs.get(i).shouldHave(cssClass("active"));
            List<SelenideElement> temp = $$(".card.landscape.wide");
            list.addAll(index, temp);
        }
        return list;
    }

    public List<Double> countPrices() {
        List<Double> prices = new ArrayList<>();
        for (SelenideElement element: getProductsResultSet()) {
            prices.add(Double.parseDouble(element
                    .find(".price.priceIncl span")
                    .getText()
                    .replace(",", ".")
                    .replaceAll("-", "00")));
        }
        return prices;
    }

    public int getActualResult() {
        return actualResult;
    }
}
