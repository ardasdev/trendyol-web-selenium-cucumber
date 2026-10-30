package model;

import org.openqa.selenium.By;

public class ElementModel {
    private String key;
    private String value;
    private String type;

    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public By toBy() {
        switch (type.toLowerCase()) {
            case "id":              return By.id(value);
            case "name":            return By.name(value);
            case "css":             return By.cssSelector(value);
            case "xpath":           return By.xpath(value);
            case "class":           return By.className(value);
            case "linktext":        return By.linkText(value);
            case "partiallinktext": return By.partialLinkText(value);
            case "tag":             return By.tagName(value);
            default: throw new IllegalArgumentException("Bilinmeyen locator tipi: " + type);
        }
    }
}