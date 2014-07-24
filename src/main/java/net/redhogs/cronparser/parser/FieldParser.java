package net.redhogs.cronparser.parser;

import org.apache.commons.lang3.StringUtils;

/**
 * Parses a field from a cron expression.
 */
public class FieldParser {
    private final char[] specialCharsMinusStar = new char[] { '/', '-', ',' };

    public CronFieldExpression parse(String expression) {
        if (!StringUtils.containsAny(expression, specialCharsMinusStar)) {
            if ("*".equals(expression)) {
                return new Always();
            } else {
                return new On(expression);
            }
        } else {
            String[] array = expression.split(",");
            if (array.length > 1) {
                And and = new And();
                for (String exp : array) {
                    and.and(parse(exp));
                }
                return and;
            } else {
                array = expression.split("-");
                if (array.length > 1) {
                    if (array[1].contains("/")) {
                        String[] every = array[1].split("/");
                        return new Between(array[0], every[0], every[1]);
                    } else {
                        return new Between(array[0], array[1]);
                    }
                } else {
                    return new Every(expression.split("/")[1]);
                }
            }
        }
    }
}
