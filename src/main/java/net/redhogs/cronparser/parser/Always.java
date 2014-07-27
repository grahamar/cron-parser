package net.redhogs.cronparser.parser;

/**
 * Represents a star (*) value on cron expression field
 */
public class Always extends CronFieldExpression {
    private Every every;

    public Always(FieldConstraints constraints){
        this(constraints, null);
    }

    public Always(FieldConstraints constraints, String every) {
        super(constraints);
        if(every != null){
            this.every = new Every(constraints, every);
        } else {
            this.every = new Every(constraints, "1");
        }
    }
}
