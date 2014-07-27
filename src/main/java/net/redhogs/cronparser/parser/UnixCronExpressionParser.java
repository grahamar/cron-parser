package net.redhogs.cronparser.parser;

import net.redhogs.cronparser.CronParameter;
import net.redhogs.cronparser.CronType;

import java.text.ParseException;
import java.util.Map;

/**
 * @author grhodes
 * @since 10 Dec 2012 10:58:21
 */
@Deprecated
public class UnixCronExpressionParser extends CronExpressionParser {

    UnixCronExpressionParser() {}

    @Override
    protected Map<CronParameter, String> parse(Map<CronParameter, String> map, String expression) throws ParseException {
        String[] expressionParts = expression.split(" ");
        if (expressionParts.length < 5) {
            throw new ParseException(expression, 0);
        } else if (expressionParts.length > 5) {
            throw new ParseException(expression, expressionParts.length);
        } else if (expressionParts.length == 5) {
            //minute hour day_of_month month day_of_week
            map.put(CronParameter.MINUTE, expressionParts[0]);
            map.put(CronParameter.HOUR, expressionParts[1]);
            map.put(CronParameter.DAY_OF_MONTH, expressionParts[2]);
            map.put(CronParameter.MONTH, expressionParts[3]);
            map.put(CronParameter.DAY_OF_WEEK, expressionParts[4]);
        }
        return map;
    }

    @Override
    public CronType getCronType() {
        return CronType.UNIX;
    }
}
