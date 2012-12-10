/**
 * 
 */
package com.gr.cronparser.builder;

import java.text.MessageFormat;


/**
 * @author grhodes
 * @since 10 Dec 2012 14:10:43
 */
public class SecondsDescriptionBuilder extends AbstractDescriptionBuilder {

    @Override
    protected String getSingleItemDescription(String expression) {
        return expression;
    }

    @Override
    protected String getIntervalDescriptionFormat(String expression) {
        return MessageFormat.format("every {0} seconds", expression);
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
