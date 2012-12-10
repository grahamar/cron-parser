/**
 * 
 */
package com.gr.cronparser;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gr.cronparser.builder.DayOfMonthDescriptionBuilder;
import com.gr.cronparser.builder.DayOfWeekDescriptionBuilder;
import com.gr.cronparser.builder.HoursDescriptionBuilder;
import com.gr.cronparser.builder.MinutesDescriptionBuilder;
import com.gr.cronparser.builder.MonthDescriptionBuilder;
import com.gr.cronparser.builder.SecondsDescriptionBuilder;


/**
 * @author grhodes
 * @since 10 Dec 2012 11:36:38
 */
public class CronExpressionDescriptor {

    private static final Logger LOG = LoggerFactory.getLogger(CronExpressionDescriptor.class);
    private static final char[] specialCharacters = new char[] { '/', '-', ',', '*' };

    private CronExpressionDescriptor() {
    }

    public static String getDescription(String expression) throws ParseException {
        return getDescription(DescriptionTypeEnum.FULL, expression, new Options());
    }

    public static String getDescription(DescriptionTypeEnum type, String expression) throws ParseException {
        return getDescription(type, expression, new Options());
    }

    public static String getDescription(DescriptionTypeEnum type, String expression, Options options) throws ParseException {
        String[] expressionParts = new String[6];
        String description = "";
        try {
            expressionParts = ExpressionParser.parse(expression);
            switch (type) {
                case FULL:
                    description = getFullDescription(expression, expressionParts, options);
                    break;
                case TIMEOFDAY:
                    description = getTimeOfDayDescription(expression, expressionParts, options);
                    break;
                case HOURS:
                    description = getHoursDescription(expression, expressionParts, options);
                    break;
                case MINUTES:
                    description = getMinutesDescription(expression, expressionParts, options);
                    break;
                case SECONDS:
                    description = getSecondsDescription(expression, expressionParts, options);
                    break;
                case DAYOFMONTH:
                    description = getDayOfMonthDescription(expression, expressionParts, options);
                    break;
                case MONTH:
                    description = getMonthDescription(expression, expressionParts, options);
                    break;
                case DAYOFWEEK:
                    description = getDayOfWeekDescription(expression, expressionParts, options);
                    break;
                default:
                    description = getSecondsDescription(expression, expressionParts, options);
                    break;
            }
        } catch (ParseException e) {
            if (!options.isThrowExceptionOnParseError()) {
                description = e.getMessage();
                LOG.debug("Exception parsing expression.", e);
            } else {
                LOG.error("Exception parsing expression.", e);
                throw e;
            }
        }
        return description;
    }

    /**
     * @param options
     * @param expressionParts
     * @param expression
     * @return
     */
    private static String getDayOfWeekDescription(String expression, String[] expressionParts, Options options) {
        return new DayOfWeekDescriptionBuilder().getSegmentDescription(expressionParts[5], ", every day");
    }

    /**
     * @param options
     * @param expressionParts
     * @param expression
     * @return
     */
    private static String getMonthDescription(String expression, String[] expressionParts, Options options) {
        return new MonthDescriptionBuilder().getSegmentDescription(expressionParts[4], "");
    }

    /**
     * @param options
     * @param expressionParts
     * @param expression
     * @return
     */
    private static String getDayOfMonthDescription(String expression, String[] expressionParts, Options options) {
        String description = null;
        String exp = expressionParts[3].replace("?", "*");
        if ("L".equals(exp)) {
            description = ", on the last day of the month";
        } else if ("WL".equals(exp) || "LW".equals(exp)) {
            description = ", on the last weekday of the month";
        } else {
            Pattern pattern = Pattern.compile("(\\dW)|(W\\d)");
            Matcher matcher = pattern.matcher(exp);
            if (matcher.matches()) {
                int dayNumber = Integer.parseInt(matcher.group().replace("W", ""));
                String dayString = dayNumber == 1 ? "first weekday" : MessageFormat.format("weekday nearest day {0}", dayNumber);
                description = MessageFormat.format(", on the {0} of the month", dayString);
            } else {
                description = new DayOfMonthDescriptionBuilder().getSegmentDescription(exp, ", every day");
            }
        }
        return description;
    }

    /**
     * @param options
     * @param expressionParts
     * @param expression
     * @return
     */
    private static String getSecondsDescription(String expression, String[] expressionParts, Options options) {
        return new SecondsDescriptionBuilder().getSegmentDescription(expressionParts[0], "every second");
    }

    /**
     * @param options
     * @param expressionParts
     * @param expression
     * @return
     */
    private static String getMinutesDescription(String expression, String[] expressionParts, Options options) {
        return new MinutesDescriptionBuilder().getSegmentDescription(expressionParts[1], "every minute");
    }

    /**
     * @param options
     * @param expressionParts
     * @param expression
     * @return
     */
    private static String getHoursDescription(String expression, String[] expressionParts, Options options) {
        return new HoursDescriptionBuilder().getSegmentDescription(expressionParts[2], "every hour");
    }

    /**
     * @param options
     * @param expressionParts
     * @param expression
     * @return
     */
    private static String getTimeOfDayDescription(String expression, String[] expressionParts, Options options) {
        String secondsExpression = expressionParts[0];
        String minutesExpression = expressionParts[1];
        String hoursExpression = expressionParts[2];
        StringBuffer description = new StringBuffer();
        // Handle special cases first
        if (!StringUtils.containsAny(minutesExpression, specialCharacters) && !StringUtils.containsAny(hoursExpression, specialCharacters) && !StringUtils.containsAny(secondsExpression, specialCharacters)) {
            description.append("At ").append(DateAndTimeUtils.formatTime(hoursExpression, minutesExpression, secondsExpression)); // Specific time of day (e.g. 10 14)
        } else if (minutesExpression.contains("-") && !StringUtils.containsAny(hoursExpression, specialCharacters)) {
            // Minute range in single hour (e.g. 0-10 11)
            String[] minuteParts = minutesExpression.split("-");
            description.append(MessageFormat.format("Every minute between {0} and {1}", DateAndTimeUtils.formatTime(hoursExpression, minuteParts[0]),
                    DateAndTimeUtils.formatTime(hoursExpression, minuteParts[1])));
        } else if (hoursExpression.contains(",") && !StringUtils.containsAny(minutesExpression, specialCharacters)) {
            // Hours list with single minute (e.g. 30 6,14,16)
            String[] hourParts = hoursExpression.split(",");
            description.append("At");
            for (int i = 0; i < hourParts.length; i++) {
                description.append(" ").append(DateAndTimeUtils.formatTime(hourParts[i], minutesExpression));
                if (i < hourParts.length - 2) {
                    description.append(",");
                }
                if (i == hourParts.length - 2) {
                    description.append(" and");
                }
            }
        } else {
            String secondsDescription = getSecondsDescription(expression, expressionParts, options);
            String minutesDescription = getMinutesDescription(expression, expressionParts, options);
            String hoursDescription = getHoursDescription(expression, expressionParts, options);
            description.append(secondsDescription);
            if (description.length() > 0) {
                description.append(", ");
            }
            description.append(minutesDescription);
            if (description.length() > 0) {
                description.append(", ");
            }
            description.append(hoursDescription);
        }
        return description.toString();
    }

    /**
     * @param options
     * @param expressionParts
     * @param expression
     * @return
     */
    private static String getFullDescription(String expression, String[] expressionParts, Options options) {
        String description = "";
        String timeSegment = getTimeOfDayDescription(expression, expressionParts, options);
        String dayOfMonthDesc = getDayOfMonthDescription(expression, expressionParts, options);
        String monthDesc = getMonthDescription(expression, expressionParts, options);
        String dayOfWeekDesc = getDayOfWeekDescription(expression, expressionParts, options);
        description = MessageFormat.format("{0}{1}{2}", timeSegment, ("*".equals(expressionParts[3]) ? dayOfWeekDesc : dayOfMonthDesc), monthDesc);
        description = transformVerbosity(description, options);
        description = transformCase(description, options);
        return description;
    }

    /**
     * @param description
     * @return
     */
    private static String transformCase(String description, Options options) {
        String descTemp = description;
        switch (options.getCasingType()) {
            case Sentence:
                descTemp = StringUtils.upperCase("" + descTemp.charAt(0)) + descTemp.substring(1);
                break;
            case Title:
                descTemp = StringUtils.capitalize(descTemp);
                break;
            default:
                descTemp = descTemp.toLowerCase();
                break;
        }
        return descTemp;
    }

    /**
     * @param description
     * @param options
     * @return
     */
    private static String transformVerbosity(String description, Options options) {
        String descTemp = description;
        if (!options.isVerbose()) {
            descTemp = descTemp.replace("every 1 minute", "every minute");
            descTemp = descTemp.replace("every 1 hour", "every hour");
            descTemp = descTemp.replace("every 1 day", "every day");
            descTemp = descTemp.replace(", every minute", "");
            descTemp = descTemp.replace(", every hour", "");
            descTemp = descTemp.replace(", every day", "");
        }
        return descTemp;
    }

}
