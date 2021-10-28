package util;

import BaseTest.Base;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class Methods extends Base {
    public static String text = "";

    public WebElement findElement(String key) {
        try {
            WebElement element = (new WebDriverWait(driver, 20, 300))
                    .until(ExpectedConditions.elementToBeClickable(By.cssSelector(key)));
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                    element);
            return element;

        } catch (Exception ex) {
            Assert.fail("" + key + "'li element 10 saniye boyunca arandı fakat bulunamadı..!!!");
            return null;
        }
    }

    public List<WebElement> findElements(String key) {
        try {
            WebDriverWait waitForFormLabel = new WebDriverWait(driver, 30);
            List<WebElement> elements = driver.findElements(By.cssSelector(key));
            waitForFormLabel.until(ExpectedConditions.visibilityOfAllElements(elements));
            return elements;

        } catch (Exception ex) {
            Assert.fail("" + key + "'li element 10 saniye boyunca arandı fakat bulunamadı..!!!");
            return null;
        }
    }

    public void clickToElement(String key) {
        WebElement element = findElement(key);
        //scrollToElement(element);
        waitSecond(500);
        element.click();
    }

    public void sendKeysToElement(String key, String text) {
        WebElement element = findElement(key);
        //scrollToElement(element);
        waitSecond(500);
        element.clear();
        element.sendKeys(text);
    }

    public String getTextElement(WebElement element) {
        //scrollToElement(element);
        waitSecond(500);
        String elemaninTexti = "";
        elemaninTexti = element.getText();
        System.out.println("Elemanın Text değeri: " + elemaninTexti);
        return elemaninTexti;
    }

    public void waitSecond(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getTextControl(String key, String text) {
        WebElement element = findElement(key);
        String elemaninTexti = element.getText();
        System.out.println("Elemanın Text değeri: " + elemaninTexti);
        Assert.assertEquals("Eleman girilen texti içermiyor..!!!", elemaninTexti, text);
        System.out.println("Girilen değer ile Elementin texti uyuşuyor..");
    }


    public void checkTextAttribute(String key, String attr, String text) {
        System.out.println("Kontrol Edilecek Alanın Texti: " + findElement(key).getAttribute(attr));
        Assert.assertTrue(key + " alanında " + text + " yazmıyor!",
                findElement(key).getAttribute(attr).contains(text));
    }

    public void getLinkControl(String text) {
        String sayfanınLinki = driver.getCurrentUrl();
        System.out.println("Sayfanın Linki: " + sayfanınLinki);
        Assert.assertTrue("Sayfa Linki girilen texti içermiyor..!!!", sayfanınLinki.contains(text));
        System.out.println("Sayfa Linki ile Girilen text uyuşuyor..");
    }

    public void textControl(String key) {
        WebElement element = findElement(key);
        Assert.assertTrue("Eleman texti kaydedilen değer ile uyuşmuyor..!!!", getTextElement(element).contains(text));
        System.out.println("Girilen değer ile Elementin texti uyuşuyor..");
    }

    public int randInt(int low, int high) {
        Random r = new Random();
        int result = r.nextInt(high - low) + low;
        System.out.println(result + ". Eleman secildi.");
        return result;
    }

    public void randomClickAndSaveValue(String key) {
        List<WebElement> elements = findElements(key);
        int randValue = randInt(0, elements.size());
        WebElement element = elements.get(randValue);
        text = element.findElement(By.cssSelector("div[class='price'] div:not([class='discount-percent']):last-child")).getText();
        System.out.println("Seçilen elemanın turarı: " + text);
        element.click();
        System.out.println(randValue + ". Elemente tıklandı..");

    }
    public void login(String username, String password) {
        try{
            WebElement usernameInput = findElement("LoginEmail");
            WebElement passwordInput = findElement("Password");

            usernameInput.sendKeys(username);
            passwordInput.sendKeys(password);

            clickToElement("loginLink");

        } catch (Exception ex) {
            Assert.fail("Kullanici adi:" + username + " , Sifre:" + password + "konfigurasyonu yanlis");

        }

    }
}
