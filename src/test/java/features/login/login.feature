Feature: Giriş İşlemleri

  Background:
    Given kullanıcı "https://www.trendyol.com/" sitesine gider

  Scenario: Geçerli bilgiyle giriş başarılı olur
    When "giris_yap_link" butonuna tıklanır
    And  "login_email_field" alanına config "login.email" değeri yazılır
    And  "email_devam_btn" butonuna tıklanır
    And  "login_pw_field" alanına config "login.sifre" değeri yazılır
    And  "giris_btn" butonuna tıklanır
    Then "hesabim_text" görüntülenir

  Scenario: Kayıtlı olmayan e-posta ile giriş denenince kayıt ekranı açılır
    When "giris_yap_link" butonuna tıklanır
    And  "login_email_field" alanına "yanlis@ornek.com" yazılır
    And  "email_devam_btn" butonuna tıklanır
    Then "hesap_olustur_text" görüntülenir