/**
 * 
 */
package net.redhogs.cronparser.builder;

import java.text.MessageFormat;

import org.apache.commons.lang3.StringUtils;


/**
 * @author grhodes
 * @since 10 Dec 2012 14:00:49
 */
public abstract class AbstractDescriptionBuilder {

    protected static final char[] specialCharsMinusStar = new char[] { '/', '-', ',' };

    public String getSegmentDescription(String expression, String allDescription) {
        String description = "";
        if (StringUtils.isEmpty(expression) || "0".equals(expression)) {
            description = "";
        } else if ("*".equals(expression)) {
            description = allDescription;
        } else if (!StringUtils.containsAny(expression, specialCharsMinusStar)) {
            description = MessageFormat.format(getDescriptionFormat(expression), getSingleItemDescription(expression));
        } else if (expression.contains("/")) {
            String[] segments = expression.split("/");
            description = MessageFormat.format(getIntervalDescriptionFormat(segments[1]), getSingleItemDescription(segments[1]));
            // interval contains 'between' piece (e.g. 2-59/3)
            if (segments[0].contains("-")) {
                String betweenSegmentOfInterval = segments[0];
                String[] betweenSegments = betweenSegmentOfInterval.split("-");
                description += ", " + MessageFormat.format(getBetweenDescriptionFormat(betweenSegmentOfInterval), getSingleItemDescription(betweenSegments[0]), getSingleItemDescription(betweenSegments[1]));
            }
        } else if (expression.contains("-")) {
            String[] segments = expression.split("-");
            description = MessageFormat.format(getBetweenDescriptionFormat(expression), getSingleItemDescription(segments[0]), getSingleItemDescription(segments[1]));
        } else if (expression.contains(",")) {
            String[] segments = expression.split(",");
            String descriptionContent = "";
            for (int i = 0; i < segments.length; i++) {
                if (i > 0 && segments.length > 2) {
                    descriptionContent += ",";
                    if (i < segments.length - 1) {
                        descriptionContent += " ";
                    }
                }
                if (i > 0 && segments.length > 1 && (i == segments.length - 1 || segments.length == 2)) {
                    descriptionContent += " and ";
                }
                descriptionContent += getSingleItemDescription(segments[i]);
            }
            description = MessageFormat.format(getDescriptionFormat(expression), descriptionContent);
        }
        return description;
    }

    /**
     * @param expression
     * @return
     */
    protected abstract String getBetweenDescriptionFormat(String expression);

    /**
     * @param expression
     * @return
     */
    protected abstract String getIntervalDescriptionFormat(String expression);

    /**
     * @param expression
     * @return
     */
    protected abstract String getSingleItemDescription(String expression);

    /**
     * @param expression
     * @return
     */
    protected abstract String getDescriptionFormat(String expression);

    /**
     * @param num
     * @param singular
     * @param plural
     * @return
     */
    protected String plural(int num, String singular, String plural) {
        if (num > 1) {
            return plural;
        }
        return singular;
    }

}
