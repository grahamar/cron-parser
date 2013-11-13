package net.redhogs.cronparser;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.text.ParseException;

/**
 * @author grhodes
 * @since 10 Dec 2012 10:58:21
 */
class ExpressionParser {

    private ExpressionParser() {
    }

    public static String[] parse(String expression) throws ParseException {
        String[] parsed = new String[6];
        if (StringUtils.isEmpty(expression)) {
            throw new IllegalArgumentException(I18nMessages.get("expression_empty_exception"));
        }

        String[] expressionParts = expression.split(" ");
        if (expressionParts.length < 5) {
            throw new ParseException(expression, 0);
        } else if (expressionParts.length > 6) {
            throw new ParseException(expression, 6);
        } else if (expressionParts.length == 5) {
            parsed[0] = StringUtils.EMPTY; // 5 part CRON so default seconds to empty and shift array
            System.arraycopy(expressionParts, 0, parsed, 1, 5);
        } else {
            parsed = expressionParts;
        }

        normaliseExpression(parsed);

        return parsed;
    }

    /**
     * @param expressionParts
     */
    private static void normaliseExpression(String[] expressionParts) {
        // Convert ? to * only for day of month and day of week
        expressionParts[3] = expressionParts[3].replace('?', '*');
        expressionParts[5] = expressionParts[5].replace('?', '*');

        // Convert 0/, 1/ to */
        expressionParts[0] = expressionParts[0].replace("0/", "*/"); // seconds
        expressionParts[1] = expressionParts[1].replace("0/", "*/"); // minutes
        expressionParts[2] = expressionParts[2].replace("0/", "*/"); // hours
        expressionParts[3] = expressionParts[3].replace("1/", "*/"); // DOM
        expressionParts[4] = expressionParts[4].replace("1/", "*/"); // Month
        expressionParts[5] = expressionParts[5].replace("1/", "*/"); // DOW

        // convert */1 to *
        for (int i = 0; i <= 5; i++) {
            if ("*/1".equals(expressionParts[i])) {
                expressionParts[i] = "*";
            }
        }

        // convert SUN-SAT format to 0-6 format
        if(!StringUtils.isNumeric(expressionParts[5])) {
            for (int i = 0; i <= 6; i++) {
                expressionParts[5] = expressionParts[5].replace(DateAndTimeUtils.getDayOfWeekName(i + 1), String.valueOf(i));
            }
        }

        // convert JAN-DEC format to 1-12 format
        if(!StringUtils.isNumeric(expressionParts[4])) {
            for (int i = 1; i <= 12; i++) {
                DateTime currentMonth = new DateTime().withDayOfMonth(1).withMonthOfYear(i);
                String currentMonthDescription = currentMonth.toString("MMM").toUpperCase();
                expressionParts[4] = expressionParts[4].replace(currentMonthDescription, String.valueOf(i));
            }
        }

        // convert 0 second to (empty)
        if ("0".equals(expressionParts[0])) {
            expressionParts[0] = StringUtils.EMPTY;
        }
    }

}
