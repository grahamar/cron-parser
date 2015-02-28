package net.redhogs.cronparser.builder;

import net.redhogs.cronparser.DateAndTimeUtils;
import net.redhogs.cronparser.I18nMessages;

import java.text.MessageFormat;

/**
 * @author grhodes
 * @since 10 Dec 2012 14:18:21
 */
public class HoursDescriptionBuilder extends AbstractDescriptionBuilder {

    @Override
    protected String getSingleItemDescription(String expression) {
        return DateAndTimeUtils.formatTime(expression, "0");
    }

    @Override
    protected String getIntervalDescriptionFormat(String expression) {
        return MessageFormat.format(I18nMessages.get("every_x")+" " +
                plural(expression, I18nMessages.get("hour"), I18nMessages.get("hours")), expression);
    }

    @Override
    protected String getBetweenDescriptionFormat(String expression) {
        return I18nMessages.get("between_x_and_y");
    }

    @Override
    protected String getDescriptionFormat(String expression) {
        return I18nMessages.get("at_x");
    }

}
