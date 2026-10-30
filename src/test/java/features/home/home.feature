Feature: Ana Sayfa

  Background:
    Given kullanıcı "https://www.trendyol.com/" sitesine gider

  Scenario Outline: Kategori menüsünden gezinme
    When "<kategori_element>" butonuna tıklanır
    And  popup kapatılır
    Then adres çubuğu "<kategori>" içerir

    Examples:
      | kategori_element | kategori   |
      | elektronik_tab   | elektronik |