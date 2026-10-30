package utils;

import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;

public class Config {
    private static final Properties props = new Properties();

    static {
        try (InputStream is = Config.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            props.load(is);
        } catch (Exception e) {
            throw new RuntimeException("config.properties okunamadı", e);
        }
    }

    /**
     * Önce ortam değişkenine bakar ("login.email" -> LOGIN_EMAIL), sonra -D sistem
     * özelliğine, en son config.properties dosyasına. Şifreler repoya girmez.
     */
    public static String get(String key) {
        // Locale.ROOT: Türkçe dil ayarında "i".toUpperCase() noktalı "İ" üretir
        String envKey = key.toUpperCase(Locale.ROOT).replace('.', '_');
        String env = System.getenv(envKey);
        if (env != null && !env.isBlank()) {
            return env;
        }
        return System.getProperty(key, props.getProperty(key));
    }
}
