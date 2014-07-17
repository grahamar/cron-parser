package net.redhogs.cronparser.parser;

import net.redhogs.cronparser.CronType;

/**
 * Created by jose.rozanec on 7/16/14.
 */
public class ParserFactory {

    private static final QuartzCronExpressionParser quartzCronExpressionParser = new QuartzCronExpressionParser();
    private static final UnixCronExpressionParser unixCronExpressionParser = new UnixCronExpressionParser();

    private ParserFactory(){}

    public CronExpressionParser getQuartzCronExpressionParser(){
        return quartzCronExpressionParser;
    }

    public CronExpressionParser getUnixCronExpressionParser(){
        return unixCronExpressionParser;
    }

    public CronExpressionParser retrieveInstanceForType(CronType type){
        switch (type){
            case QUARTZ:
                return quartzCronExpressionParser;
            default:
                return unixCronExpressionParser;
        }
    }

    public static ParserFactory getInstance(){
        return new ParserFactory();
    }
}
