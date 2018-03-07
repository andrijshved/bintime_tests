package nl.centralpoint.pages;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class SearchMainPage {

    private SelenideElement searchBar = $(By.xpath("//input[@name='search']"));

    private SelenideElement searchButton = $(".search.orange");

    public void inputProduct(String id) {
        searchBar.shouldBe(visible);
        searchBar.click();
        searchBar.clear();
        searchBar.setValue(id);
    }

    public ResultProductPage clickSearch() {
        searchButton.shouldBe(visible);
        searchButton.click();
        return page(ResultProductPage.class);
    }
}
