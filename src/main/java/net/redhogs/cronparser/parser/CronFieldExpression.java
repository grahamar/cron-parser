package net.redhogs.cronparser.parser;

public abstract class CronFieldExpression {

    protected FieldConstraints constraints;

    public CronFieldExpression(FieldConstraints constraints){
        this.constraints = constraints;
    }

    public And and(CronFieldExpression exp) {
        return new And().and(this).and(exp);
    }
}
