package net.redhogs.cronparser.parser;

public class On extends CronFieldExpression {
    private int number;

    public On(FieldConstraints constraints, String exp){
        super(constraints);
        number = constraints.validateInRange(constraints.intToInt(constraints.stringToInt(exp)));
    }
}
