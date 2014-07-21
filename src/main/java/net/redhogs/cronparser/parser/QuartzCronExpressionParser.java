package net.redhogs.cronparser.parser;

import net.redhogs.cronparser.CronParameter;
import net.redhogs.cronparser.CronType;

import java.text.ParseException;
import java.util.Map;

public class QuartzCronExpressionParser extends CronExpressionParser {

    QuartzCronExpressionParser() {}

    @Override
    protected Map<CronParameter, String> parse(Map<CronParameter, String> map, String expression) throws ParseException {
        String[] expressionParts = expression.split(" ");
        if (expressionParts.length < 6) {
            throw new ParseException(expression, 0);
        } else if (expressionParts.length > 7) {
            throw new ParseException(expression, expressionParts.length);
        } else if (expressionParts.length == 6) {
            //second, minute hour day_of_month month day_of_week
            map.put(CronParameter.SECOND, expressionParts[0]);
            map.put(CronParameter.MINUTE, expressionParts[1]);
            map.put(CronParameter.HOUR, expressionParts[2]);
            map.put(CronParameter.DAY_OF_MONTH, expressionParts[3]);
            map.put(CronParameter.MONTH, expressionParts[4]);
            map.put(CronParameter.DAY_OF_WEEK, expressionParts[5]);
        } else if(expressionParts.length == 7){
            //second, minute hour day_of_month month day_of_week year
            map.put(CronParameter.SECOND, expressionParts[0]);
            map.put(CronParameter.MINUTE, expressionParts[1]);
            map.put(CronParameter.HOUR, expressionParts[2]);
            map.put(CronParameter.DAY_OF_MONTH, expressionParts[3]);
            map.put(CronParameter.MONTH, expressionParts[4]);
            map.put(CronParameter.DAY_OF_WEEK, expressionParts[5]);
            map.put(CronParameter.YEAR, expressionParts[6]);
        }
        return map;
    }

    @Override
    public CronType getCronType() {
        return CronType.QUARTZ;
    }
}
