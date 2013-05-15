/**
 * 
 */
package net.redhogs.cronparser.builder;


/**
 * @author grhodes
 * @since 10 Dec 2012 14:24:08
 */
public class DayOfMonthDescriptionBuilder extends AbstractDescriptionBuilder {

    @Override
    protected String getSingleItemDescription(String expression) {
        return expression;
    }

    @Override
    protected String getIntervalDescriptionFormat(String expression) {
        return ", every {0} " + plural(expression, "day", "days");
    }

    @Override
    protected String getBetweenDescriptionFormat(String expression) {
        return ", between day {0} and {1} of the month";
    }

    @Override
    protected String getDescriptionFormat(String expression) {
        return ", on day {0} of the month";
    }

}
