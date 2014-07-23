package net.redhogs.cronparser.parser;

public class Between extends CronFieldExpression {
    private int from;
    private int to;
    private Every every;

    public Between(String from, String to) {
        this(from, to, "1");
    }

    public Between(String from, String to, String every) {
        super();
        this.from = validateInRange(intToInt(stringToInt(from)));
        this.to = validateInRange(intToInt(stringToInt(to)));
        this.every = new Every(every);
    }
}
