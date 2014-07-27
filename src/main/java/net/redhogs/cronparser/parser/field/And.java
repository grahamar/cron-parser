package net.redhogs.cronparser.parser.field;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a conjunction of cron expressions for a field.
 */
public class And extends CronFieldExpression {

    private List<CronFieldExpression> expressions;

    public And() {
        super(FieldConstraints.nullConstraints());
        expressions = new ArrayList<CronFieldExpression>();
    }

    @Override
    public And and(CronFieldExpression exp) {
        expressions.add(exp);
        return this;
    }
}
