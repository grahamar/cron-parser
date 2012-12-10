/**
 * 
 */
package com.gr.cronparser.builder;

import java.text.MessageFormat;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.joda.time.format.DateTimeFormat;

import com.gr.cronparser.DateAndTimeUtils;


/**
 * @author grhodes
 * @since 10 Dec 2012 14:23:18
 */
public class DayOfWeekDescriptionBuilder extends AbstractDescriptionBuilder {

    @Override
    protected String getSingleItemDescription(String expression) {
        String exp = expression;
        if (expression.contains("#")) {
            exp = expression.substring(0, expression.indexOf("#"));
        } else if (expression.contains("L")) {
            exp = exp.replace("L", "");
        }
        if (StringUtils.isNumeric(exp)) {
            return DateAndTimeUtils.getDayOfWeekName(Integer.parseInt(exp));
        } else {
            return DateTimeFormat.forPattern("EEE").parseDateTime(WordUtils.capitalizeFully(exp)).dayOfWeek().getAsText();
        }
    }

    @Override
    protected String getIntervalDescriptionFormat(String expression) {
        return MessageFormat.format(", every {0} days of the week", expression);
    }

    @Override
    protected String getBetweenDescriptionFormat(String expression) {
        return ", {0} through {1}";
    }

    @Override
    protected String getDescriptionFormat(String expression) {
        String format = null;
        if (expression.contains("#")) {
            String dayOfWeekOfMonthNumber = expression.substring(expression.indexOf("#") + 1);
            String dayOfWeekOfMonthDescription = "";
            if ("1".equals(dayOfWeekOfMonthNumber)) {
                dayOfWeekOfMonthDescription = "first";
            } else if ("2".equals(dayOfWeekOfMonthNumber)) {
                dayOfWeekOfMonthDescription = "second";
            } else if ("3".equals(dayOfWeekOfMonthNumber)) {
                dayOfWeekOfMonthDescription = "third";
            } else if ("4".equals(dayOfWeekOfMonthNumber)) {
                dayOfWeekOfMonthDescription = "forth";
            } else if ("5".equals(dayOfWeekOfMonthNumber)) {
                dayOfWeekOfMonthDescription = "fifth";
            }
            format = ", on the " + dayOfWeekOfMonthDescription + " {0} of the month";
        } else if (expression.contains("L")) {
            format = ", on the last {0} of the month";
        } else {
            format = ", only on {0}";
        }
        return format;
    }

}
