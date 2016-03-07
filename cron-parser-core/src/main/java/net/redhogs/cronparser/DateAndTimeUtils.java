package net.redhogs.cronparser;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

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
    public static String formatTime(String hoursExpression, String minutesExpression, Options opts) {
        return formatTime(hoursExpression, minutesExpression, "", opts);
    }

    /**
     * @param hoursExpression
     * @param minutesExpression
     * @param secondsExpression
     * @return
     */
    public static String formatTime(String hoursExpression, String minutesExpression, String secondsExpression, Options opts) {
        int hour = Integer.parseInt(hoursExpression);
        int minutes = Integer.parseInt(minutesExpression);

        LocalTime localTime;
        DateTimeFormatter timeFormat;

        if(opts.isTwentyFourHourTime()) {
            if (!StringUtils.isEmpty(secondsExpression)) {
                final int seconds = Integer.parseInt(secondsExpression);
                localTime = new LocalTime(hour, minutes, seconds);
                timeFormat = DateTimeFormat.mediumTime();
            } else {
                localTime = new LocalTime(hour, minutes);
                timeFormat = DateTimeFormat.shortTime();
            }
        } else {
            if (!StringUtils.isEmpty(secondsExpression)) {
                final int seconds = Integer.parseInt(secondsExpression);
                localTime = new LocalTime(hour, minutes, seconds);
                timeFormat = DateTimeFormat.forPattern("h:mm:ss a");
            } else {
                localTime = new LocalTime(hour, minutes);
                timeFormat = DateTimeFormat.forPattern("h:mm a");
            }
        }
        return localTime.toString(timeFormat.withLocale(I18nMessages.getCurrentLocale()));
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
