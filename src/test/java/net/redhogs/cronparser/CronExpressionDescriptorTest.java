/**
 * 
 */
package net.redhogs.cronparser;

import net.redhogs.cronparser.CronExpressionDescriptor;

import org.junit.Assert;
import org.junit.Test;



/**
 * @author grhodes
 * @since 10 Dec 2012 14:46:13
 */
public class CronExpressionDescriptorTest {

    @Test
    public void testEverySecond() throws Exception {
        Assert.assertEquals("Every second", CronExpressionDescriptor.getDescription("* * * * * *"));
    }

    @Test
    public void testEvery45Seconds() throws Exception {
        Assert.assertEquals("Every 45 seconds", CronExpressionDescriptor.getDescription("*/45 * * * * *"));
    }

    @Test
    public void testMinuteSpan() throws Exception {
        Assert.assertEquals("Every minute between 11:00 AM and 11:10 AM", CronExpressionDescriptor.getDescription("0-10 11 * * *"));
    }

    @Test
    public void testEveryMinute() throws Exception {
        Assert.assertEquals("Every minute", CronExpressionDescriptor.getDescription("* * * * *"));
        Assert.assertEquals("Every minute", CronExpressionDescriptor.getDescription("*/1 * * * *"));
        Assert.assertEquals("Every minute", CronExpressionDescriptor.getDescription("0 0/1 * * * ?"));
    }

    @Test
    public void testEvery5Minutes() throws Exception {
        Assert.assertEquals("Every 5 minutes", CronExpressionDescriptor.getDescription("*/5 * * * *"));
        Assert.assertEquals("Every 5 minutes", CronExpressionDescriptor.getDescription("0 */5 * * * *"));
        Assert.assertEquals("Every 10 minutes", CronExpressionDescriptor.getDescription("0 0/10 * * * ?"));
    }

    @Test
    public void testEveryHour() throws Exception {
        Assert.assertEquals("Every hour", CronExpressionDescriptor.getDescription("0 0 * * * ?"));
        Assert.assertEquals("Every hour", CronExpressionDescriptor.getDescription("0 0 0/1 * * ?"));
    }

    @Test
    public void testDailyAtTime() throws Exception {
        Assert.assertEquals("At 11:30 AM", CronExpressionDescriptor.getDescription("30 11 * * *"));
    }

    @Test
    public void testTimeOfDayCertainDaysOfWeek() throws Exception {
        Assert.assertEquals("At 11:00 PM, Monday through Friday", CronExpressionDescriptor.getDescription("0 23 ? * MON-FRI"));
        Assert.assertEquals("At 11:30 AM, Monday through Friday", CronExpressionDescriptor.getDescription("30 11 * * 1-5"));
    }

    @Test
    public void testOneMonthOnly() throws Exception {
        Assert.assertEquals("Every minute, only in March", CronExpressionDescriptor.getDescription("* * * 3 *"));
    }

    @Test
    public void testTwoMonthsOnly() throws Exception {
        Assert.assertEquals("Every minute, only in March and June", CronExpressionDescriptor.getDescription("* * * 3,6 *"));
    }

    @Test
    public void testTwoTimesEachAfternoon() throws Exception {
        Assert.assertEquals("At 2:30 PM and 4:30 PM", CronExpressionDescriptor.getDescription("30 14,16 * * *"));
    }

    @Test
    public void testThreeTimesDaily() throws Exception {
        Assert.assertEquals("At 6:30 AM, 2:30 PM and 4:30 PM", CronExpressionDescriptor.getDescription("30 6,14,16 * * *"));
    }

    @Test
    public void testOnceAWeek() throws Exception {
        Assert.assertEquals("At 9:46 AM, only on Monday", CronExpressionDescriptor.getDescription("46 9 * * 1"));
    }

    @Test
    public void testDayOfMonth() throws Exception {
        Assert.assertEquals("At 12:23 PM, on day 15 of the month", CronExpressionDescriptor.getDescription("23 12 15 * *"));
    }

    @Test
    public void testMonthName() throws Exception {
        Assert.assertEquals("At 12:23 PM, only in January", CronExpressionDescriptor.getDescription("23 12 * JAN *"));
    }

    @Test
    public void testDayOfMonthWithQuestionMark() throws Exception {
        Assert.assertEquals("At 12:23 PM, only in January", CronExpressionDescriptor.getDescription("23 12 ? JAN *"));
    }

    @Test
    public void testMonthNameRange2() throws Exception {
        Assert.assertEquals("At 12:23 PM, January through February", CronExpressionDescriptor.getDescription("23 12 * JAN-FEB *"));
    }

    @Test
    public void testMonthNameRange3() throws Exception {
        Assert.assertEquals("At 12:23 PM, January through March", CronExpressionDescriptor.getDescription("23 12 * JAN-MAR *"));
    }

    @Test
    public void testDayOfWeekName() throws Exception {
        Assert.assertEquals("At 12:23 PM, only on Sunday", CronExpressionDescriptor.getDescription("23 12 * * SUN"));
    }

    @Test
    public void testDayOfWeekRange() throws Exception {
        Assert.assertEquals("Every 5 minutes, at 3:00 PM, Monday through Friday", CronExpressionDescriptor.getDescription("*/5 15 * * MON-FRI"));
    }

    @Test
    public void testDayOfWeekOnceInMonth() throws Exception {
        Assert.assertEquals("Every minute, on the third Monday of the month", CronExpressionDescriptor.getDescription("* * * * MON#3"));
    }

    @Test
    public void testLastDayOfTheWeekOfTheMonth() throws Exception {
        Assert.assertEquals("Every minute, on the last Thursday of the month", CronExpressionDescriptor.getDescription("* * * * 4L"));
    }

    @Test
    public void testLastDayOfTheMonth() throws Exception {
        Assert.assertEquals("Every 5 minutes, on the last day of the month, only in January", CronExpressionDescriptor.getDescription("*/5 * L JAN *"));
    }

    @Test
    public void testTimeOfDayWithSeconds() throws Exception {
        Assert.assertEquals("At 2:02:30 PM", CronExpressionDescriptor.getDescription("30 02 14 * * *"));
    }

    @Test
    public void testSecondInternvals() throws Exception {
        Assert.assertEquals("Seconds 5 through 10 past the minute", CronExpressionDescriptor.getDescription("5-10 * * * * *"));
    }

    @Test
    public void testSecondMinutesHoursIntervals() throws Exception {
        Assert.assertEquals("Seconds 5 through 10 past the minute, minutes 30 through 35 past the hour, between 10:00 AM and 12:00 PM",
 CronExpressionDescriptor.getDescription("5-10 30-35 10-12 * * *"));
    }

    @Test
    public void testEvery5MinutesAt30Seconds() throws Exception {
        Assert.assertEquals("At 30 seconds past the minute, every 5 minutes", CronExpressionDescriptor.getDescription("30 */5 * * * *"));
    }

    @Test
    public void testMinutesPastTheHourRange() throws Exception {
        Assert.assertEquals("At 30 minutes past the hour, between 10:00 AM and 1:00 PM, only on Wednesday and Friday",
 CronExpressionDescriptor.getDescription("0 30 10-13 ? * WED,FRI"));
    }

    @Test
    public void testSecondsPastTheMinuteInterval() throws Exception {
        Assert.assertEquals("At 10 seconds past the minute, every 5 minutes", CronExpressionDescriptor.getDescription("10 0/5 * * * ?"));
    }

    @Test
    public void testBetweenWithInterval() throws Exception {
        Assert.assertEquals("Every 3 minutes, minutes 2 through 59 past the hour, at 1:00 AM, 9:00 AM, and 10:00 PM, between day 11 and 26 of the month, January through June",
                CronExpressionDescriptor.getDescription("2-59/3 1,9,22 11-26 1-6 ?"));
    }

    @Test
    public void testRecurringFirstOfMonth() throws Exception {
        Assert.assertEquals("At 6:00:00 AM", CronExpressionDescriptor.getDescription("0 0 6 1/1 * ?"));
    }

    @Test
    public void testMinutesPastTheHour() throws Exception {
        Assert.assertEquals("At 5 minutes past the hour", CronExpressionDescriptor.getDescription("0 5 0/1 * * ?"));
    }

}
