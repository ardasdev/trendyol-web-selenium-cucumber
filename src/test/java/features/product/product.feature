Feature: Ürün Detayı

  Background:
    Given kullanıcı "https://www.trendyol.com/" sitesine gider

  Scenario Outline: Ürün detayını görüntüleme
    When "arama_kutusu" butonuna tıklanır
    And  "arama_input" alanına "<aranan>" yazılır
    And  "arama_btn" butonuna tıklanır
    And  "ilk_urun_kart" butonuna tıklanır
    And  yeni sekmeye geçilir
    And  onboarding kapatılır
    Then "urun_adi" görüntülenir
    And  "urun_fiyati" görüntülenir

    Examples:
      | aranan            |
      | kablosuz kulaklık |