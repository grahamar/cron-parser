package net.redhogs.cronparser.parser;

public class Every extends CronFieldExpression {
    private int number;

    public Every(String number){
        super();
        this.number = stringToInt(number);
    }
}
