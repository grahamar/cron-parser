package net.redhogs.cronparser;

import net.redhogs.cronparser.builder.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        return getDescription(DescriptionTypeEnum.FULL, expression, new Options(), I18nMessages.DEFAULT_LOCALE);
    }

    public static String getDescription(String expression, Options options) throws ParseException {
        return getDescription(DescriptionTypeEnum.FULL, expression, options, I18nMessages.DEFAULT_LOCALE);
    }

    public static String getDescription(String expression, Locale locale) throws ParseException {
        return getDescription(DescriptionTypeEnum.FULL, expression, new Options(), locale);
    }

    public static String getDescription(String expression, Options options, Locale locale) throws ParseException {
        return getDescription(DescriptionTypeEnum.FULL, expression, options, locale);
    }

    public static String getDescription(DescriptionTypeEnum type, String expression) throws ParseException {
        return getDescription(type, expression, new Options(), I18nMessages.DEFAULT_LOCALE);
    }

    public static String getDescription(DescriptionTypeEnum type, String expression, Locale locale) throws ParseException {
        return getDescription(type, expression, new Options(), locale);
    }

    public static String getDescription(DescriptionTypeEnum type, String expression, Options options) throws ParseException {
        return getDescription(type, expression, options, I18nMessages.DEFAULT_LOCALE);
    }

    public static String getDescription(DescriptionTypeEnum type, String expression, Options options, Locale locale) throws ParseException {
        I18nMessages.setCurrentLocale(locale);
        String[] expressionParts;
        String description = "";
        try {
            expressionParts = ExpressionParser.parse(expression, options);
            switch (type) {
                case FULL:
                    description = getFullDescription(expressionParts, options);
                    break;
                case TIMEOFDAY:
                    description = getTimeOfDayDescription(expressionParts);
                    break;
                case HOURS:
                    description = getHoursDescription(expressionParts);
                    break;
                case MINUTES:
                    description = getMinutesDescription(expressionParts);
                    break;
                case SECONDS:
                    description = getSecondsDescription(expressionParts);
                    break;
                case DAYOFMONTH:
                    description = getDayOfMonthDescription(expressionParts);
                    break;
                case MONTH:
                    description = getMonthDescription(expressionParts);
                    break;
                case DAYOFWEEK:
                    description = getDayOfWeekDescription(expressionParts, options);
                    break;
                case YEAR:
                    description = getYearDescription(expressionParts, options);
                    break;
                default:
                    description = getSecondsDescription(expressionParts);
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
     * @param expressionParts
     * @return
     */
    private static String getYearDescription(String[] expressionParts, Options options) {
      return new YearDescriptionBuilder(options).getSegmentDescription(expressionParts[6], ", "+I18nMessages.get("every_year"));
    }

    /**
     * @param expressionParts
     * @return
     */
    private static String getDayOfWeekDescription(String[] expressionParts, Options options) {
        return new DayOfWeekDescriptionBuilder(options).getSegmentDescription(expressionParts[5], ", "+I18nMessages.get("every_day"));
    }

    /**
     * @param expressionParts
     * @return
     */
    private static String getMonthDescription(String[] expressionParts) {
        return new MonthDescriptionBuilder().getSegmentDescription(expressionParts[4], "");
    }

    /**
     * @param expressionParts
     * @return
     */
    private static String getDayOfMonthDescription(String[] expressionParts) {
        String description = null;
        String exp = expressionParts[3].replace("?", "*");
        if ("L".equals(exp)) {
            description = ", "+I18nMessages.get("on_the_last_day_of_the_month");
        } else if ("WL".equals(exp) || "LW".equals(exp)) {
            description = ", "+I18nMessages.get("on_the_last_weekday_of_the_month");
        } else {
            Pattern pattern = Pattern.compile("(\\dW)|(W\\d)");
            Matcher matcher = pattern.matcher(exp);
            if (matcher.matches()) {
                int dayNumber = Integer.parseInt(matcher.group().replace("W", ""));
                String dayString = dayNumber == 1 ? I18nMessages.get("first_weekday") : MessageFormat.format(I18nMessages.get("weekday_nearest_day"), dayNumber);
                description = MessageFormat.format(", "+I18nMessages.get("on_the_of_the_month"), dayString);
            } else {
                description = new DayOfMonthDescriptionBuilder().getSegmentDescription(exp, ", "+I18nMessages.get("every_day"));
            }
        }
        return description;
    }

    /**
     * @param expressionParts
     * @return
     */
    private static String getSecondsDescription(String[] expressionParts) {
        return new SecondsDescriptionBuilder().getSegmentDescription(expressionParts[0], I18nMessages.get("every_second"));
    }

    /**
     * @param expressionParts
     * @return
     */
    private static String getMinutesDescription(String[] expressionParts) {
        return new MinutesDescriptionBuilder().getSegmentDescription(expressionParts[1], I18nMessages.get("every_minute"));
    }

    /**
     * @param expressionParts
     * @return
     */
    private static String getHoursDescription(String[] expressionParts) {
        return new HoursDescriptionBuilder().getSegmentDescription(expressionParts[2], I18nMessages.get("every_hour"));
    }

    /**
     * @param expressionParts
     * @return
     */
    private static String getTimeOfDayDescription(String[] expressionParts) {
        String secondsExpression = expressionParts[0];
        String minutesExpression = expressionParts[1];
        String hoursExpression = expressionParts[2];
        StringBuilder description = new StringBuilder();
        // Handle special cases first
        if (!StringUtils.containsAny(minutesExpression, specialCharacters) && !StringUtils.containsAny(hoursExpression, specialCharacters) && !StringUtils.containsAny(secondsExpression, specialCharacters)) {
            description.append(I18nMessages.get("at")).append(" ").append(DateAndTimeUtils.formatTime(hoursExpression, minutesExpression, secondsExpression)); // Specific time of day (e.g. 10 14)
        } else if (minutesExpression.contains("-") && !minutesExpression.contains("/") && !StringUtils.containsAny(hoursExpression, specialCharacters)) {
            // Minute range in single hour (e.g. 0-10 11)
            String[] minuteParts = minutesExpression.split("-");
            description.append(MessageFormat.format(I18nMessages.get("every_minute_between"), DateAndTimeUtils.formatTime(hoursExpression, minuteParts[0]),
                    DateAndTimeUtils.formatTime(hoursExpression, minuteParts[1])));
        } else if (hoursExpression.contains(",") && !StringUtils.containsAny(minutesExpression, specialCharacters)) {
            // Hours list with single minute (e.g. 30 6,14,16)
            String[] hourParts = hoursExpression.split(",");
            description.append(I18nMessages.get("at"));
            for (int i = 0; i < hourParts.length; i++) {
                description.append(" ").append(DateAndTimeUtils.formatTime(hourParts[i], minutesExpression));
                if (i < hourParts.length - 2) {
                    description.append(",");
                }
                if (i == hourParts.length - 2) {
                    description.append(" ");
                    description.append(I18nMessages.get("and"));
                }
            }
        } else {
            String secondsDescription = getSecondsDescription(expressionParts);
            String minutesDescription = getMinutesDescription(expressionParts);
            String hoursDescription = getHoursDescription(expressionParts);
            description.append(secondsDescription);
            if (description.length() > 0 && StringUtils.isNotEmpty(minutesDescription)) {
                description.append(", ");
            }
            description.append(minutesDescription);
            if (description.length() > 0 && StringUtils.isNotEmpty(hoursDescription)) {
                description.append(", ");
            }
            description.append(hoursDescription);
        }
        return description.toString();
    }

    /**
     * @param options
     * @param expressionParts
     * @return
     */
    private static String getFullDescription(String[] expressionParts, Options options) {
        String description = "";
        String timeSegment = getTimeOfDayDescription(expressionParts);
        String dayOfMonthDesc = getDayOfMonthDescription(expressionParts);
        String monthDesc = getMonthDescription(expressionParts);
        String dayOfWeekDesc = getDayOfWeekDescription(expressionParts, options);
        String yearDesc = getYearDescription(expressionParts, options);
        description = MessageFormat.format("{0}{1}{2}{3}", timeSegment, ("*".equals(expressionParts[3]) ? dayOfWeekDesc : dayOfMonthDesc), monthDesc, yearDesc);
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
            descTemp = descTemp.replace(I18nMessages.get("every_1_minute"), I18nMessages.get("every_minute"));
            descTemp = descTemp.replace(I18nMessages.get("every_1_hour"), I18nMessages.get("every_hour"));
            descTemp = descTemp.replace(I18nMessages.get("every_1_day"), I18nMessages.get("every_day"));
            descTemp = descTemp.replace(", "+I18nMessages.get("every_minute"), "");
            descTemp = descTemp.replace(", "+I18nMessages.get("every_hour"), "");
            descTemp = descTemp.replace(", "+I18nMessages.get("every_day"), "");
        }
        return descTemp;
    }

}
