package net.redhogs.cronparser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public final class I18nMessages {

    public static final Locale DEFAULT_LOCALE = Locale.UK;
    private static final String BUNDLE = "CronParserI18N";
    private static Locale currentLocale = DEFAULT_LOCALE;
    private static ResourceBundle messages = ResourceBundle.getBundle(BUNDLE, currentLocale, new UTF8Control());

    private I18nMessages() {}

    public static Locale getCurrentLocale() {
        return currentLocale;
    }

    public static void setCurrentLocale(Locale currentLocale) {
        I18nMessages.currentLocale = currentLocale;
        I18nMessages.messages = ResourceBundle.getBundle(BUNDLE, currentLocale, new UTF8Control());
    }

    public static String get(String property) {
        return I18nMessages.messages.getString(property);
    }

    static class UTF8Control extends ResourceBundle.Control {
        public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload) throws IllegalAccessException, InstantiationException, IOException {
            // The below is a copy of the default implementation.
            String bundleName = toBundleName(baseName, locale);
            String resourceName = toResourceName(bundleName, "properties");
            ResourceBundle bundle = null;
            InputStream stream = null;
            if (reload) {
                URL url = loader.getResource(resourceName);
                if (url != null) {
                    URLConnection connection = url.openConnection();
                    if (connection != null) {
                        connection.setUseCaches(false);
                        stream = connection.getInputStream();
                  }
                }
            } else {
                stream = loader.getResourceAsStream(resourceName);
            }
            if (stream != null) {
                try {
                    // Only this line is changed to make it to read properties files as UTF-8.
                    bundle = new PropertyResourceBundle(new InputStreamReader(stream, "UTF-8"));
                } finally {
                    stream.close();
                }
            }
            return bundle;
        }
    }

}
