package net.redhogs.cronparser.parser.field;

/**
 * Represents every x time on a cron field.
 */
public class Every extends CronFieldExpression {
    private int time;

    public Every(FieldConstraints constraints, String time) {
        super(constraints);
        if(time == null){
            time = "1";
        }
        this.time = getConstraints().stringToInt(time);
    }

    public int getTime() {
        return time;
    }
}
