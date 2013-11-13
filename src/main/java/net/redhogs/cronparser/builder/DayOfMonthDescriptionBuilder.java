package net.redhogs.cronparser.builder;

import net.redhogs.cronparser.I18nMessages;

/**
 * @author grhodes
 * @since 10 Dec 2012 14:24:08
 */
public class DayOfMonthDescriptionBuilder extends AbstractDescriptionBuilder {

    @Override
    protected String getSingleItemDescription(String expression) {
        return expression;
    }

    @Override
    protected String getIntervalDescriptionFormat(String expression) {
        return ", "+I18nMessages.get("every_x")+" " + plural(expression, I18nMessages.get("day"), I18nMessages.get("days"));
    }

    @Override
    protected String getBetweenDescriptionFormat(String expression) {
        return ", "+I18nMessages.get("between_days_of_the_month");
    }

    @Override
    protected String getDescriptionFormat(String expression) {
        return ", "+I18nMessages.get("on_day_of_the_month");
    }

}
