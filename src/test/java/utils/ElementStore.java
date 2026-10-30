package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.ElementModel;
import org.openqa.selenium.By;

import java.io.InputStream;
import java.util.List;

public class ElementStore {

    private static List<ElementModel> elements;

    public static void load() {
        if (elements != null) return;
        try (InputStream is = ElementStore.class.getClassLoader()
                .getResourceAsStream("elements.json")) {
            if (is == null) throw new RuntimeException("elements.json bulunamadı!");
            elements = new ObjectMapper().readValue(is, new TypeReference<List<ElementModel>>() {});
        } catch (Exception e) {
            throw new RuntimeException("elements.json okunamadı!", e);
        }
    }

    public static By getBy(String key) {
        if (elements == null) load();
        ElementModel el = elements.stream()
                .filter(e -> e.getKey().equals(key))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Key bulunamadı: " + key));
        return switch (el.getType()) {
            case "id"   -> By.id(el.getValue());
            case "css"  -> By.cssSelector(el.getValue());
            case "xpath"-> By.xpath(el.getValue());
            case "name" -> By.name(el.getValue());
            default -> throw new RuntimeException("Tanımsız locator tipi: " + el.getType());
        };
    }
}