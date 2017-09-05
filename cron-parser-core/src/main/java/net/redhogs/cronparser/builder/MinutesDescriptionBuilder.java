package net.redhogs.cronparser.builder;

import net.redhogs.cronparser.DateAndTimeUtils;
import net.redhogs.cronparser.I18nMessages;
import net.redhogs.cronparser.Options;

import java.text.MessageFormat;

/**
 * @author grhodes
 * @since 10 Dec 2012 14:11:11
 */
public class MinutesDescriptionBuilder extends AbstractDescriptionBuilder {
    private final Options options;

    public MinutesDescriptionBuilder(Options options) {
        this.options = options;
    }
    @Override
    protected String getSingleItemDescription(String expression) {
        return DateAndTimeUtils.formatMinutes(expression);
    }

    @Override
    protected String getIntervalDescriptionFormat(String expression) {
        return MessageFormat.format(I18nMessages.get("every_x") + getSpace(options) + minPlural(expression), expression);
    }

    @Override
    protected String getBetweenDescriptionFormat(String expression, boolean omitSeparator) {
        return I18nMessages.get("minutes_through_past_the_hour");
    }

    @Override
    protected String getDescriptionFormat(String expression) {
        return "0".equals(expression) ? "" : I18nMessages.get("at_x") + getSpace(options) + minPlural(expression) +
                getSpace(options) + I18nMessages.get("past_the_hour");
    }

    @Override
    protected Boolean needSpaceBetweenWords() {
        return options.isNeedSpaceBetweenWords();
    }

    private String minPlural(String expression) {
        return plural(expression, I18nMessages.get("minute"), I18nMessages.get("minutes"));
    }

}
