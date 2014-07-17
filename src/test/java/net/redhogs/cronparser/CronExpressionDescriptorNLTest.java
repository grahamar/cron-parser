package net.redhogs.cronparser;

import net.redhogs.cronparser.builder.CronExpressionDescriptor;
import net.redhogs.cronparser.builder.DescriptorParamsBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

public class CronExpressionDescriptorNLTest {

    private static final Locale DUTCH = new Locale("nl");

    private CronExpressionDescriptor descriptor;
    
    @Before
    public void setUp(){
        descriptor =
                DescriptorParamsBuilder.createDescriptor()
                        .withLocale(DUTCH)
                        .forCronType(CronType.UNIX)
                        .build();
    }

    @Test
    public void testEverySecond() throws Exception {
        descriptor =
                DescriptorParamsBuilder.createDescriptor()
                        .withLocale(DUTCH)
                        .forCronType(CronType.QUARTZ)
                        .build();
        Assert.assertEquals("Elke seconde", descriptor.getDescription("* * * * * *"));
    }

    @Test
    public void testEvery45Seconds() throws Exception {
        descriptor =
                DescriptorParamsBuilder.createDescriptor()
                        .withLocale(DUTCH)
                        .forCronType(CronType.QUARTZ)
                        .build();
        Assert.assertEquals("Elke 45 seconden", descriptor.getDescription("*/45 * * * * *"));
    }

    @Test
    public void testMinuteSpan() throws Exception {
        Assert.assertEquals("Elke minuut tussen 11:00 AM en 11:10 AM", descriptor.getDescription("0-10 11 * * *"));
    }

    @Test
    public void testEveryMinute() throws Exception {
        Assert.assertEquals("Elke minuut", descriptor.getDescription("* * * * *"));
        Assert.assertEquals("Elke minuut", descriptor.getDescription("*/1 * * * *"));
        Assert.assertEquals("Elke minuut", descriptor.getDescription("0/1 * * * ?"));
    }

    @Test
    public void testEveryXMinutes() throws Exception {
        Assert.assertEquals("Elke 5 minuten", descriptor.getDescription("*/5 * * * *"));
        Assert.assertEquals("Elke 10 minuten", descriptor.getDescription("0/10 * * * ?"));
    }

    @Test
    public void testEveryHour() throws Exception {
        Assert.assertEquals("Elk uur", descriptor.getDescription("0 * * * ?"));
        Assert.assertEquals("Elk uur", descriptor.getDescription("0 0/1 * * ?"));
    }

    @Test
    public void testDailyAtTime() throws Exception {
        Assert.assertEquals("Om 11:30:00 AM", descriptor.getDescription("30 11 * * *"));
    }

    @Test
    public void testTimeOfDayCertainDaysOfWeek() throws Exception {
        Assert.assertEquals("Om 11:00:00 PM, van maandag tot vrijdag", descriptor.getDescription("0 23 ? * MON-FRI"));
        Assert.assertEquals("Om 11:30:00 AM, van maandag tot vrijdag", descriptor.getDescription("30 11 * * 1-5"));
    }

    @Test
    public void testOneMonthOnly() throws Exception {
        Assert.assertEquals("Elke minuut, alleen in maart", descriptor.getDescription("* * * 3 *"));
    }

    @Test
    public void testTwoMonthsOnly() throws Exception {
        Assert.assertEquals("Elke minuut, alleen in maart en juni", descriptor.getDescription("* * * 3,6 *"));
    }

    @Test
    public void testTwoTimesEachAfternoon() throws Exception {
        Assert.assertEquals("Om 2:30 PM en 4:30 PM", descriptor.getDescription("30 14,16 * * *"));
    }

    @Test
    public void testThreeTimesDaily() throws Exception {
        Assert.assertEquals("Om 6:30 AM, 2:30 PM en 4:30 PM", descriptor.getDescription("30 6,14,16 * * *"));
    }

    @Test
    public void testOnceAWeek() throws Exception {
        Assert.assertEquals("Om 9:46:00 AM, alleen op maandag", descriptor.getDescription("46 9 * * 1"));
    }

    @Test
    public void testOnceAWeekNonZeroBased() throws Exception {
        Options options = new Options();
        options.setZeroBasedDayOfWeek(false);
        Assert.assertEquals("Om 9:46:00 AM, alleen op maandag", descriptor.getDescription("46 9 * * 1"));
        Assert.assertEquals("Om 9:46:00 AM, alleen op dinsdag", descriptor.getDescription("46 9 * * 2"));
        Assert.assertEquals("Om 9:46:00 AM, alleen op zondag", descriptor.getDescription("46 9 * * 7"));
    }

    @Test
    public void testDayOfMonth() throws Exception {
        Assert.assertEquals("Om 12:23:00 PM, op de 15e dag van de maand", descriptor.getDescription("23 12 15 * *"));
    }

    @Test
    public void testMonthName() throws Exception {
        Assert.assertEquals("Om 12:23:00 PM, alleen in januari", descriptor.getDescription("23 12 * JAN *"));
    }

    @Test
    public void testDayOfMonthWithQuestionMark() throws Exception {
        Assert.assertEquals("Om 12:23:00 PM, alleen in januari", descriptor.getDescription("23 12 ? JAN *"));
    }

    @Test
    public void testMonthNameRange2() throws Exception {
        Assert.assertEquals("Om 12:23:00 PM, van januari tot februari", descriptor.getDescription("23 12 * JAN-FEB *"));
    }

    @Test
    public void testMonthNameRange3() throws Exception {
        Assert.assertEquals("Om 12:23:00 PM, van januari tot maart", descriptor.getDescription("23 12 * JAN-MAR *"));
    }

    @Test
    public void testDayOfWeekName() throws Exception {
        Assert.assertEquals("Om 12:23:00 PM, alleen op zondag", descriptor.getDescription("23 12 * * SUN"));
    }

    @Test
    public void testDayOfWeekRange() throws Exception {
        Assert.assertEquals("Elke 5 minuten, om 3:00 PM, van maandag tot vrijdag", descriptor.getDescription("*/5 15 * * MON-FRI"));
    }

    @Test
    public void testDayOfWeekOnceInMonth() throws Exception {
        Assert.assertEquals("Elke minuut, op de derde maandag van de maand", descriptor.getDescription("* * * * MON#3"));
    }

    @Test
    public void testLastDayOfTheWeekOfTheMonth() throws Exception {
        Assert.assertEquals("Elke minuut, op de laatste donderdag van de maand", descriptor.getDescription("* * * * 4L"));
    }

    @Test
    public void testLastDayOfTheMonth() throws Exception {
        Assert.assertEquals("Elke 5 minuten, op de laatste dag van de maand, alleen in januari", descriptor.getDescription("*/5 * L JAN *"));
    }

    @Test
    public void testTimeOfDayWithSeconds() throws Exception {
        descriptor =
                DescriptorParamsBuilder.createDescriptor()
                        .withLocale(DUTCH)
                        .forCronType(CronType.QUARTZ)
                        .build();
        Assert.assertEquals("Om 2:02:30 PM", descriptor.getDescription("30 02 14 * * *"));
    }

    @Test
    public void testSecondInternvals() throws Exception {
        descriptor =
                DescriptorParamsBuilder.createDescriptor()
                        .withLocale(DUTCH)
                        .forCronType(CronType.QUARTZ)
                        .build();
        Assert.assertEquals("Van 5 tot 10 seconden na de minuut", descriptor.getDescription("5-10 * * * * *"));
    }

    @Test
    public void testSecondMinutesHoursIntervals() throws Exception {
        descriptor =
                DescriptorParamsBuilder.createDescriptor()
                        .withLocale(DUTCH)
                        .forCronType(CronType.QUARTZ)
                        .build();
        Assert.assertEquals("Van 5 tot 10 seconden na de minuut, van 30 tot 35 minuten na het uur, tussen 10:00 AM en 12:00 PM",
                descriptor.getDescription("5-10 30-35 10-12 * * *"));
    }

    @Test
    public void testEvery5MinutesAt30Seconds() throws Exception {
        descriptor =
                DescriptorParamsBuilder.createDescriptor()
                        .withLocale(DUTCH)
                        .forCronType(CronType.QUARTZ)
                        .build();
        Assert.assertEquals("30 seconden na de minuut, elke 5 minuten", descriptor.getDescription("30 */5 * * * *"));
    }

    @Test
    public void testMinutesPastTheHourRange() throws Exception {
        Assert.assertEquals("Om 30 minuten na het uur, tussen 10:00 AM en 1:00 PM, alleen op woensdag en vrijdag",
                descriptor.getDescription("30 10-13 ? * WED,FRI"));
    }

    @Test
    public void testSecondsPastTheMinuteInterval() throws Exception {
        descriptor =
                DescriptorParamsBuilder.createDescriptor()
                        .withLocale(DUTCH)
                        .forCronType(CronType.QUARTZ)
                        .build();
        Assert.assertEquals("10 seconden na de minuut, elke 5 minuten", descriptor.getDescription("10 0/5 * * * ?"));
    }

    @Test
    public void testBetweenWithInterval() throws Exception {
        Assert.assertEquals("Elke 3 minuten, van 02 tot 59 minuten na het uur, om 1:00 AM, 9:00 AM, en 10:00 PM, tussen dag 11 en 26 van de maand, van januari tot juni",
                descriptor.getDescription("2-59/3 1,9,22 11-26 1-6 ?"));
    }

    @Test
    public void testRecurringFirstOfMonth() throws Exception {
        Assert.assertEquals("Om 6:00:00 AM", descriptor.getDescription("0 6 1/1 * ?"));
    }

    @Test
    public void testMinutesPastTheHour() throws Exception {
        Assert.assertEquals("Om 05 minuten na het uur", descriptor.getDescription("5 0/1 * * ?"));
    }

    @Test
    public void testEveryPastTheHour() throws Exception {
        Assert.assertEquals("Om 00, 05, 10, 15, 20, 25, 30, 35, 40, 45, 50, en 55 minuten na het uur", descriptor.getDescription("0,5,10,15,20,25,30,35,40,45,50,55 * ? * *"));
    }

}
