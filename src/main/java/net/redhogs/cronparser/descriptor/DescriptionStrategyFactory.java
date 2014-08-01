package net.redhogs.cronparser.descriptor;

import com.google.common.base.Function;
import net.redhogs.cronparser.parser.field.CronFieldExpression;
import org.joda.time.DateTime;

import java.util.ResourceBundle;

class DescriptionStrategyFactory {
    public static DescriptionStrategy daysOfWeekInstance(final ResourceBundle bundle, final CronFieldExpression expression){
        return new NominalDescriptionStrategy(
                bundle,
                new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) {
                        return new DateTime().withDayOfWeek(integer).dayOfWeek().getAsText(bundle.getLocale());
                    }
                },
                expression
        );
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
        return new NominalDescriptionStrategy(bundle, null,expression);
    }

    public static DescriptionStrategy hhMMssInstance(ResourceBundle bundle, final CronFieldExpression hours,
                                                     final CronFieldExpression minutes, final CronFieldExpression seconds){
        return new TimeDescriptionStrategy(bundle, hours, minutes, seconds);
    }
}
