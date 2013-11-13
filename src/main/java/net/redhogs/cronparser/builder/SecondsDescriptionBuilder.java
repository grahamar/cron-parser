package net.redhogs.cronparser.builder;

import net.redhogs.cronparser.I18nMessages;

import java.text.MessageFormat;

/**
 * @author grhodes
 * @since 10 Dec 2012 14:10:43
 */
public class SecondsDescriptionBuilder extends AbstractDescriptionBuilder {

    @Override
    protected String getSingleItemDescription(String expression) {
        return expression;
    }

    @Override
    protected String getIntervalDescriptionFormat(String expression) {
        return MessageFormat.format(I18nMessages.get("every_x_seconds"), expression);
    }

    @Override
    protected String getBetweenDescriptionFormat(String expression) {
        return I18nMessages.get("seconds_through_past_the_minute");
    }

    @Override
    protected String getDescriptionFormat(String expression) {
        return I18nMessages.get("at_x_seconds_past_the_minute");
    }

}
