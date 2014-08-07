package net.redhogs.cronparser.parser;

import net.redhogs.cronparser.CronType;

@Deprecated
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
                return getQuartzCronExpressionParser();
            default:
                return getUnixCronExpressionParser();
        }
    }

    public static ParserFactory getInstance(){
        return new ParserFactory();
    }
}
