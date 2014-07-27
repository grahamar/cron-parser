package net.redhogs.cronparser.parser.field;

/**
 * Represents every x time on a cron field.
 */
public class Every extends CronFieldExpression {
    private int number;

    public Every(FieldConstraints constraints, String number){
        super(constraints);
        this.number = constraints.stringToInt(number);
    }
}
