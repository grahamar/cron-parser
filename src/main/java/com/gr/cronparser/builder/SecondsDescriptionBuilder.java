/**
 * 
 */
package com.gr.cronparser.builder;

import org.apache.commons.lang3.StringUtils;


/**
 * @author grhodes
 * @since 10 Dec 2012 14:10:43
 */
public class SecondsDescriptionBuilder extends AbstractDescriptionBuilder {

    @Override
    protected String getSingleItemDescription(String expression) {
        return StringUtils.leftPad(expression, 2, '0');
    }

    @Override
    protected String getIntervalDescriptionFormat(String expression) {
        return String.format("every {0} seconds", expression);
    }

    @Override
    protected String getBetweenDescriptionFormat(String expression) {
        return "seconds {0} through {1} past the minute";
    }

    @Override
    protected String getDescriptionFormat(String expression) {
        return "at {0} seconds past the minute";
    }

}
