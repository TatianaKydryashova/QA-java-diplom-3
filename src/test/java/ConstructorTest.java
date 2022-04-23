import com.UserOperations;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import java.util.Map;
import static org.junit.Assert.assertTrue;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class ConstructorTest {
    public UserOperations userOperations;
    public Map<String, String> userData;

    @Before
    public void setup() {
        userOperations = new UserOperations();
        userData = userOperations.register();
        //для запуска тестов в Яндекс браузере - убрать комментарий
        //System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
        Configuration.browserSize = "1920x1080";
    }

    @After
    public void tearDown() {
        userOperations.delete();
        getWebDriver().quit();
    }

    @Test
    @DisplayName("Checking go On Sauce tabs")
    public void clickOnSauceButtonTest() {
        MainPage mainPage = open(MainPage.URL, MainPage.class);
        mainPage.clickLoginButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.login(userData.get("email"), userData.get("password"));
        mainPage.clickSaucesButton();
        assertTrue("Isn't sauces tab", mainPage.isSaucesTabText());
    }

    @Test
    @DisplayName("Checking go On Fillings tabs")
    public void clickOnFillingsButtonTest() {
        MainPage mainPage = open(MainPage.URL, MainPage.class);
        mainPage.clickLoginButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.login(userData.get("email"), userData.get("password"));
        mainPage.clickFillingsButton();
        assertTrue("Isn't fillings tab", mainPage.isFillingsTabText());
    }

    @Test
    @DisplayName("Checking go On Bread tabs")
    public void clickOnBreadButtonTest() {
        MainPage mainPage = open(MainPage.URL, MainPage.class);
        mainPage.clickLoginButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.login(userData.get("email"), userData.get("password"));
        mainPage.clickBunsButton();
        assertTrue("Isn't buns tab", mainPage.isBunsTabText());
    }
}
