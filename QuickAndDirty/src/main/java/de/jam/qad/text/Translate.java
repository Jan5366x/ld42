package de.jam.qad.text;

import gnu.trove.map.hash.THashMap;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Translate
 *
 * @author Jan5366x
 * Created on 11.08.2018.
 */
public class Translate {
    private static THashMap<String, String> translateMap = new THashMap<>();
    private static TranslateBundle translateBundle = new TranslateBundle();

    public static String get(final String key, final Object... args) {

        final var translate = translateMap.get(cleanupKey(key));

        if (translate == null || translate.isEmpty())
            return key;

        return format(translate, args);

    }

    public static void add(final String key, final String text) {
        translateMap.put(key, text);
    }

    public static void add(final Properties properties) {
        properties.forEach((k, t) -> {
            if (k instanceof String && t instanceof String)
                add(((String) k), ((String) t));
        });
    }

    private static String format(final String text, final Object[] args) {
        if (!text.contains("{"))
            return text;

        var result = text;

        for (var i = 0; i < args.length; i++)
            result = result.replace("{" + String.valueOf(i) + "}",args[i].toString());

        return result;
    }

    private static String cleanupKey(final String key) {
        return key.trim();
    }

    public static ResourceBundle getResourceBundle() {
        return translateBundle;
    }

    public static Enumeration<String> getKeys() {
        return Collections.enumeration(translateMap.keySet());
    }
}
