package net.redhogs.cronparser;

import java.util.Locale;
import java.util.ResourceBundle;

@Deprecated
public final class I18nMessages {

    public static final Locale DEFAULT_LOCALE = Locale.UK;
    private static final String BUNDLE = "CronParserI18N";
    private static Locale currentLocale = DEFAULT_LOCALE;
    private static ResourceBundle messages = ResourceBundle.getBundle(BUNDLE, currentLocale);

    private I18nMessages() {}

    public static Locale getCurrentLocale() {
        return currentLocale;
    }

    public static void setCurrentLocale(Locale currentLocale) {
        I18nMessages.currentLocale = currentLocale;
        I18nMessages.messages = ResourceBundle.getBundle(BUNDLE, currentLocale);
    }

    public static String get(String property) {
        return I18nMessages.messages.getString(property);
    }

}
