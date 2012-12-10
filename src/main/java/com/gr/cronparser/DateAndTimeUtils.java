/**
 * 
 */
package com.gr.cronparser;

import java.text.MessageFormat;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;


/**
 * @author grhodes
 * @since 10 Dec 2012 14:18:58
 */
public final class DateAndTimeUtils {

    private DateAndTimeUtils() {
    }

    /**
     * @param hoursExpression
     * @param minutesExpression
     * @return
     */
    public static String formatTime(String hoursExpression, String minutesExpression) {
        return formatTime(hoursExpression, minutesExpression, "");
    }

    /**
     * @param hoursExpression
     * @param minutesExpression
     * @param secondsExpression
     * @return
     */
    public static String formatTime(String hoursExpression, String minutesExpression, String secondsExpression) {
        int hour = Integer.parseInt(hoursExpression);
        String amPM = hour >= 12 ? "PM" : "AM";
        if (hour > 12) {
            hour -= 12;
        }
        String minute = String.valueOf(Integer.parseInt(minutesExpression));
        String second = "";
        if (!StringUtils.isEmpty(secondsExpression)) {
            second = ":" + StringUtils.leftPad(String.valueOf(Integer.parseInt(secondsExpression)), 2, '0');
        }
        return MessageFormat.format("{0}:{1}{2} {3}", String.valueOf(hour), StringUtils.leftPad(minute, 2, '0'), second, amPM);
    }

    public static String getDayOfWeekName(int dayOfWeek) {
        return new DateTime().withDayOfWeek(dayOfWeek).dayOfWeek().getAsText();
    }

}
