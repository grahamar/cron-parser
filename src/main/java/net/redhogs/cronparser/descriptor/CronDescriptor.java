package net.redhogs.cronparser.descriptor;

import com.google.common.collect.Maps;
import net.redhogs.cronparser.CronParameter;
import net.redhogs.cronparser.parser.field.CronFieldExpression;
import net.redhogs.cronparser.parser.field.CronFieldParseResult;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class CronDescriptor {

    public static final Locale DEFAULT_LOCALE = Locale.UK;
    private static final String BUNDLE = "CronParserI18N";
    private ResourceBundle bundle;

    private CronDescriptor(Locale locale){
        bundle = ResourceBundle.getBundle(BUNDLE, locale);
    }

    private CronDescriptor(){
        bundle = ResourceBundle.getBundle(BUNDLE, DEFAULT_LOCALE);
    }

    public String describe(List<CronFieldParseResult> fields) {
        Map<CronParameter, CronFieldExpression> expressions = Maps.newHashMap();
        for(CronFieldParseResult result : fields){
            expressions.put(result.getField(), result.getExpression());
        }
        return
                new StringBuilder().append(describeHHmmss(expressions)).append(" ")
                        .append(describeDayOfMonth(expressions)).append(" ")
                        .append(describeMonth(expressions)).append(" ")
                        .append(describeDayOfWeek(expressions)).append(" ")
                        .append(describeYear(expressions))
                        .toString().replaceAll("\\s+", " ").trim();

    }

    private String describeHHmmss(Map<CronParameter, CronFieldExpression> expressions) {
        return DescriptionStrategyFactory.hhMMssInstance(
                bundle,
                        expressions.get(CronParameter.HOUR),
                        expressions.get(CronParameter.MINUTE),
                        expressions.get(CronParameter.SECOND)
                ).describe();
    }

    private String describeDayOfMonth(Map<CronParameter, CronFieldExpression> expressions) {
        return String.format(
                DescriptionStrategyFactory.daysOfMonthInstance(
                        bundle,
                        expressions.get(CronParameter.DAY_OF_MONTH)
                ).describe(), bundle.getString("day"));
    }

    private String describeMonth(Map<CronParameter, CronFieldExpression> expressions) {
        return String.format(
                DescriptionStrategyFactory.monthsInstance(
                        bundle,
                        expressions.get(CronParameter.MONTH)
                ).describe(),
                bundle.getString("month"));
    }

    private String describeDayOfWeek(Map<CronParameter, CronFieldExpression> expressions) {
        return String.format(
                DescriptionStrategyFactory.daysOfWeekInstance(
                        bundle,
                        expressions.get(CronParameter.DAY_OF_WEEK)
                ).describe(),
                bundle.getString("day"));
    }

    private String describeYear(Map<CronParameter, CronFieldExpression> expressions) {
        return String.format(
                DescriptionStrategyFactory.plainInstance(
                        bundle,
                        expressions.get(CronParameter.YEAR)
                ).describe(),
                bundle.getString("year"));
    }

    public static CronDescriptor instance(){
        return new CronDescriptor();
    }

    public static CronDescriptor instance(Locale locale){
        return new CronDescriptor(locale);
    }
}
