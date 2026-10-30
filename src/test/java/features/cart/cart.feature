Feature: Sepet İşlemleri

  Background:
    Given kullanıcı "https://www.trendyol.com/" sitesine gider

  Scenario Outline: Ürünü sepete ekleme
    When "arama_kutusu" butonuna tıklanır
    And  "arama_input" alanına "<aranan>" yazılır
    And  "arama_btn" butonuna tıklanır
    And  "ilk_urun_sepet_btn" butonuna tıklanır
    Then "sepet_urun_sayisi" görüntülenir

    Examples:
      | aranan            |
      | kablosuz kulaklık |

  Scenario Outline: Sepeti görüntüleme
    When "arama_kutusu" butonuna tıklanır
    And  "arama_input" alanına "<aranan>" yazılır
    And  "arama_btn" butonuna tıklanır
    And  "ilk_urun_sepet_btn" butonuna tıklanır
    And  "sepete_git_link" butonuna tıklanır
    Then "adet_selector" görüntülenir

    Examples:
      | aranan            |
      | kablosuz kulaklık |

  Scenario Outline: Sepetteki ürün adedini artırma
    When "arama_kutusu" butonuna tıklanır
    And  "arama_input" alanına "<aranan>" yazılır
    And  "arama_btn" butonuna tıklanır
    And  "ilk_urun_sepet_btn" butonuna tıklanır
    And  "sepete_git_link" butonuna tıklanır
    And  "adet_arttir_btn" butonuna tıklanır
    Then "adet_selector" elementinin değeri "<adet>" olur

    Examples:
      | aranan            | adet |
      | kablosuz kulaklık | 2    |