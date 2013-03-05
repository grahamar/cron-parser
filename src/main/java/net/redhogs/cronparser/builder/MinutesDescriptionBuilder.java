/**
 * 
 */
package net.redhogs.cronparser.builder;

import java.text.MessageFormat;


/**
 * @author grhodes
 * @since 10 Dec 2012 14:11:11
 */
public class MinutesDescriptionBuilder extends AbstractDescriptionBuilder {

    @Override
    protected String getSingleItemDescription(String expression) {
        return expression;
    }

    @Override
    protected String getIntervalDescriptionFormat(String expression) {
        return MessageFormat.format("every {0} " + plural(Integer.parseInt(expression), "minute", "minutes"), expression);
    }

    @Override
    protected String getBetweenDescriptionFormat(String expression) {
        return "minutes {0} through {1} past the hour";
    }

    @Override
    protected String getDescriptionFormat(String expression) {
        return expression == "0" ? "" : "at {0} " + plural(Integer.parseInt(expression), "minute", "minutes") + " past the hour";
    }

}
