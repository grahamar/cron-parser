package net.redhogs.cronparser.builder;

import net.redhogs.cronparser.I18nMessages;
import org.joda.time.DateTime;

import java.text.MessageFormat;

/**
 * @author grhodes
 * @since 10 Dec 2012 14:23:50
 */
public class MonthDescriptionBuilder extends AbstractDescriptionBuilder {

    @Override
    protected String getSingleItemDescription(String expression) {
        return new DateTime().withDayOfMonth(1).withMonthOfYear(Integer.parseInt(expression)).
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
