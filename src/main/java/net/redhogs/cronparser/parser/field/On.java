package net.redhogs.cronparser.parser.field;

public class On extends CronFieldExpression {
    private int time;

    public On(FieldConstraints constraints, String exp){
        super(constraints);
        time = constraints.validateInRange(constraints.intToInt(constraints.stringToInt(exp)));
    }

    public int getTime(){
        return time;
    }
}
