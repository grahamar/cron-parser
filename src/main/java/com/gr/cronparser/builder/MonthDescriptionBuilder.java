/**
 * 
 */
package com.gr.cronparser.builder;

import org.apache.commons.lang3.StringUtils;

import com.gr.cronparser.CronExpressionUtils;


/**
 * @author grhodes
 * @since 10 Dec 2012 14:23:50
 */
public class MonthDescriptionBuilder extends AbstractDescriptionBuilder {

    @Override
    protected String getSingleItemDescription(String expression) {
        return CronExpressionUtils.formatTime(expression, "0");
    }

    @Override
    protected String getIntervalDescriptionFormat(String expression) {
        return String.format("every {0} hours", StringUtils.leftPad(expression, 2, '0'));
    }

    @Override
    protected String getBetweenDescriptionFormat(String expression) {
        return "between {0} and {1}";
    }

    @Override
    protected String getDescriptionFormat(String expression) {
        return "at {0}";
    }

}
