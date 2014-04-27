package net.redhogs.cronparser.builder;

import net.redhogs.cronparser.DateAndTimeUtils;
import net.redhogs.cronparser.I18nMessages;
import net.redhogs.cronparser.Options;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.joda.time.format.DateTimeFormat;

import java.text.MessageFormat;

/**
 * @author grhodes
 * @since 10 Dec 2012 14:23:18
 */
public class DayOfWeekDescriptionBuilder extends AbstractDescriptionBuilder {

    private final Options options;

    /**
     * @deprecated Use DayOfWeekDescriptionBuilder(Options) instead.
     */
    @Deprecated
    public DayOfWeekDescriptionBuilder() {
        this.options = null;
    }

    public DayOfWeekDescriptionBuilder(Options options) {
        this.options = options;
    }

    @Override
    protected String getSingleItemDescription(String expression) {
        String exp = expression;
        if (expression.contains("#")) {
            exp = expression.substring(0, expression.indexOf("#"));
        } else if (expression.contains("L")) {
            exp = exp.replace("L", "");
        }
        if (StringUtils.isNumeric(exp)) {
            int dayOfWeekNum = Integer.parseInt(exp);
            if(options != null && !options.isZeroBasedDayOfWeek() && dayOfWeekNum <= 1) {
                dayOfWeekNum = 7;
            } else if(options != null && !options.isZeroBasedDayOfWeek()) {
                dayOfWeekNum -= 1;
            }
            return DateAndTimeUtils.getDayOfWeekName(dayOfWeekNum);
        } else {
            return DateTimeFormat.forPattern("EEE").parseDateTime(WordUtils.capitalizeFully(exp)).dayOfWeek().getAsText(I18nMessages.getCurrentLocale());
        }
    }

    @Override
    protected String getIntervalDescriptionFormat(String expression) {
        return MessageFormat.format(", "+I18nMessages.get("interval_description_format"), expression);
    }

    @Override
    protected String getBetweenDescriptionFormat(String expression) {
        return ", "+I18nMessages.get("between_description_format");
    }

    @Override
    protected String getDescriptionFormat(String expression) {
        String format = null;
        if (expression.contains("#")) {
            String dayOfWeekOfMonthNumber = expression.substring(expression.indexOf("#") + 1);
            String dayOfWeekOfMonthDescription = "";
            if ("1".equals(dayOfWeekOfMonthNumber)) {
                dayOfWeekOfMonthDescription = I18nMessages.get("first");
            } else if ("2".equals(dayOfWeekOfMonthNumber)) {
                dayOfWeekOfMonthDescription = I18nMessages.get("second");
            } else if ("3".equals(dayOfWeekOfMonthNumber)) {
                dayOfWeekOfMonthDescription = I18nMessages.get("third");
            } else if ("4".equals(dayOfWeekOfMonthNumber)) {
                dayOfWeekOfMonthDescription = I18nMessages.get("fourth");
            } else if ("5".equals(dayOfWeekOfMonthNumber)) {
                dayOfWeekOfMonthDescription = I18nMessages.get("fifth");
            }
            format = ", "+String.format(I18nMessages.get("on_the_day_of_the_month"), dayOfWeekOfMonthDescription);
        } else if (expression.contains("L")) {
            format = ", "+I18nMessages.get("on_the_last_of_the_month");
        } else {
            format = ", "+I18nMessages.get("only_on");
        }
        return format;
    }

}
