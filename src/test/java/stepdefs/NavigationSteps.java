package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverManager;

import java.time.Duration;

public class NavigationSteps {

    WebDriver driver = DriverManager.getDriver();

    @Given("kullanıcı {string} sitesine gider")
    public void kullanici_siteye_gider(String url) {
        driver.get(url);
        popupKapat();
        cookieKapat();

    }

    @Then("listelenen ürünler {string} ile {string} fiyat aralığında olur")
    public void fiyat_araliginda_olur(String min, String max) {
        String url = driver.getCurrentUrl();
        String beklenen = "prc=" + min + "-" + max;
        Assert.assertTrue("URL beklenen fiyat filtresini içermiyor. Beklenen: " + beklenen + " | URL: " + url, url.contains(beklenen));
    }
    private void popupKapat() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(4))
                    .until(ExpectedConditions.elementToBeClickable(
                            By.className("modal-section-close"))).click();
        } catch (Exception ignored) { }
    }

    private void cookieKapat() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(4))
                    .until(ExpectedConditions.elementToBeClickable(
                            By.id("onetrust-reject-all-handler"))).click();
        } catch (Exception ignored) { }
    }
}
