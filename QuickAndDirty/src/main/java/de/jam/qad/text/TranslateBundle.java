package de.jam.qad.text;

import java.util.Enumeration;
import java.util.ResourceBundle;

/**
 * TranslateBundle
 *
 * @author Jan5366x
 * Created on 11.08.2018.
 */
public class TranslateBundle extends ResourceBundle {
    @Override
    protected Object handleGetObject(String key) {
        return Translate.get(key);
    }

    @Override
    public Enumeration<String> getKeys() {
        return Translate.getKeys();
    }
}
