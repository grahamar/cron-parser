package net.redhogs.cronparser.parser;

import java.util.ArrayList;
import java.util.List;

public class And extends CronFieldExpression {

    private List<CronFieldExpression> expressions;

    public And() {
        expressions = new ArrayList<CronFieldExpression>();
    }

    @Override
    public And and(CronFieldExpression exp) {
        expressions.add(exp);
        return this;
    }
}
