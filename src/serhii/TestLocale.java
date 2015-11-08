package serhii;

import java.util.Locale;

/**
 * Created by serhii on 08.11.15.
 */
public class TestLocale {

    public static void main(String[] args) {
        Locale currentLocale = Locale.getDefault();
        System.out.println(currentLocale.getLanguage());

    }
}
