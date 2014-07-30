package net.redhogs.cronparser.descriptor;

import com.google.common.base.Function;
import net.redhogs.cronparser.I18nMessages;
import net.redhogs.cronparser.parser.field.CronFieldExpression;
import org.joda.time.DateTime;

import java.util.ResourceBundle;

class DescriptionStrategyFactory {
    public static DescriptionStrategy daysOfWeekInstance(ResourceBundle bundle, final CronFieldExpression expression){
        return new NominalDescriptionStrategy(
                bundle,
                new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) {
                        return new DateTime().withDayOfWeek(integer).dayOfWeek().getAsText(I18nMessages.getCurrentLocale());
                    }
                },
                expression
        );
    }

    public static DescriptionStrategy monthsInstance(ResourceBundle bundle, final CronFieldExpression expression){
        return new NominalDescriptionStrategy(
                bundle,
                new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) {
                        return new DateTime().withMonthOfYear(integer).monthOfYear().getAsText(I18nMessages.getCurrentLocale());
                    }
                },
                expression
        );
    }

    public static DescriptionStrategy plainInstance(ResourceBundle bundle, final CronFieldExpression expression){
        return new NominalDescriptionStrategy(bundle, null,expression);
    }

    public static DescriptionStrategy hhMMssInstance(ResourceBundle bundle, final CronFieldExpression hours, final CronFieldExpression minutes, final CronFieldExpression seconds){
        return new TimeDescriptionStrategy(hours, minutes, seconds);
    }
}
