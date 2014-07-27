package net.redhogs.cronparser.parser;

import net.redhogs.cronparser.CronParameter;

public class CronFieldParseResult {
    private CronParameter field;
    private CronFieldExpression expression;

    public CronFieldParseResult(CronParameter field, CronFieldExpression expression) {
        this.field = field;
        this.expression = expression;
    }
}
