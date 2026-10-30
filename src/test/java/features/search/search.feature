Feature: Ürün Arama, Sıralama, Filtreleme

  Background:
    Given kullanıcı "https://www.trendyol.com/" sitesine gider

  Scenario Outline: Ürün Arama
    When "arama_kutusu" butonuna tıklanır
    And  "arama_input" alanına "<aranan>" yazılır
    And  "arama_btn" butonuna tıklanır
    Then "sonuc_basligi" elementi "<aranan>" metnini içerir

    Examples:
      | aranan            |
      | kablosuz kulaklık |

  Scenario Outline: Ürünleri fiyata göre sıralama
    When "arama_kutusu" butonuna tıklanır
    And  "arama_input" alanına "<aranan>" yazılır
    And  "arama_btn" butonuna tıklanır
    And  "siralama_select_box" butonuna tıklanır
    And  "siralama_dusuk_fiyat" butonuna tıklanır
    Then "siralama_select_box" elementinin metni "Önerilen Sıralama" değildir

    Examples:
      | aranan            | siralama_element     |
      | kablosuz kulaklık | siralama_dusuk_fiyat |

  Scenario Outline: Fiyat aralığına göre filtreleme
    When "arama_kutusu" butonuna tıklanır
    And  "arama_input" alanına "<aranan>" yazılır
    And  "arama_btn" butonuna tıklanır
    And  "fiyat_baslik" butonuna tıklanır
    And  "fiyat_min_input" alanına "<min>" yazılır
    And  "fiyat_max_input" alanına "<max>" yazılır
    And  "fiyat_uygula_btn" butonuna tıklanır
    Then adres çubuğu "prc=<min>-<max>" içerene kadar beklenir

    Examples:
      | aranan            | min | max  |
      | kablosuz kulaklık | 500 | 1500 |
      | kablosuz kulaklık | 300 | 900  |