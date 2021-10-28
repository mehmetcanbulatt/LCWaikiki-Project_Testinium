package Scenario;

import BaseTest.Base;
import org.junit.Test;
import util.Methods;


public class ScenarioTest extends Base {
    Methods steps = new Methods();


    @Test
    public void TestScenario() {
        steps.getLinkControl("lcwaikiki.com/tr-TR/TR"); // Ana sayfadaki title kontrol edilir.
        steps.clickToElement("span[class='dropdown-label']"); // Giris yap butonuna tiklanir
        steps.sendKeysToElement("LoginEmail","mehmetcanbulat10@gmail.com");
        steps.sendKeysToElement("Password","1234MCMC");
        steps.clickToElement("loginLink");
        steps.waitSecond(2000);
        steps.sendKeysToElement("input[id='search_input']", "pantolon"); // arama alanına text yazılır.
        steps.clickToElement("button[class='searchButton']"); // ara butonuna tıklanır.
        steps.clickToElement("a[class='lazy-load-button']"); // sayfanın sonuna scroll olup daha fazla gör butonuna tıklanır.
        steps.randomClickAndSaveValue("div[class*='product-item-wrapper']"); // random olarak bir ürün seç ürünün fiyatını hafızaya al ve ürün detaya git
        steps.clickToElement("#option-size > a:nth-child(6)"); // beden seç
        steps.clickToElement("a[id='pd_add_to_cart']"); // sepete ekle
        steps.clickToElement("span[id='spanCart']"); // sepete git
        steps.textControl("div[class*='option-detail'] span"); //s epetimde fiyat kontrolü
        steps.clickToElement("a[class='oq-up plus']"); // ürün adedi arttır
        steps.waitSecond(2000);
        steps.checkTextAttribute("input[class='item-quantity-input ignored']","data-quantity","2"); // ürün adedinin 2 olduğu kontrol edilir
        steps.clickToElement("a[class='cart-square-link']"); // sepetteki ürünleri sil
        steps.clickToElement("a[class*='sc-delete']"); // silmeyi onayla
        steps.getTextControl("p[class='cart-empty-title']","Sepetinizde ürün bulunmamaktadır."); // sepet boş mu kontrol et
    }
}
