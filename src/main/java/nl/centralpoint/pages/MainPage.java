package nl.centralpoint.pages;

import static com.codeborne.selenide.Condition.*;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.page;

public class MainPage {

    public final static int TIMEOUT = 5000;
    private SelenideElement filter;
    private int actualResult;

    public ResultPage clickZoekenButton() {
        filter.scrollTo();
        filter.find("button").shouldNotHave(Condition.cssClass(".inactive")).click();
        return page(ResultPage.class);
    }

    public void chooseFilterValue() {
        List<SelenideElement> products = filter.findAll("li");
        int checkBoxNumber = (int) (Math.random()*products.size());
        SelenideElement checkBox = products.get(checkBoxNumber);
        if (filter.find(".jspDrag").is(exist)) {
            if (!checkBox.is(visible)) {
                WebElement scroll = filter.find(".jspDrag").toWebElement();
                WebElement scrollTrack = filter.find(".jspTrack").toWebElement();
                int heightScrollTrack = Integer.parseInt(scrollTrack.getCssValue("height").replaceAll("px", ""));
                int stepSize = heightScrollTrack/products.size();
                int currentStep = checkBoxNumber;
                Actions action = new Actions(WebDriverRunner.getWebDriver());
                action.dragAndDropBy(scroll, 0, currentStep*stepSize).build().perform();
            }
        }
        checkBox.find("input").waitUntil(visible, TIMEOUT).click();
        this.checkExpectedResult(checkBox);
    }

    public void openFilter() {
        filter.shouldBe(visible);
        filter.hover().click();
        filter.find(".dropdown").waitUntil(visible, TIMEOUT);
    }

    public void selectFilter(String name) {
        for (SelenideElement element : this.getFilters()) {
            if (element.getText().equals(name)) {
                this.filter = element;
                break;
            }
        }
    }

    public void setPriceRange(String lim, String value) {
        SelenideElement field = null;
        switch (lim) {
            case "max": field = $("#priceRangehigh");
                        break;
            case "min": field = $("#priceRangeLow");
                        break;
        }
        field.shouldBe(visible).clear();
        field.setValue(value);
    }

    public void selectRandomFilter() {
        List<SelenideElement> list = new ArrayList<>();
        for (SelenideElement element : this.getFilters()) {
            if (element.getText().equals("Prijs") || element.getText().equals("Sortering")) continue;
            list.add(element);
        }
        int filterNumber = (int) (Math.random()*list.size());
        this.filter = list.get(filterNumber);
        if (filterNumber>5) $("#refineInStock").scrollTo();
    }

    private void checkExpectedResult(SelenideElement element) {
        StringBuilder counter = new StringBuilder(element.find(".number").getText());
        this.actualResult = Integer.parseInt(counter.substring(1, counter.length()-1).trim());
    }

    private List<SelenideElement> getFilters() {
        return $$("#filters .mobileSwitchFiltersOff .filter");
    }

    public int getActualResult() {
        return actualResult;
    }
}
