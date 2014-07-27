package net.redhogs.cronparser.parser;

import net.redhogs.cronparser.CronParameter;

import java.util.Comparator;

public class CronField {
    private CronParameter field;
    private FieldParser parser;

    private CronField(CronParameter field) {
        this.field = field;
        this.parser =
                new FieldParser()
                        .withConstraints(FieldConstraints.forField(field));
    }

    public static CronField seconds(){
        return new CronField(CronParameter.SECOND);
    }

    public static CronField minutes(){
        return new CronField(CronParameter.MINUTE);
    }

    public static CronField hours(){
        return new CronField(CronParameter.HOUR);
    }

    public static CronField daysOfWeek(){
        return new CronField(CronParameter.DAY_OF_WEEK);
    }

    public static CronField daysOfMonth(){
        return new CronField(CronParameter.DAY_OF_MONTH);
    }

    public static CronField months(){
        return new CronField(CronParameter.MONTH);
    }

    public static CronField years(){
        return new CronField(CronParameter.YEAR);
    }

    public CronParameter getField() {
        return field;
    }

    public CronFieldParseResult parse(String expression) {
        return new CronFieldParseResult(field, parser.parse(expression));
    }

    public static Comparator<CronField> createFieldTypeComparator(){
        return new Comparator<CronField>() {
            @Override
            public int compare(CronField o1, CronField o2) {
                return o1.getField().getOrder() - o2.getField().getOrder();
            }
        };
    }
}
