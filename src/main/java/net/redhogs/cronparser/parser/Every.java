package net.redhogs.cronparser.parser;

/**
 * Represents every x time on a cron field.
 */
public class Every extends CronFieldExpression {
    private int number;

    public Every(String number){
        super();
        this.number = stringToInt(number);
    }
}
