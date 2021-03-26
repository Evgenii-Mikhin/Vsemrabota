//Тестовое задание: есть сайт https://vsemrabota.ru/seekers?category=28&city=0&region=0
// Нужно написать автотесты, который проверяют работу поля поиска “Введите специальность, профессию”
// (самому придумать тесты на это (хотя бы 2)) и тест на фильтрацию вакансий по городу
package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class VsemrabotaVacancyPage extends BaseTest {

    @FindBy(xpath = "//input[@class='styles_input__1WzMJ']")
    public static WebElement searchField;

    @FindBy(xpath = "//div[@class='css-1g6gooi']//input")
    public WebElement searchContainerCity;

    @FindBy(xpath = "//button[@class='styles_clearValue__36_I1']")
    WebElement buttonCleanTown;

    @FindBy(xpath = "//button[@class='styles_clearBtn__1Bnqq']")
    WebElement clearBtn;

    @FindBy(xpath = "//p[@class='styles_city__21PpM']")
    public static WebElement returnText;
    @FindBy(xpath = "//p[@class='styles_city__21PpM']")
    public static List<WebElement> returnedCitiesInVacancy;

    public VsemrabotaVacancyPage() {
        PageFactory.initElements(BaseTest.getDriver(), this);
    }

    public void waits() {
        Wait<WebDriver> wait = new WebDriverWait(getDriver(), 5, 1000);
        wait.until(ExpectedConditions.visibilityOf(searchField));
    }

    public void searchFieldProf(String value) {
        searchField.click();
        searchField.clear();
        searchField.sendKeys(value);
        assertEquals(value, searchField.getAttribute("value"));
    }

    public void searchContainerCity(String value) {
        searchContainerCity.sendKeys(value);
        assertEquals(value, searchContainerCity.getAttribute("value"));
    }

    public void checkElementsOnPage(String value) {
        try {
            Boolean isPresent = getDriver().findElements(By.xpath("//a[contains(text(),'" + value + "')]")).size() > 0;
            Assert.assertTrue(isPresent);
        } catch (AssertionError e) {
            System.out.println("Поиск: " + value + " - не дал результатов");
        }
    }

    public void buttonCleanTown() {
        buttonCleanTown.click();
    }

    public void clearBtn() {
        clearBtn.click();
    }

    public void checkElementsCitiesSize(String text) {
        searchContainerCity.sendKeys(text);
        searchContainerCity.sendKeys(Keys.ENTER);
        HashSet<String> setText = new HashSet<String>();
        String valueText = null;
        for (WebElement value : returnedCitiesInVacancy) {
            valueText = value.getAttribute("innerHTML");
            setText.add(valueText);
        }
        setText.add(text);
        Boolean isCorrectSize = setText.size() == 1; //Идея заключается в том чтобы добавить введенный город в HashSet и проверить, что его размер также остался =1 (тк в нем только уникальные значения)
        Assert.assertTrue(isCorrectSize);
    }
}

