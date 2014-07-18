package net.redhogs.cronparser;

import net.redhogs.cronparser.builder.CronExpressionDescriptor;
import net.redhogs.cronparser.builder.DescriptorParamsBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author grhodes
 * @since 10 Dec 2012 14:46:13
 */
public class CronExpressionDescriptorTest {

    private CronExpressionDescriptor descriptor;

    @Before
    public void setUp(){
        descriptor =
                DescriptorParamsBuilder.createDescriptor()
                        .forCronType(CronType.UNIX)
                        .instance();
    }

    @Test
    public void testEverySecond() throws Exception {
        descriptor =
                DescriptorParamsBuilder.createDescriptor()
                        .forCronType(CronType.QUARTZ)
                        .instance();
        Assert.assertEquals("Every second", descriptor.getDescription("* * * * * *"));
    }

    @Test
    public void testEvery45Seconds() throws Exception {
        descriptor =
                DescriptorParamsBuilder.createDescriptor()
                        .forCronType(CronType.QUARTZ)
                        .instance();
        Assert.assertEquals("Every 45 seconds", descriptor.getDescription("*/45 * * * * *"));
    }

    @Test
    public void testMinuteSpan() throws Exception {
        Assert.assertEquals("Every minute between 11:00 AM and 11:10 AM", descriptor.getDescription("0-10 11 * * *"));
    }

    @Test
    public void testEveryMinute() throws Exception {
        Assert.assertEquals("Every minute", descriptor.getDescription("* * * * *"));
        Assert.assertEquals("Every minute", descriptor.getDescription("*/1 * * * *"));
        Assert.assertEquals("Every minute", descriptor.getDescription("0/1 * * * ?"));
    }

    @Test
    public void testEveryXMinutes() throws Exception {
        Assert.assertEquals("Every 5 minutes", descriptor.getDescription("*/5 * * * *"));
        Assert.assertEquals("Every 10 minutes", descriptor.getDescription("0/10 * * * ?"));
    }

    @Test
    public void testEveryHour() throws Exception {
        Assert.assertEquals("Every hour", descriptor.getDescription("0 * * * ?"));
        Assert.assertEquals("Every hour", descriptor.getDescription("0 0/1 * * ?"));
    }

    @Test
    public void testDailyAtTime() throws Exception {
        Assert.assertEquals("At 11:30:00 AM", descriptor.getDescription("30 11 * * *"));
    }

    @Test
    public void testTimeOfDayCertainDaysOfWeek() throws Exception {
        Assert.assertEquals("At 11:00:00 PM, Monday through Friday", descriptor.getDescription("0 23 ? * MON-FRI"));
        Assert.assertEquals("At 11:30:00 AM, Monday through Friday", descriptor.getDescription("30 11 * * 1-5"));
    }

    @Test
    public void testOneMonthOnly() throws Exception {
        Assert.assertEquals("Every minute, only in March", descriptor.getDescription("* * * 3 *"));
    }

    @Test
    public void testTwoMonthsOnly() throws Exception {
        Assert.assertEquals("Every minute, only in March and June", descriptor.getDescription("* * * 3,6 *"));
    }

    @Test
    public void testTwoTimesEachAfternoon() throws Exception {
        Assert.assertEquals("At 2:30 PM and 4:30 PM", descriptor.getDescription("30 14,16 * * *"));
    }

    @Test
    public void testThreeTimesDaily() throws Exception {
        Assert.assertEquals("At 6:30 AM, 2:30 PM and 4:30 PM", descriptor.getDescription("30 6,14,16 * * *"));
    }

    @Test
    public void testOnceAWeek() throws Exception {
        Assert.assertEquals("At 9:46:00 AM, only on Monday", descriptor.getDescription("46 9 * * 1"));
    }

    @Test
    public void testOnceAWeekNonZeroBased() throws Exception {
        Options options = new Options();
        options.setZeroBasedDayOfWeek(false);
        Assert.assertEquals("At 9:46:00 AM, only on Monday", descriptor.getDescription("46 9 * * 1"));
        Assert.assertEquals("At 9:46:00 AM, only on Tuesday", descriptor.getDescription("46 9 * * 2"));
        Assert.assertEquals("At 9:46:00 AM, only on Sunday", descriptor.getDescription("46 9 * * 7"));
    }

    @Test
    public void testDayOfMonth() throws Exception {
        Assert.assertEquals("At 12:23:00 PM, on day 15 of the month", descriptor.getDescription("23 12 15 * *"));
    }

    @Test
    public void testMonthName() throws Exception {
        Assert.assertEquals("At 12:23:00 PM, only in January", descriptor.getDescription("23 12 * JAN *"));
    }

    @Test
    public void testDayOfMonthWithQuestionMark() throws Exception {
        Assert.assertEquals("At 12:23:00 PM, only in January", descriptor.getDescription("23 12 ? JAN *"));
    }

    @Test
    public void testMonthNameRange2() throws Exception {
        Assert.assertEquals("At 12:23:00 PM, January through February", descriptor.getDescription("23 12 * JAN-FEB *"));
    }

    @Test
    public void testMonthNameRange3() throws Exception {
        Assert.assertEquals("At 12:23:00 PM, January through March", descriptor.getDescription("23 12 * JAN-MAR *"));
    }

    @Test
    public void testDayOfWeekName() throws Exception {
        Assert.assertEquals("At 12:23:00 PM, only on Sunday", descriptor.getDescription("23 12 * * SUN"));
    }

    @Test
    public void testDayOfWeekRange() throws Exception {
        Assert.assertEquals("Every 5 minutes, at 3:00 PM, Monday through Friday", descriptor.getDescription("*/5 15 * * MON-FRI"));
    }

    @Test
    public void testDayOfWeekOnceInMonth() throws Exception {
        Assert.assertEquals("Every minute, on the third Monday of the month", descriptor.getDescription("* * * * MON#3"));
    }

    @Test
    public void testLastDayOfTheWeekOfTheMonth() throws Exception {
        Assert.assertEquals("Every minute, on the last Thursday of the month", descriptor.getDescription("* * * * 4L"));
    }

    @Test
    public void testLastDayOfTheMonth() throws Exception {
        Assert.assertEquals("Every 5 minutes, on the last day of the month, only in January", descriptor.getDescription("*/5 * L JAN *"));
    }

    @Test
    public void testTimeOfDayWithSeconds() throws Exception {
        descriptor =
                DescriptorParamsBuilder.createDescriptor()
                        .forCronType(CronType.QUARTZ)
                        .instance();
        Assert.assertEquals("At 2:02:30 PM", descriptor.getDescription("30 02 14 * * *"));
    }

    @Test
    public void testSecondInternvals() throws Exception {
        descriptor =
                DescriptorParamsBuilder.createDescriptor()
                        .forCronType(CronType.QUARTZ)
                        .instance();
        Assert.assertEquals("Seconds 5 through 10 past the minute", descriptor.getDescription("5-10 * * * * *"));
    }

    @Test
    public void testSecondMinutesHoursIntervals() throws Exception {
        descriptor =
                DescriptorParamsBuilder.createDescriptor()
                        .forCronType(CronType.QUARTZ)
                        .instance();
        Assert.assertEquals("Seconds 5 through 10 past the minute, minutes 30 through 35 past the hour, between 10:00 AM and 12:00 PM",
                descriptor.getDescription("5-10 30-35 10-12 * * *"));
    }

    @Test
    public void testEvery5MinutesAt30Seconds() throws Exception {
        descriptor =
                DescriptorParamsBuilder.createDescriptor()
                        .forCronType(CronType.QUARTZ)
                        .instance();
        Assert.assertEquals("At 30 seconds past the minute, every 5 minutes", descriptor.getDescription("30 */5 * * * *"));
    }

    @Test
    public void testMinutesPastTheHourRange() throws Exception {
        Assert.assertEquals("At 30 minutes past the hour, between 10:00 AM and 1:00 PM, only on Wednesday and Friday",
                descriptor.getDescription("30 10-13 ? * WED,FRI"));
    }

    @Test
    public void testSecondsPastTheMinuteInterval() throws Exception {
        descriptor =
                DescriptorParamsBuilder.createDescriptor()
                        .forCronType(CronType.QUARTZ)
                        .instance();
        Assert.assertEquals("At 10 seconds past the minute, every 5 minutes", descriptor.getDescription("10 0/5 * * * ?"));
    }

    @Test
    public void testBetweenWithInterval() throws Exception {
        Assert.assertEquals("Every 3 minutes, minutes 02 through 59 past the hour, at 1:00 AM, 9:00 AM, and 10:00 PM, between day 11 and 26 of the month, January through June",
                descriptor.getDescription("2-59/3 1,9,22 11-26 1-6 ?"));
    }

    @Test
    public void testRecurringFirstOfMonth() throws Exception {
        Assert.assertEquals("At 6:00:00 AM", descriptor.getDescription("0 6 1/1 * ?"));
    }

    @Test
    public void testMinutesPastTheHour() throws Exception {
        Assert.assertEquals("At 05 minutes past the hour", descriptor.getDescription("5 0/1 * * ?"));
    }

    /**
     * @since https://github.com/RedHogs/cron-parser/issues/2
     */
    @Test
    public void testEveryPastTheHour() throws Exception {
        Assert.assertEquals("At 00, 05, 10, 15, 20, 25, 30, 35, 40, 45, 50, and 55 minutes past the hour", descriptor.getDescription("0,5,10,15,20,25,30,35,40,45,50,55 * ? * *"));
    }

}
