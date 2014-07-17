package net.redhogs.cronparser.builder;

import net.redhogs.cronparser.I18nMessages;
import org.joda.time.DateTime;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author grhodes
 * @since 10 Dec 2012 14:23:50
 */
public class MonthDescriptionBuilder extends AbstractDescriptionBuilder {

    private Map<String, Integer> months;

    public MonthDescriptionBuilder(){
        months = new HashMap<String, Integer>();
        months.put("JAN", 1);
        months.put("FEB", 2);
        months.put("MAR", 3);
        months.put("APR", 4);
        months.put("MAY", 5);
        months.put("JUN", 6);
        months.put("JUL", 7);
        months.put("AUG", 8);
        months.put("SEP", 9);
        months.put("OCT", 10);
        months.put("NOV", 11);
        months.put("DEC", 12);
    }

    @Override
    protected String getSingleItemDescription(String expression) {
        int month = months.get(expression)==null ? Integer.parseInt(expression) : months.get(expression);
        return new DateTime().withDayOfMonth(1).withMonthOfYear(month).
                toString("MMMM", I18nMessages.getCurrentLocale());
    }

    @Override
    protected String getIntervalDescriptionFormat(String expression) {
        return MessageFormat.format(", "+I18nMessages.get("every_x")+" " +
                plural(expression, I18nMessages.get("month"), I18nMessages.get("months")), expression);
    }

    @Override
    protected String getBetweenDescriptionFormat(String expression) {
        return ", "+I18nMessages.get("between_description_format");
    }

    @Override
    protected String getDescriptionFormat(String expression) {
        return ", "+I18nMessages.get("only_in");
    }

}
