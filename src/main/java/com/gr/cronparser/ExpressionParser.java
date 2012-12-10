/**
 * 
 */
package com.gr.cronparser;

import hirondelle.date4j.DateTime;

import java.text.ParseException;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;


/**
 * @author grhodes
 * @since 10 Dec 2012 10:58:21
 */
public class ExpressionParser {

    private final String expression;
    private final Options options;

    public ExpressionParser(String expression, Options options) {
        this.expression = expression;
        this.options = options;
    }

    public String[] parse() throws ParseException {
        String[] parsed = new String[6];
        if (StringUtils.isEmpty(expression)) {
            throw new IllegalArgumentException("Expression cannot be null or empty");
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
    private void normaliseExpression(String[] expressionParts) {
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
            if (expressionParts[i] == "*/1") {
                expressionParts[i] = "*";
            }
        }

        // convert SUN-SAT format to 0-6 format
        for (int i = 0; i <= 6; i++) {
            if (!options.isDayOfWeekStartAtZero()) {
                expressionParts[5] = expressionParts[5].replace(String.valueOf(i + 1), String.valueOf(i));
            }
            expressionParts[5] = expressionParts[5].replace(CronExpressionUtils.getDayOfWeekName(i), String.valueOf(i));
        }

        // convert JAN-DEC format to 1-12 format
        for (int i = 1; i <= 12; i++) {
            DateTime currentMonth = new DateTime(DateTime.now(TimeZone.getDefault()).getYear(), i, 1, 0, 0, 0, 0);
            String currentMonthDescription = currentMonth.format("MMM").toUpperCase();
            expressionParts[4] = expressionParts[4].replace(currentMonthDescription, String.valueOf(i));
        }

        // convert 0 second to (empty)
        if (expressionParts[0] == "0") {
            expressionParts[0] = StringUtils.EMPTY;
        }
    }

}
