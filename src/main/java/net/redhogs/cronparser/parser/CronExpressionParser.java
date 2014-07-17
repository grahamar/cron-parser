package net.redhogs.cronparser.parser;

import net.redhogs.cronparser.CronParameter;
import net.redhogs.cronparser.CronType;
import net.redhogs.cronparser.DateAndTimeUtils;
import net.redhogs.cronparser.I18nMessages;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jose.rozanec on 7/16/14.
 */
public abstract class CronExpressionParser {

    public abstract CronType getCronType();

    public final Map<CronParameter, String> parse(String expression) throws ParseException {
        if (StringUtils.isEmpty(expression)) {
            throw new IllegalArgumentException(I18nMessages.get("expression_empty_exception"));
        }
        Map<CronParameter, String> map = initializeDefaultMap();

        parse(map, expression);

        normaliseExpression(map);

        return map;
    }

    protected abstract Map<CronParameter, String> parse(Map<CronParameter, String> map, String expression) throws ParseException;

    /**
     * @param expressionParts
     */
    protected final static void normaliseExpression(Map<CronParameter, String> expressionParts) {
        // Convert ? to * only for day of month and day of week
        expressionParts.put(CronParameter.DAY_OF_MONTH, expressionParts.get(CronParameter.DAY_OF_MONTH).replace('?', '*'));
        expressionParts.put(CronParameter.DAY_OF_WEEK, expressionParts.get(CronParameter.DAY_OF_WEEK).replace('?', '*'));

        // Convert 0/, 1/ to */
        expressionParts.put(CronParameter.SECOND, expressionParts.get(CronParameter.SECOND).replace("0/", "*/"));
        expressionParts.put(CronParameter.MINUTE, expressionParts.get(CronParameter.MINUTE).replace("0/", "*/"));
        expressionParts.put(CronParameter.HOUR, expressionParts.get(CronParameter.HOUR).replace("0/", "*/"));
        expressionParts.put(CronParameter.DAY_OF_MONTH, expressionParts.get(CronParameter.DAY_OF_MONTH).replace("1/", "*/"));
        expressionParts.put(CronParameter.MONTH, expressionParts.get(CronParameter.MONTH).replace("1/", "*/"));
        expressionParts.put(CronParameter.DAY_OF_WEEK, expressionParts.get(CronParameter.DAY_OF_WEEK).replace("1/", "*/"));

        for(CronParameter param : expressionParts.keySet()){
            if("*/1".equals(expressionParts.get(param))){
                expressionParts.put(param, "*");
            }
        }

        // convert SUN-SAT format to 0-6 format
        if(!StringUtils.isNumeric(expressionParts.get(CronParameter.DAY_OF_WEEK))) {
            for (int i = 1; i <= 7; i++) {
                expressionParts
                        .put(CronParameter.DAY_OF_WEEK,
                                expressionParts.get(CronParameter.DAY_OF_WEEK)
                                        .replace(DateAndTimeUtils.getDayOfWeekName(i), String.valueOf(i)));
            }
        }

        // convert JAN-DEC format to 1-12 format
        if(!StringUtils.isNumeric(expressionParts.get(CronParameter.DAY_OF_MONTH))) {
            for (int i = 1; i <= 12; i++) {
                DateTime currentMonth = new DateTime().withDayOfMonth(1).withMonthOfYear(i);
                String currentMonthDescription = currentMonth.toString("MMM").toUpperCase();
                expressionParts
                        .put(CronParameter.DAY_OF_MONTH,
                                expressionParts.get(CronParameter.DAY_OF_MONTH).replace(currentMonthDescription, String.valueOf(i)));
            }
        }

        // TODO validate no longer needed: convert 0 second to (empty)
       // if ("0".equals(expressionParts[0])) {
       //     expressionParts[0] = StringUtils.EMPTY;
       // }
    }

    private Map<CronParameter, String> initializeDefaultMap() {
        Map<CronParameter, String> map = new HashMap<CronParameter, String>();
        for(CronParameter param : CronParameter.values()){
            map.put(param, "0");
        }
        map.put(CronParameter.YEAR, "*");
        return map;
    }
}
