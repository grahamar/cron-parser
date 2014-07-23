package net.redhogs.cronparser.parser;

public class On extends CronFieldExpression {
    private int number;

    public On(String exp){
        super();
        number = validateInRange(intToInt(stringToInt(exp)));
    }
}
