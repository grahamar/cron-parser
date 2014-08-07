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
        this.from = getConstraints().validateInRange(getConstraints().intToInt(getConstraints().stringToInt(from)));
        this.to = getConstraints().validateInRange(getConstraints().intToInt(getConstraints().stringToInt(to)));
        this.every = new Every(getConstraints(), every);
        validate();
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

    private void validate(){
        if(from >= to){
            throw new RuntimeException("Bad range defined! Defined range should satisfy from <= to, but was [%s, %s]");
        }
        if(every.getTime() > (to - from)){
            throw new RuntimeException("Every x time cannot exceed range length");
        }
    }
}
