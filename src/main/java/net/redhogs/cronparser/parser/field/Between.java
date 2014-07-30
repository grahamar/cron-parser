package net.redhogs.cronparser.parser.field;

/**
 * Represents a range in a cron expression.
 */
public class Between extends CronFieldExpression {
    private int from;
    private int to;
    private Every every;

    public Between(FieldConstraints constraints, String from, String to) {
        this(constraints, from, to, "1");
    }

    public Between(FieldConstraints constraints, String from, String to, String every) {
        super(constraints);
        this.from = constraints.validateInRange(constraints.intToInt(constraints.stringToInt(from)));
        this.to = constraints.validateInRange(constraints.intToInt(constraints.stringToInt(to)));
        this.every = new Every(constraints, every);
    }

    public int getFrom(){
        return from;
    }

    public int getTo(){
        return to;
    }

    public Every getEvery(){
        return every;
    }
}
