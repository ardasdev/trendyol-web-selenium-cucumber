# Trendyol Web Test Automation — Selenium + Cucumber

UI test automation for the Trendyol web site, written during my QA internship (June–July 2026).

## Tech stack

Java 17 · Selenium 4 · Cucumber (Gherkin, Turkish step definitions) · JUnit · Maven

## What is tested

| Feature file | Scenarios |
| --- | --- |
| `search.feature` | Product search, sorting by price, price range filter |
| `home.feature` | Browsing through the category menu |
| `product.feature` | Product detail page |
| `cart.feature` | Add to cart, view cart, change quantity |
| `login.feature` | Successful login, unregistered e-mail opens sign-up |

Most scenarios are `Scenario Outline`s with `Examples` tables, so the same flow runs with different data.

## Design

- **Element repository:** locators live in `src/test/resources/elements.json`, not in the step code. Steps refer to elements by key (e.g. `"giris_btn"`).
- **Explicit waits** (`WebDriverWait` + `ExpectedConditions`) instead of `Thread.sleep`.
- **Credentials are never committed.** `Config` reads values from environment variables first (`login.email` → `LOGIN_EMAIL`), then from `-D` system properties, then from `config.properties` (which is empty in the repo).

## How to run

Set your own test account credentials as environment variables:

```powershell
# Windows PowerShell
$env:LOGIN_EMAIL = "your-test-account@example.com"
$env:LOGIN_SIFRE = "your-password"
```

```bash
# macOS / Linux
export LOGIN_EMAIL="your-test-account@example.com"
export LOGIN_SIFRE="your-password"
```

Then run the `RunAllTests` runner from your IDE, or the individual runners under `src/test/java/runner/`.

## Known limitations

- The tests run against the **live** Trendyol site. Page structure, pop-ups and bot protection can change at any time, so some locators may need updating. A more stable Selenium project against a demo site is in progress.
