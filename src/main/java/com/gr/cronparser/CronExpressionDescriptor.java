/**
 * 
 */
package com.gr.cronparser;

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
    private final String expression;
    private final Options options;
    private final String[] expressionParts;
    private boolean parsed;

    public CronExpressionDescriptor(String expression) {
        this(expression, new Options());
    }

    public CronExpressionDescriptor(String expression, Options options) {
        this.expression = expression;
        this.options = options;
        this.expressionParts = new String[6];
        this.parsed = false;
    }

    public String getDescription(DescriptionTypeEnum type) throws ParseException {
        String description = "";
        try {
            if (!parsed) {
                ExpressionParser parser = new ExpressionParser(expression, options);
                parser.parse();
                parsed = true;
            }
            switch (type) {
                case FULL:
                    description = getFullDescription();
                    break;
                case TIMEOFDAY:
                    description = getTimeOfDayDescription();
                    break;
                case HOURS:
                    description = getHoursDescription();
                    break;
                case MINUTES:
                    description = getMinutesDescription();
                    break;
                case SECONDS:
                    description = getSecondsDescription();
                    break;
                case DAYOFMONTH:
                    description = getDayOfMonthDescription();
                    break;
                case MONTH:
                    description = getMonthDescription();
                    break;
                case DAYOFWEEK:
                    description = getDayOfWeekDescription();
                    break;
                default:
                    description = getSecondsDescription();
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
     * @return
     */
    private String getDayOfWeekDescription() {
        return new DayOfWeekDescriptionBuilder().getSegmentDescription(expressionParts[5], ", every day");
    }

    /**
     * @return
     */
    private String getMonthDescription() {
        return new MonthDescriptionBuilder().getSegmentDescription(expressionParts[4], "");
    }

    /**
     * @return
     */
    private String getDayOfMonthDescription() {
        String description = null;
        String expression = expressionParts[3];
        expression = expression.replace("?", "*");
        if ("L".equals(expression)) {
            description = ", on the last day of the month";
        } else if ("WL".equals(expression) || "LW".equals(expression)) {
            description = ", on the last weekday of the month";
        } else {
            Pattern pattern = Pattern.compile("(\\dW)|(W\\d)");
            Matcher matcher = pattern.matcher(expression);
            if (matcher.matches()) {
                int dayNumber = Integer.parseInt(matcher.group().replace("W", ""));
                String dayString = dayNumber == 1 ? "first weekday" : String.format("weekday nearest day {0}", dayNumber);
                description = String.format(", on the {0} of the month", dayString);
            } else {
                description = new DayOfMonthDescriptionBuilder().getSegmentDescription(expression, ", every day");
            }
        }
        return description;
    }

    /**
     * @return
     */
    private String getSecondsDescription() {
        return new SecondsDescriptionBuilder().getSegmentDescription(expressionParts[0], "every second");
    }

    /**
     * @return
     */
    private String getMinutesDescription() {
        return new MinutesDescriptionBuilder().getSegmentDescription(expressionParts[1], "every minute");
    }

    /**
     * @return
     */
    private String getHoursDescription() {
        return new HoursDescriptionBuilder().getSegmentDescription(expression, "every hour");
    }

    /**
     * @return
     */
    private String getTimeOfDayDescription() {
        String secondsExpression = expressionParts[0];
        String minutesExpression = expressionParts[1];
        String hoursExpression = expressionParts[2];
        StringBuffer description = new StringBuffer();
        // Handle special cases first
        if (!StringUtils.containsAny(minutesExpression, specialCharacters) && !StringUtils.containsAny(hoursExpression, specialCharacters) && !StringUtils.containsAny(secondsExpression, specialCharacters)) {
            description.append("At ").append(CronExpressionUtils.formatTime(hoursExpression, minutesExpression, secondsExpression)); // Specific time of day (e.g. 10 14)
        } else if (minutesExpression.contains("-") && !StringUtils.containsAny(hoursExpression, specialCharacters)) {
            // Minute range in single hour (e.g. 0-10 11)
            String[] minuteParts = minutesExpression.split("-");
            description.append(String.format("Every minute between {0} and {1}", CronExpressionUtils.formatTime(hoursExpression, minuteParts[0]), CronExpressionUtils.formatTime(hoursExpression, minuteParts[1])));
        } else if (hoursExpression.contains(",") && !StringUtils.containsAny(minutesExpression, specialCharacters)) {
            // Hours list with single minute (e.g. 30 6,14,16)
            String[] hourParts = hoursExpression.split(",");
            description.append("At");
            for (int i = 0; i < hourParts.length; i++) {
                description.append(" ").append(CronExpressionUtils.formatTime(hourParts[i], minutesExpression));
                if (i < hourParts.length - 2) {
                    description.append(",");
                }
                if (i == hourParts.length - 2) {
                    description.append(" and");
                }
            }
        } else {
            String secondsDescription = getSecondsDescription();
            String minutesDescription = getMinutesDescription();
            String hoursDescription = getHoursDescription();
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
     * @return
     */
    private String getFullDescription() {
        String description = "";
        String timeSegment = getTimeOfDayDescription();
        String dayOfMonthDesc = getDayOfMonthDescription();
        String monthDesc = getMonthDescription();
        String dayOfWeekDesc = getDayOfWeekDescription();
        description = String.format("{0}{1}{2}", timeSegment, ((expressionParts[3] == "*") ? dayOfWeekDesc : dayOfMonthDesc), monthDesc);
        description = transformVerbosity(description);
        description = transformCase(description);
        return description;
    }

    /**
     * @param description
     * @return
     */
    private String transformCase(String description) {
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
     * @return
     */
    private String transformVerbosity(String description) {
        String descTemp = description;
        if (!options.isVerbose()) {
            descTemp = descTemp.replace(", every minute", "");
            descTemp = descTemp.replace(", every hour", "");
            descTemp = descTemp.replace(", every day", "");
        }
        return descTemp;
    }

}
