package net.redhogs.cronparser.parser.field;

import net.redhogs.cronparser.CronParameter;

import java.util.Comparator;

public class CronFieldParseResult {
    private CronParameter field;
    private CronFieldExpression expression;

    public CronFieldParseResult(CronParameter field, CronFieldExpression expression) {
        this.field = field;
        this.expression = expression;
    }

    public CronParameter getField(){
        return field;
    }

    public CronFieldExpression getExpression(){
        return expression;
    }

    public static Comparator<CronFieldParseResult> createFieldComparator(){
        return new Comparator<CronFieldParseResult>() {
            @Override
            public int compare(CronFieldParseResult o1, CronFieldParseResult o2) {
                return o1.field.getOrder() - o2.field.getOrder();
            }
        };
    }
}
