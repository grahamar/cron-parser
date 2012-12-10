/**
 * 
 */
package com.gr.cronparser.builder;

import java.text.MessageFormat;

import org.joda.time.DateTime;


/**
 * @author grhodes
 * @since 10 Dec 2012 14:23:50
 */
public class MonthDescriptionBuilder extends AbstractDescriptionBuilder {

    @Override
    protected String getSingleItemDescription(String expression) {
        return new DateTime().withDayOfMonth(1).withMonthOfYear(Integer.parseInt(expression)).toString("MMMM");
    }

    @Override
    protected String getIntervalDescriptionFormat(String expression) {
        return MessageFormat.format(", every {0} " + plural(Integer.parseInt(expression), "month", "months"), expression);
    }

    @Override
    protected String getBetweenDescriptionFormat(String expression) {
        return ", {0} through {1}";
    }

    @Override
    protected String getDescriptionFormat(String expression) {
        return ", only in {0}";
    }

}
