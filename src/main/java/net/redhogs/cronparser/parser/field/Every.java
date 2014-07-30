package net.redhogs.cronparser.parser.field;

/**
 * Represents every x time on a cron field.
 */
public class Every extends CronFieldExpression {
    private int time;

    public Every(FieldConstraints constraints, String time) {
        super(constraints);
        this.time = constraints.stringToInt(time);
    }

    public int getTime() {
        return time;
    }
}
