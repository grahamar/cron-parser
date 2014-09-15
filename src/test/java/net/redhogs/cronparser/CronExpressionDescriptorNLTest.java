package net.redhogs.cronparser;

import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

public class CronExpressionDescriptorNLTest {

    private static final Locale DUTCH = new Locale("nl");

    @Test
    public void testEverySecond() throws Exception {
        Assert.assertEquals("Elke seconde", CronExpressionDescriptor.getDescription("* * * * * *", DUTCH));
    }

    @Test
    public void testEvery45Seconds() throws Exception {
        Assert.assertEquals("Elke 45 seconden", CronExpressionDescriptor.getDescription("*/45 * * * * *", DUTCH));
    }

    @Test
    public void testMinuteSpan() throws Exception {
        Assert.assertEquals("Elke minuut tussen 11:00 AM en 11:10 AM", CronExpressionDescriptor.getDescription("0-10 11 * * *", DUTCH));
    }

    @Test
    public void testEveryMinute() throws Exception {
        Assert.assertEquals("Elke minuut", CronExpressionDescriptor.getDescription("* * * * *", DUTCH));
        Assert.assertEquals("Elke minuut", CronExpressionDescriptor.getDescription("*/1 * * * *", DUTCH));
        Assert.assertEquals("Elke minuut", CronExpressionDescriptor.getDescription("0 0/1 * * * ?", DUTCH));
    }

    @Test
    public void testEveryXMinutes() throws Exception {
        Assert.assertEquals("Elke 5 minuten", CronExpressionDescriptor.getDescription("*/5 * * * *", DUTCH));
        Assert.assertEquals("Elke 5 minuten", CronExpressionDescriptor.getDescription("0 */5 * * * *", DUTCH));
        Assert.assertEquals("Elke 10 minuten", CronExpressionDescriptor.getDescription("0 0/10 * * * ?", DUTCH));
    }

    @Test
    public void testEveryHour() throws Exception {
        Assert.assertEquals("Elk uur", CronExpressionDescriptor.getDescription("0 0 * * * ?", DUTCH));
        Assert.assertEquals("Elk uur", CronExpressionDescriptor.getDescription("0 0 0/1 * * ?", DUTCH));
    }

    @Test
    public void testDailyAtTime() throws Exception {
        Assert.assertEquals("Om 11:30 AM", CronExpressionDescriptor.getDescription("30 11 * * *", DUTCH));
    }

    @Test
    public void testTimeOfDayCertainDaysOfWeek() throws Exception {
        Assert.assertEquals("Om 11:00 PM, van maandag tot vrijdag", CronExpressionDescriptor.getDescription("0 23 ? * MON-FRI", DUTCH));
        Assert.assertEquals("Om 11:30 AM, van maandag tot vrijdag", CronExpressionDescriptor.getDescription("30 11 * * 1-5", DUTCH));
    }

    @Test
    public void testOneMonthOnly() throws Exception {
        Assert.assertEquals("Elke minuut, alleen in maart", CronExpressionDescriptor.getDescription("* * * 3 *", DUTCH));
    }

    @Test
    public void testTwoMonthsOnly() throws Exception {
        Assert.assertEquals("Elke minuut, alleen in maart en juni", CronExpressionDescriptor.getDescription("* * * 3,6 *", DUTCH));
    }

    @Test
    public void testTwoTimesEachAfternoon() throws Exception {
        Assert.assertEquals("Om 2:30 PM en 4:30 PM", CronExpressionDescriptor.getDescription("30 14,16 * * *", DUTCH));
    }

    @Test
    public void testThreeTimesDaily() throws Exception {
        Assert.assertEquals("Om 6:30 AM, 2:30 PM en 4:30 PM", CronExpressionDescriptor.getDescription("30 6,14,16 * * *", DUTCH));
    }

    @Test
    public void testOnceAWeek() throws Exception {
        Assert.assertEquals("Om 9:46 AM, alleen op maandag", CronExpressionDescriptor.getDescription("46 9 * * 1", DUTCH));
    }

    @Test
    public void testOnceAWeekNonZeroBased() throws Exception {
        Options options = new Options();
        options.setZeroBasedDayOfWeek(false);
        Assert.assertEquals("Om 9:46 AM, alleen op zondag", CronExpressionDescriptor.getDescription("46 9 * * 1", options, DUTCH));
        Assert.assertEquals("Om 9:46 AM, alleen op maandag", CronExpressionDescriptor.getDescription("46 9 * * 2", options, DUTCH));
        Assert.assertEquals("Om 9:46 AM, alleen op zaterdag", CronExpressionDescriptor.getDescription("46 9 * * 7", options, DUTCH));
    }

    @Test
    public void testDayOfMonth() throws Exception {
        Assert.assertEquals("Om 12:23 PM, op de 15e dag van de maand", CronExpressionDescriptor.getDescription("23 12 15 * *", DUTCH));
    }

    @Test
    public void testMonthName() throws Exception {
        Assert.assertEquals("Om 12:23 PM, alleen in januari", CronExpressionDescriptor.getDescription("23 12 * JAN *", DUTCH));
    }

    @Test
    public void testDayOfMonthWithQuestionMark() throws Exception {
        Assert.assertEquals("Om 12:23 PM, alleen in januari", CronExpressionDescriptor.getDescription("23 12 ? JAN *", DUTCH));
    }

    @Test
    public void testMonthNameRange2() throws Exception {
        Assert.assertEquals("Om 12:23 PM, van januari tot februari", CronExpressionDescriptor.getDescription("23 12 * JAN-FEB *", DUTCH));
    }

    @Test
    public void testMonthNameRange3() throws Exception {
        Assert.assertEquals("Om 12:23 PM, van januari tot maart", CronExpressionDescriptor.getDescription("23 12 * JAN-MAR *", DUTCH));
    }

    @Test
    public void testDayOfWeekName() throws Exception {
        Assert.assertEquals("Om 12:23 PM, alleen op zondag", CronExpressionDescriptor.getDescription("23 12 * * SUN", DUTCH));
    }

    @Test
    public void testDayOfWeekRange() throws Exception {
        Assert.assertEquals("Elke 5 minuten, om 3:00 PM, van maandag tot vrijdag", CronExpressionDescriptor.getDescription("*/5 15 * * MON-FRI", DUTCH));
    }

    @Test
    public void testDayOfWeekOnceInMonth() throws Exception {
        Assert.assertEquals("Elke minuut, op de derde maandag van de maand", CronExpressionDescriptor.getDescription("* * * * MON#3", DUTCH));
    }

    @Test
    public void testLastDayOfTheWeekOfTheMonth() throws Exception {
        Assert.assertEquals("Elke minuut, op de laatste donderdag van de maand", CronExpressionDescriptor.getDescription("* * * * 4L", DUTCH));
    }

    @Test
    public void testLastDayOfTheMonth() throws Exception {
        Assert.assertEquals("Elke 5 minuten, op de laatste dag van de maand, alleen in januari", CronExpressionDescriptor.getDescription("*/5 * L JAN *", DUTCH));
    }

    @Test
    public void testTimeOfDayWithSeconds() throws Exception {
        Assert.assertEquals("Om 2:02:30 PM", CronExpressionDescriptor.getDescription("30 02 14 * * *", DUTCH));
    }

    @Test
    public void testSecondInternvals() throws Exception {
        Assert.assertEquals("Van 5 tot 10 seconden na de minuut", CronExpressionDescriptor.getDescription("5-10 * * * * *", DUTCH));
    }

    @Test
    public void testSecondMinutesHoursIntervals() throws Exception {
        Assert.assertEquals("Van 5 tot 10 seconden na de minuut, van 30 tot 35 minuten na het uur, tussen 10:00 AM en 12:00 PM",
                CronExpressionDescriptor.getDescription("5-10 30-35 10-12 * * *", DUTCH));
    }

    @Test
    public void testEvery5MinutesAt30Seconds() throws Exception {
        Assert.assertEquals("30 seconden na de minuut, elke 5 minuten", CronExpressionDescriptor.getDescription("30 */5 * * * *", DUTCH));
    }

    @Test
    public void testMinutesPastTheHourRange() throws Exception {
        Assert.assertEquals("Om 30 minuten na het uur, tussen 10:00 AM en 1:00 PM, alleen op woensdag en vrijdag",
                CronExpressionDescriptor.getDescription("0 30 10-13 ? * WED,FRI", DUTCH));
    }

    @Test
    public void testSecondsPastTheMinuteInterval() throws Exception {
        Assert.assertEquals("10 seconden na de minuut, elke 5 minuten", CronExpressionDescriptor.getDescription("10 0/5 * * * ?", DUTCH));
    }

    @Test
    public void testBetweenWithInterval() throws Exception {
        Assert.assertEquals("Elke 3 minuten, van 02 tot 59 minuten na het uur, om 1:00 AM, 9:00 AM, en 10:00 PM, tussen dag 11 en 26 van de maand, van januari tot juni",
                CronExpressionDescriptor.getDescription("2-59/3 1,9,22 11-26 1-6 ?", DUTCH));
    }

    @Test
    public void testRecurringFirstOfMonth() throws Exception {
        Assert.assertEquals("Om 6:00 AM", CronExpressionDescriptor.getDescription("0 0 6 1/1 * ?", DUTCH));
    }

    @Test
    public void testMinutesPastTheHour() throws Exception {
        Assert.assertEquals("Om 05 minuten na het uur", CronExpressionDescriptor.getDescription("0 5 0/1 * * ?", DUTCH));
    }

    @Test
    public void testEveryPastTheHour() throws Exception {
        Assert.assertEquals("Om 00, 05, 10, 15, 20, 25, 30, 35, 40, 45, 50, en 55 minuten na het uur", CronExpressionDescriptor.getDescription("0 0,5,10,15,20,25,30,35,40,45,50,55 * ? * *", DUTCH));
    }

    @Test
    public void testEveryXMinutePastTheHourWithInterval() throws Exception {
        Assert.assertEquals("Elke 2 minuten, van 00 tot 30 minuten na het uur, om 5:00 PM, van maandag tot vrijdag", CronExpressionDescriptor.getDescription("0 0-30/2 17 ? * MON-FRI", DUTCH));
    }

    @Test
    public void testOneYearOnlyWithSeconds() throws Exception {
        Assert.assertEquals("Elke seconde, alleen in 2013", CronExpressionDescriptor.getDescription("* * * * * * 2013", DUTCH));
    }

    @Test
    public void testOneYearOnlyWithoutSeconds() throws Exception {
        Assert.assertEquals("Elke minuut, alleen in 2013", CronExpressionDescriptor.getDescription("* * * * * 2013", DUTCH));
    }

    @Test
    public void testTwoYearsOnly() throws Exception {
        Assert.assertEquals("Elke minuut, alleen in 2013 en 2014", CronExpressionDescriptor.getDescription("* * * * * 2013,2014", DUTCH));
    }

    @Test
    public void testYearRange2() throws Exception {
        Assert.assertEquals("Om 12:23 PM, van januari tot februari, van 2013 tot 2014", CronExpressionDescriptor.getDescription("23 12 * JAN-FEB * 2013-2014", DUTCH));
    }

    @Test
    public void testYearRange3() throws Exception {
        Assert.assertEquals("Om 12:23 PM, van januari tot maart, van 2013 tot 2015", CronExpressionDescriptor.getDescription("23 12 * JAN-MAR * 2013-2015", DUTCH));
    }

}
