package team1.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;


import static team1.constant.ProjectConstants.*;

public class LanguageResourceBundle {

    private static String localization;
    private static Map map;

    public static String LOCALE_PATH = RESOURCES_PATH + "/locale/%s_dictionary.properties";

    // when loading class into jvm
    static {
        Locale currentLocale = Locale.getDefault();
        localization = currentLocale.getLanguage();

        LOCALE_PATH = String.format(LOCALE_PATH, localization);

        try {

            Properties properties = new Properties();
            properties.load(new FileInputStream(LOCALE_PATH));

            map = properties;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String get(String key) {
        return map.get(key).toString();
    }
}
