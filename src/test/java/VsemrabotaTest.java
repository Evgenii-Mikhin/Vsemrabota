import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.Keys;
import pages.VsemrabotaVacancyPage;
import util.BaseTest;
import static org.junit.Assert.assertEquals;

public class VsemrabotaTest extends BaseTest {

    @Test
    public void testVsemrabotaTestSmoke(){
        VsemrabotaVacancyPage vsemrabotaVacancyPage = new VsemrabotaVacancyPage() ;

        vsemrabotaVacancyPage.searchFieldProf("Водитель");
        vsemrabotaVacancyPage.searchContainerCity ("Москва");
        vsemrabotaVacancyPage.searchContainerCity.sendKeys(Keys.ENTER);

    }

    @Test
    public void testVsemrabotaTestClearinglines(){
        VsemrabotaVacancyPage vsemrabotaVacancyPage = new VsemrabotaVacancyPage() ;

        vsemrabotaVacancyPage.searchContainerCity ("Казань");
        vsemrabotaVacancyPage.searchContainerCity.sendKeys(Keys.ENTER);
        vsemrabotaVacancyPage.searchFieldProf("Менеджер");
        vsemrabotaVacancyPage.buttonCleanTown();
        assertEquals("Не сработала кнопка отчистки поля 'Все города'", "", vsemrabotaVacancyPage.searchContainerCity.getAttribute("value"));
        vsemrabotaVacancyPage.clearBtn();
        assertEquals("Не сработала кнопка отчистки поля 'Введите профессию'", "", vsemrabotaVacancyPage.searchField.getAttribute("value"));

    }
    @Test
    public void testVsemrabotaTestsFilters() {
        VsemrabotaVacancyPage vsemrabotaVacancyPage = new VsemrabotaVacancyPage();

        vsemrabotaVacancyPage.checkElementsCitiesSize("Москва");
    }
}