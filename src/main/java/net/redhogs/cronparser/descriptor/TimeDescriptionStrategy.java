package net.redhogs.cronparser.descriptor;

import net.redhogs.cronparser.I18nMessages;
import net.redhogs.cronparser.parser.field.*;

import java.util.ResourceBundle;

class TimeDescriptionStrategy extends DescriptionStrategy {

    private CronFieldExpression seconds;
    private CronFieldExpression minutes;
    private CronFieldExpression hours;

    TimeDescriptionStrategy(ResourceBundle bundle, CronFieldExpression hours, CronFieldExpression minutes, CronFieldExpression seconds){
        super(bundle);
        this.hours = ensureInstance(hours);
        this.minutes = ensureInstance(minutes);
        this.seconds = ensureInstance(seconds);
    }

    private CronFieldExpression ensureInstance(CronFieldExpression expression) {
        if(expression != null){
            return expression;
        }else{
            return new Always(FieldConstraints.nullConstraints());
        }
    }

    @Override
    public String describe() {
        if(minutes instanceof On
                && hours instanceof On){
            if(seconds instanceof Always){
                return describeHoursAndMinutesOn();
            }
            if(seconds instanceof On){
                return String.format("%s %02d:%02d:%02d", bundle.getString("at"), ((On)hours).getTime(), ((On)minutes).getTime(), ((On)seconds).getTime());
            }
            return describe(seconds) + describeHoursAndMinutesOn();
        }
        if(minutes instanceof Always
                && hours instanceof On){
            if(seconds instanceof Always){
                return String.format("%s %02d:00", bundle.getString("at"), ((On)hours).getTime());
            } else {
                return describe(seconds) + String.format("%s %02d:00", bundle.getString("at"), ((On)hours).getTime());
            }
        }
        return describe(seconds) + describe(minutes) + describe(hours);
    }

    private String describeHoursAndMinutesOn(){
        return String.format("%s %02d:%02d", bundle.getString("at"), ((On)hours).getTime(), ((On)minutes).getTime());
    }
}
