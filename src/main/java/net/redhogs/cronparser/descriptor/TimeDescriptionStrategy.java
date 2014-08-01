package net.redhogs.cronparser.descriptor;

import com.google.common.base.Function;
import com.google.common.collect.Sets;
import net.redhogs.cronparser.parser.field.*;

import java.util.ResourceBundle;
import java.util.Set;

class TimeDescriptionStrategy extends DescriptionStrategy {

    private CronFieldExpression hours;
    private CronFieldExpression minutes;
    private CronFieldExpression seconds;
    private Set<Function<TimeFields,String>> descriptions;

    TimeDescriptionStrategy(ResourceBundle bundle, CronFieldExpression hours, CronFieldExpression minutes, CronFieldExpression seconds){
        super(bundle);
        this.hours = ensureInstance(hours);
        this.minutes = ensureInstance(minutes);
        this.seconds = ensureInstance(seconds);
        descriptions = Sets.newHashSet();
        registerFunctions();
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
        TimeFields fields = new TimeFields(hours, minutes, seconds);
        for(Function<TimeFields, String> function : descriptions){
            if(!"".equals(function.apply(fields))){
                return function.apply(fields);
            }
        }
        return String.format(describe(seconds), bundle.getString("seconds")) +
                String.format(describe(minutes), bundle.getString("minutes")) +
                String.format(describe(hours), bundle.getString("hours"));
    }

    private void registerFunctions() {
        //11:45
        descriptions.add(
                new Function<TimeFields, String>() {
                    @Override
                    public String apply(TimeFields timeFields) {
                        if (timeFields.hours instanceof On &&
                                timeFields.minutes instanceof On
                                &&  timeFields.seconds instanceof Always) {
                            return String.format("%s %02d:%02d", bundle.getString("at"), ((On)hours).getTime(), ((On)minutes).getTime());
                        }
                        return "";
                    }
                });

        //11:30:45
        descriptions.add(
                new Function<TimeFields, String>() {
                    @Override
                    public String apply(TimeFields timeFields) {
                        if (timeFields.hours instanceof On &&
                                timeFields.minutes instanceof On
                                &&  timeFields.seconds instanceof On) {
                            return String.format("%s %02d:%02d:%02d", bundle.getString("at"), ((On)hours).getTime(), ((On)minutes).getTime(), ((On)seconds).getTime());
                        }
                        return "";
                    }
                });

        //11 -> 11:00
        descriptions.add(
                new Function<TimeFields, String>() {
                    @Override
                    public String apply(TimeFields timeFields) {
                        if (timeFields.hours instanceof On &&
                                timeFields.minutes instanceof Always &&
                                timeFields.seconds instanceof Always) {
                            return String.format("%s %02d:00", bundle.getString("at"), ((On)hours).getTime());
                        }
                        return "";
                    }
                });

//        "Every minute between 11:00 and 11:10"
        descriptions.add(
                new Function<TimeFields, String>() {
                    @Override
                    public String apply(TimeFields timeFields) {
                        if (timeFields.minutes instanceof Between &&
                                timeFields.hours instanceof On) {
                            if(timeFields.seconds instanceof Always){
                                return String.format("%s %s %s %02d:%02d %s %02d:%02d",
                                        bundle.getString("every"),
                                        bundle.getString("minute"),
                                        bundle.getString("between"),
                                        ((On) timeFields.hours).getTime(),((Between) timeFields.minutes).getFrom(),
                                        bundle.getString("and"),
                                        ((On) timeFields.hours).getTime(),((Between) timeFields.minutes).getTo());
                            }
                        }
                        return "";
                    }
                });
    }

    class TimeFields{
        public CronFieldExpression seconds;
        public CronFieldExpression minutes;
        public CronFieldExpression hours;

        public TimeFields(CronFieldExpression hours, CronFieldExpression minutes, CronFieldExpression seconds){
            this.hours = hours;
            this.minutes = minutes;
            this.seconds = seconds;
        }
    }
}
