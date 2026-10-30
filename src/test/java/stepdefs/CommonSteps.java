package stepdefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.ElementModel;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Config;
import utils.DriverManager;
import utils.ElementReader;

import java.time.Duration;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CommonSteps {

    private final WebDriver driver = DriverManager.getDriver();
    private final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    private By by(String key) {
        return ElementReader.get(key).toBy();
    }

    @When("{string} alanına {string} yazılır")
    public void alana_yazilir(String key, String text) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(by(key)));
        el.clear();
        el.sendKeys(text);
    }

    @When("{string} butonuna tıklanır")
    public void butona_tiklanir(String key) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(by(key)));
        try {
            el.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
        }
    }

    @When("popup kapatılır")
    public void popup_kapatilir() {
        try {
            driver.findElement(By.className("modal-section-close")).click();
        } catch (NoSuchElementException ignored) {

        }
    }

    @Then("{string} görüntülenir")
    public void goruntulenir(String key) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(by(key)));
        assertTrue("'" + key + "' görüntülenmedi", el.isDisplayed());
    }

    @Then("{string} elementi {string} metnini içerir")
    public void elementi_metni_icerir(String key, String beklenen) {
        String metin = wait.until(
                ExpectedConditions.visibilityOfElementLocated(by(key))).getText();
        assertTrue("'" + key + "' metni '" + beklenen + "' içermiyor: " + metin,
                metin.toLowerCase().contains(beklenen.toLowerCase()));
    }

    @Then("{string} elementinin metni {string} olur")
    public void elementinin_metni_olur(String key, String beklenen) {
        By by = by(key);
        wait.until(d -> d.findElement(by).getText().trim().equals(beklenen.trim()));
        assertEquals(beklenen.trim(), driver.findElement(by).getText().trim());
    }
    @Then("adres çubuğu {string} içerir")
    public void adres_cubugu_icerir(String beklenen) {
        wait.until(d -> d.getCurrentUrl().toLowerCase().contains(beklenen.toLowerCase()));
        org.junit.Assert.assertTrue("URL '" + beklenen + "' içermiyor: " + driver.getCurrentUrl(), driver.getCurrentUrl().toLowerCase().contains(beklenen.toLowerCase()));
    }
    @Then("{string} elementinin değeri {string} olur")
    public void elementinin_degeri_olur(String key, String beklenen) {
        By by = by(key);
        wait.until(d -> {
            String v = d.findElement(by).getAttribute("value");
            return v != null && v.trim().equals(beklenen.trim());
        });
        Assert.assertEquals(beklenen.trim(),
                driver.findElement(by).getAttribute("value").trim());
    }
    @When("onboarding kapatılır")
    public void onboarding_kapatilir() {
        try {
            driver.findElement(by("onboarding_primary_btn")).click();
        } catch (org.openqa.selenium.NoSuchElementException ignored) {
        }
    }
    @When("yeni sekmeye geçilir")
    public void yeni_sekmeye_gecilir() {
        var handles = new ArrayList<>(driver.getWindowHandles());
        if (handles.size() > 1) {
            driver.switchTo().window(handles.get(handles.size() - 1));
        }
    }
    @Then("adres çubuğu {string} içerene kadar beklenir")
    public void adres_cubugu_icerene_kadar(String beklenen) {
        wait.until(d -> d.getCurrentUrl().contains(beklenen));
        Assert.assertTrue("URL '" + beklenen + "' içermiyor: " + driver.getCurrentUrl(), driver.getCurrentUrl().contains(beklenen));
    }
    @Then("{string} elementinin metni {string} değildir")
    public void elementinin_metni_degildir(String key, String istenmeyen) {
        By by = by(key);
        wait.until(d -> {
            String t = d.findElement(by).getText().trim();
            return !t.isEmpty() && !t.equals(istenmeyen);
        });

    }
    @When("{string} alanına config {string} değeri yazılır")
    public void alana_config_yazilir(String key, String configKey) {
        org.openqa.selenium.WebElement el =
                wait.until(org.openqa.selenium.support.ui.ExpectedConditions
                        .visibilityOfElementLocated(by(key)));
        el.clear();
        el.sendKeys(Config.get(configKey));
    }
}