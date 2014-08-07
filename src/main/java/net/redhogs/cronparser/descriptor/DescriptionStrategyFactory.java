package net.redhogs.cronparser.descriptor;

import com.google.common.base.Function;
import net.redhogs.cronparser.parser.field.CronFieldExpression;
import net.redhogs.cronparser.parser.field.On;
import org.joda.time.DateTime;

import java.util.ResourceBundle;

class DescriptionStrategyFactory {
    public static DescriptionStrategy daysOfWeekInstance(final ResourceBundle bundle, final CronFieldExpression expression){
        final Function<Integer, String> nominal = new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return new DateTime().withDayOfWeek(integer).dayOfWeek().getAsText(bundle.getLocale());
            }
        };

        NominalDescriptionStrategy dow = new NominalDescriptionStrategy(bundle, nominal, expression);

        dow.addDescription(new Function<CronFieldExpression, String>() {
            @Override
            public String apply(CronFieldExpression cronFieldExpression) {
                if(cronFieldExpression instanceof On){
                    On on = (On)cronFieldExpression;
                    switch (on.getSpecialChar()){
                        case HASH:
                            return String.format("%s %s %s ", nominal.apply(on.getTime()), on.getNth(), bundle.getString("of_every_month"));
                        case L:
                            return String.format("%s %s %s ", bundle.getString("last"), nominal.apply(on.getTime()), bundle.getString("of_every_month"));
                        default:
                            return "";
                    }
                }
                return "";
            }
        });
        return dow;
    }

    public static DescriptionStrategy daysOfMonthInstance(final ResourceBundle bundle, final CronFieldExpression expression){
        NominalDescriptionStrategy dow = new NominalDescriptionStrategy(bundle, null, expression);

        dow.addDescription(new Function<CronFieldExpression, String>() {
            @Override
            public String apply(CronFieldExpression cronFieldExpression) {
                if(cronFieldExpression instanceof On){
                    On on = (On)cronFieldExpression;
                    switch (on.getSpecialChar()){
                        case W:
                            return String.format("%s %s %s ", bundle.getString("the_nearest_weekday_to_the"), on.getTime(), bundle.getString("of_the_month"));
                        case L:
                            return bundle.getString("last_day_of_month");
                        default:
                            return "";
                    }
                }
                return "";
            }
        });
        return dow;
    }

    public static DescriptionStrategy monthsInstance(final ResourceBundle bundle, final CronFieldExpression expression){
        return new NominalDescriptionStrategy(
                bundle,
                new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) {
                        return new DateTime().withMonthOfYear(integer).monthOfYear().getAsText(bundle.getLocale());
                    }
                },
                expression
        );
    }

    public static DescriptionStrategy plainInstance(ResourceBundle bundle, final CronFieldExpression expression){
        return new NominalDescriptionStrategy(bundle, null, expression);
    }

    public static DescriptionStrategy hhMMssInstance(ResourceBundle bundle, final CronFieldExpression hours,
                                                     final CronFieldExpression minutes, final CronFieldExpression seconds){
        return new TimeDescriptionStrategy(bundle, hours, minutes, seconds);
    }
}
