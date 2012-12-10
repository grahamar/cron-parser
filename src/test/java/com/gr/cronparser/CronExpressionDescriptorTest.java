/**
 * 
 */
package com.gr.cronparser;

import org.junit.Assert;
import org.junit.Test;


/**
 * @author grhodes
 * @since 10 Dec 2012 14:46:13
 */
public class CronExpressionDescriptorTest {

    @Test
    public void testEverySecond() throws Exception {
        CronExpressionDescriptor descriptor = new CronExpressionDescriptor("* * * * * *");
        Assert.assertEquals("Every second", descriptor.getDescription(DescriptionTypeEnum.FULL));
    }

    @Test
    public void testEvery45Seconds() throws Exception {
        CronExpressionDescriptor descriptor = new CronExpressionDescriptor("*/45 * * * * *");
        Assert.assertEquals("Every 45 seconds", descriptor.getDescription(DescriptionTypeEnum.FULL));
    }

    @Test
    public void testMinuteSpan() throws Exception {
        CronExpressionDescriptor descriptor = new CronExpressionDescriptor("0-10 11 * * *");
        Assert.assertEquals("Every minute between 11:00 AM and 11:10 AM", descriptor.getDescription(DescriptionTypeEnum.FULL));
    }

    @Test
    public void testEveryMinute() throws Exception {
        CronExpressionDescriptor descriptor = new CronExpressionDescriptor("* * * * *");
        Assert.assertEquals("Every minute", descriptor.getDescription(DescriptionTypeEnum.FULL));

        descriptor = new CronExpressionDescriptor("*/1 * * * *");
        Assert.assertEquals("Every minute", descriptor.getDescription(DescriptionTypeEnum.FULL));

        descriptor = new CronExpressionDescriptor("0 0/1 * * * ?");
        Assert.assertEquals("Every minute", descriptor.getDescription(DescriptionTypeEnum.FULL));
    }

    @Test
    public void testEvery5Minutes() throws Exception {
        CronExpressionDescriptor descriptor = new CronExpressionDescriptor("*/5 * * * *");
        Assert.assertEquals("Every 5 minutes", descriptor.getDescription(DescriptionTypeEnum.FULL));

        descriptor = new CronExpressionDescriptor("0 */5 * * * *");
        Assert.assertEquals("Every 5 minutes", descriptor.getDescription(DescriptionTypeEnum.FULL));

        descriptor = new CronExpressionDescriptor("0 0/10 * * * ?");
        Assert.assertEquals("Every 10 minutes", descriptor.getDescription(DescriptionTypeEnum.FULL));
    }

    @Test
    public void testEveryHour() throws Exception {
        CronExpressionDescriptor descriptor = new CronExpressionDescriptor("0 0 * * * ?");
        Assert.assertEquals("Every hour", descriptor.getDescription(DescriptionTypeEnum.FULL));

        descriptor = new CronExpressionDescriptor("0 0 0/1 * * ?");
        Assert.assertEquals("Every hour", descriptor.getDescription(DescriptionTypeEnum.FULL));
    }

    @Test
    public void testDailyAtTime() throws Exception {
        CronExpressionDescriptor descriptor = new CronExpressionDescriptor("30 11 * * *");
        Assert.assertEquals("At 11:30 AM", descriptor.getDescription(DescriptionTypeEnum.FULL));
    }

    @Test
    public void testTimeOfDayCertainDaysOfWeek() throws Exception {
        CronExpressionDescriptor descriptor = new CronExpressionDescriptor("0 23 ? * MON-FRI");
        Assert.assertEquals("At 11:00 PM, Monday through Friday", descriptor.getDescription(DescriptionTypeEnum.FULL));

        descriptor = new CronExpressionDescriptor("30 11 * * 1-5");
        Assert.assertEquals("At 11:30 AM, Monday through Friday", descriptor.getDescription(DescriptionTypeEnum.FULL));
    }

    @Test
    public void testOneMonthOnly() throws Exception {
        CronExpressionDescriptor descriptor = new CronExpressionDescriptor("* * * 3 *");
        Assert.assertEquals("Every minute, only in March", descriptor.getDescription(DescriptionTypeEnum.FULL));
    }

    @Test
    public void testTwoMonthsOnly() throws Exception {
        CronExpressionDescriptor descriptor = new CronExpressionDescriptor("* * * 3,6 *");
        Assert.assertEquals("Every minute, only in March and June", descriptor.getDescription(DescriptionTypeEnum.FULL));
    }

    @Test
    public void testTwoTimesEachAfternoon() throws Exception {
        CronExpressionDescriptor descriptor = new CronExpressionDescriptor("30 14,16 * * *");
        Assert.assertEquals("At 2:30 PM and 4:30 PM", descriptor.getDescription(DescriptionTypeEnum.FULL));
    }

    @Test
    public void testThreeTimesDaily() throws Exception {
        CronExpressionDescriptor descriptor = new CronExpressionDescriptor("30 6,14,16 * * *");
        Assert.assertEquals("At 6:30 AM, 2:30 PM and 4:30 PM", descriptor.getDescription(DescriptionTypeEnum.FULL));
    }

    @Test
    public void testOnceAWeek() throws Exception {
        CronExpressionDescriptor descriptor = new CronExpressionDescriptor("46 9 * * 1");
        Assert.assertEquals("At 9:46 AM, only on Monday", descriptor.getDescription(DescriptionTypeEnum.FULL));
    }

    @Test
    public void testDayOfMonth() throws Exception {
        CronExpressionDescriptor descriptor = new CronExpressionDescriptor("23 12 15 * *");
        Assert.assertEquals("At 12:23 PM, on day 15 of the month", descriptor.getDescription(DescriptionTypeEnum.FULL));
    }

    @Test
    public void testMonthName() throws Exception {
        CronExpressionDescriptor descriptor = new CronExpressionDescriptor("23 12 * JAN *");
        Assert.assertEquals("At 12:23 PM, only in January", descriptor.getDescription(DescriptionTypeEnum.FULL));
    }

    @Test
    public void testDayOfMonthWithQuestionMark() throws Exception {
        CronExpressionDescriptor descriptor = new CronExpressionDescriptor("23 12 ? JAN *");
        Assert.assertEquals("At 12:23 PM, only in January", descriptor.getDescription(DescriptionTypeEnum.FULL));
    }

    @Test
    public void testMonthNameRange2() throws Exception {
        CronExpressionDescriptor descriptor = new CronExpressionDescriptor("23 12 * JAN-FEB *");
        Assert.assertEquals("At 12:23 PM, January through February", descriptor.getDescription(DescriptionTypeEnum.FULL));
    }

    @Test
    public void testMonthNameRange3() throws Exception {
        CronExpressionDescriptor descriptor = new CronExpressionDescriptor("23 12 * JAN-MAR *");
        Assert.assertEquals("At 12:23 PM, January through March", descriptor.getDescription(DescriptionTypeEnum.FULL));
    }

    @Test
    public void testDayOfWeekName() throws Exception {
        CronExpressionDescriptor descriptor = new CronExpressionDescriptor("23 12 * * SUN");
        Assert.assertEquals("At 12:23 PM, only on Sunday", descriptor.getDescription(DescriptionTypeEnum.FULL));
    }

    @Test
    public void testDayOfWeekRange() throws Exception {
        CronExpressionDescriptor descriptor = new CronExpressionDescriptor("*/5 15 * * MON-FRI");
        Assert.assertEquals("Every 5 minutes, at 3:00 PM, Monday through Friday", descriptor.getDescription(DescriptionTypeEnum.FULL));
    }

    @Test
    public void testDayOfWeekOnceInMonth() throws Exception {
        CronExpressionDescriptor descriptor = new CronExpressionDescriptor("* * * * MON#3");
        Assert.assertEquals("Every minute, on the third Monday of the month", descriptor.getDescription(DescriptionTypeEnum.FULL));
    }

    @Test
    public void testLastDayOfTheWeekOfTheMonth() throws Exception {
        CronExpressionDescriptor descriptor = new CronExpressionDescriptor("* * * * 4L");
        Assert.assertEquals("Every minute, on the last Thursday of the month", descriptor.getDescription(DescriptionTypeEnum.FULL));
    }

    @Test
    public void testLastDayOfTheMonth() throws Exception {
        CronExpressionDescriptor descriptor = new CronExpressionDescriptor("*/5 * L JAN *");
        Assert.assertEquals("Every 5 minutes, on the last day of the month, only in January", descriptor.getDescription(DescriptionTypeEnum.FULL));
    }

    @Test
    public void testTimeOfDayWithSeconds() throws Exception {
        CronExpressionDescriptor descriptor = new CronExpressionDescriptor("30 02 14 * * *");
        Assert.assertEquals("At 2:02:30 PM", descriptor.getDescription(DescriptionTypeEnum.FULL));
    }

    @Test
    public void testSecondInternvals() throws Exception {
        CronExpressionDescriptor descriptor = new CronExpressionDescriptor("5-10 * * * * *");
        Assert.assertEquals("Seconds 5 through 10 past the minute", descriptor.getDescription(DescriptionTypeEnum.FULL));
    }

    @Test
    public void testSecondMinutesHoursIntervals() throws Exception {
        CronExpressionDescriptor descriptor = new CronExpressionDescriptor("5-10 30-35 10-12 * * *");
        Assert.assertEquals("Seconds 5 through 10 past the minute, minutes 30 through 35 past the hour, between 10:00 AM and 12:00 PM", descriptor.getDescription(DescriptionTypeEnum.FULL));
    }

    @Test
    public void testEvery5MinutesAt30Seconds() throws Exception {
        CronExpressionDescriptor descriptor = new CronExpressionDescriptor("30 */5 * * * *");
        Assert.assertEquals("At 30 seconds past the minute, every 5 minutes", descriptor.getDescription(DescriptionTypeEnum.FULL));
    }

    @Test
    public void testMinutesPastTheHourRange() throws Exception {
        CronExpressionDescriptor descriptor = new CronExpressionDescriptor("0 30 10-13 ? * WED,FRI");
        Assert.assertEquals("At 30 minutes past the hour, between 10:00 AM and 1:00 PM, only on Wednesday and Friday", descriptor.getDescription(DescriptionTypeEnum.FULL));
    }

    @Test
    public void testSecondsPastTheMinuteInterval() throws Exception {
        CronExpressionDescriptor descriptor = new CronExpressionDescriptor("10 0/5 * * * ?");
        Assert.assertEquals("At 10 seconds past the minute, every 5 minutes", descriptor.getDescription(DescriptionTypeEnum.FULL));
    }

    @Test
    public void testBetweenWithInterval() throws Exception {
        CronExpressionDescriptor descriptor = new CronExpressionDescriptor("2-59/3 1,9,22 11-26 1-6 ?");
        Assert.assertEquals("Every 3 minutes, minutes 2 through 59 past the hour, at 1:00 AM, 9:00 AM, and 10:00 PM, between day 11 and 26 of the month, January through June",
                descriptor.getDescription(DescriptionTypeEnum.FULL));
    }

    @Test
    public void testRecurringFirstOfMonth() throws Exception {
        CronExpressionDescriptor descriptor = new CronExpressionDescriptor("0 0 6 1/1 * ?");
        Assert.assertEquals("At 6:00:00 AM", descriptor.getDescription(DescriptionTypeEnum.FULL));
    }

    @Test
    public void testMinutesPastTheHour() throws Exception {
        CronExpressionDescriptor descriptor = new CronExpressionDescriptor("0 5 0/1 * * ?");
        Assert.assertEquals("At 5 minutes past the hour", descriptor.getDescription(DescriptionTypeEnum.FULL));
    }

}
