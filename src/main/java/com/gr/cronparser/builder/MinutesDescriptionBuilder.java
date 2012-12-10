/**
 * 
 */
package com.gr.cronparser.builder;

import org.apache.commons.lang3.StringUtils;


/**
 * @author grhodes
 * @since 10 Dec 2012 14:11:11
 */
public class MinutesDescriptionBuilder extends AbstractDescriptionBuilder {

    @Override
    protected String getSingleItemDescription(String expression) {
        return StringUtils.leftPad(expression, 2, '0');
    }

    @Override
    protected String getIntervalDescriptionFormat(String expression) {
        return String.format("every {0} minutes", StringUtils.leftPad(expression, 2, '0'));
    }

    @Override
    protected String getBetweenDescriptionFormat(String expression) {
        return "minutes {0} through {1} past the hour";
    }

    @Override
    protected String getDescriptionFormat(String expression) {
        return expression == "0" ? "" : "at {0} minutes past the hour";
    }

}
