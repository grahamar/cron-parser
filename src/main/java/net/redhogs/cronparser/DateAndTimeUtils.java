package net.redhogs.cronparser;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.text.MessageFormat;

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
        String amPM = hour >= 12 ? I18nMessages.get("time_pm") : I18nMessages.get("time_am");
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
        return new DateTime().withDayOfWeek(dayOfWeek).dayOfWeek().getAsText(I18nMessages.getCurrentLocale());
    }

    /**
     * @param minutesExpression
     * @return
     * @since https://github.com/RedHogs/cron-parser/issues/2
     */
    public static String formatMinutes(String minutesExpression) {
        if (StringUtils.contains(minutesExpression, ",")) {
            StringBuilder formattedExpression = new StringBuilder();
            for (String minute : StringUtils.split(minutesExpression, ',')) {
                formattedExpression.append(StringUtils.leftPad(minute, 2, '0'));
                formattedExpression.append(",");
            }
            return formattedExpression.toString();
        }
        return StringUtils.leftPad(minutesExpression, 2, '0');
    }

}
