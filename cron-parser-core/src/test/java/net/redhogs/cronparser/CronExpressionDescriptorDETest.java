package net.redhogs.cronparser;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author grhodes
 * @since 10 Dec 2012 14:46:13
 */
public class CronExpressionDescriptorDETest {

    private static final Locale GERMAN = new Locale("de");

    @Test
    public void testEverySecond() throws Exception {
        Assert.assertEquals("Jede Sekunde",
                CronExpressionDescriptor.getDescription("* * * * * *", GERMAN));
    }

    @Test
    public void testEvery45Seconds() throws Exception {
        Assert.assertEquals("Alle 45 Sekunden",
                CronExpressionDescriptor.getDescription("*/45 * * * * *", GERMAN));
    }

    @Test
    public void testMinuteSpan() throws Exception {
        Assert.assertEquals("Jede Minute zwischen 11:00 und 11:10",
                CronExpressionDescriptor.getDescription("0-10 11 * * *", GERMAN));
    }

    @Test
    public void testEveryMinute() throws Exception {
        Assert.assertEquals("Jede Minute",
                CronExpressionDescriptor.getDescription("* * * * *", GERMAN));
        Assert.assertEquals("Jede Minute",
                CronExpressionDescriptor.getDescription("*/1 * * * *", GERMAN));
        Assert.assertEquals("Jede Minute",
                CronExpressionDescriptor.getDescription("0 0/1 * * * ?", GERMAN));
    }

    @Test
    public void testEveryXMinutes() throws Exception {
        Assert.assertEquals("Alle 5 Minuten",
                CronExpressionDescriptor.getDescription("*/5 * * * *", GERMAN));
        Assert.assertEquals("Alle 5 Minuten",
                CronExpressionDescriptor.getDescription("0 */5 * * * *", GERMAN));
        Assert.assertEquals("Alle 10 Minuten",
                CronExpressionDescriptor.getDescription("0 0/10 * * * ?", GERMAN));
    }

    @Test
    public void testEveryHour() throws Exception {
        Assert.assertEquals("Jede Stunde",
                CronExpressionDescriptor.getDescription("0 0 * * * ?", GERMAN));
        Assert.assertEquals("Jede Stunde",
                CronExpressionDescriptor.getDescription("0 0 0/1 * * ?", GERMAN));
    }

    @Test
    public void testDailyAtTime() throws Exception {
        Assert.assertEquals("Um 11:30",
                CronExpressionDescriptor.getDescription("30 11 * * *", GERMAN));
    }

    @Test
    public void testTimeOfDayCertainDaysOfWeek() throws Exception {
        Assert.assertEquals("Um 23:00, Montag bis Freitag",
                CronExpressionDescriptor.getDescription("0 23 ? * MON-FRI", GERMAN));
        Assert.assertEquals("Um 11:30, Montag bis Freitag",
                CronExpressionDescriptor.getDescription("30 11 * * 1-5", GERMAN));
    }

    @Test
    public void testOneMonthOnly() throws Exception {
        Assert.assertEquals("Jede Minute, nur im März",
                CronExpressionDescriptor.getDescription("* * * 3 *", GERMAN));
    }

    @Test
    public void testTwoMonthsOnly() throws Exception {
        Assert.assertEquals("Jede Minute, nur im März und Juni",
                CronExpressionDescriptor.getDescription("* * * 3,6 *", GERMAN));
    }

    @Test
    public void testTwoTimesEachAfternoon() throws Exception {
        Assert.assertEquals("Um 14:30 und 16:30",
                CronExpressionDescriptor.getDescription("30 14,16 * * *", GERMAN));
    }

    @Test
    public void testThreeTimesDaily() throws Exception {
        Assert.assertEquals("Um 6:30, 14:30 und 16:30",
                CronExpressionDescriptor.getDescription("30 6,14,16 * * *", GERMAN));
    }

    @Test
    public void testOnceAWeek() throws Exception {
        Assert.assertEquals("Um 9:46, nur am Sonntag",
                CronExpressionDescriptor.getDescription("46 9 * * 0", GERMAN));
        Assert.assertEquals("Um 9:46, nur am Sonntag",
                CronExpressionDescriptor.getDescription("46 9 * * 7", GERMAN));
        Assert.assertEquals("Um 9:46, nur am Montag",
                CronExpressionDescriptor.getDescription("46 9 * * 1", GERMAN));
        Assert.assertEquals("Um 9:46, nur am Samstag",
                CronExpressionDescriptor.getDescription("46 9 * * 6", GERMAN));
    }

    @Test
    public void testOnceAWeekNonZeroBased() throws Exception {
        Options options = new Options();
        options.setZeroBasedDayOfWeek(false);
        Assert.assertEquals("Um 9:46, nur am Sonntag",
                CronExpressionDescriptor.getDescription("46 9 * * 1", options, GERMAN));
        Assert.assertEquals("Um 9:46, nur am Montag",
                CronExpressionDescriptor.getDescription("46 9 * * 2", options, GERMAN));
        Assert.assertEquals("Um 9:46, nur am Samstag",
                CronExpressionDescriptor.getDescription("46 9 * * 7", options, GERMAN));
    }

    @Test
    public void testTwiceAWeek() throws Exception {
        Assert.assertEquals("Um 9:46, nur am Montag und Dienstag",
                CronExpressionDescriptor.getDescription("46 9 * * 1,2", GERMAN));
        Assert.assertEquals("Um 9:46, nur am Sonntag und Samstag",
                CronExpressionDescriptor.getDescription("46 9 * * 0,6", GERMAN));
        Assert.assertEquals("Um 9:46, nur am Samstag und Sonntag",
                CronExpressionDescriptor.getDescription("46 9 * * 6,7", GERMAN));
    }

    @Test
    public void testTwiceAWeekNonZeroBased() throws Exception {
        Options options = new Options();
        options.setZeroBasedDayOfWeek(false);
        Assert.assertEquals("Um 9:46, nur am Sonntag und Montag",
                CronExpressionDescriptor.getDescription("46 9 * * 1,2", options, GERMAN));
        Assert.assertEquals("Um 9:46, nur am Freitag und Samstag",
                CronExpressionDescriptor.getDescription("46 9 * * 6,7", options, GERMAN));
    }

    @Test
    public void testDayOfMonth() throws Exception {
        Assert.assertEquals("Um 12:23, am 15. des Monats",
                CronExpressionDescriptor.getDescription("23 12 15 * *", GERMAN));
    }

    @Test
    public void testMonthName() throws Exception {
        Assert.assertEquals("Um 12:23, nur im Januar",
                CronExpressionDescriptor.getDescription("23 12 * JAN *", GERMAN));
    }

    @Test
    public void testDayOfMonthWithQuestionMark() throws Exception {
        Assert.assertEquals("Um 12:23, nur im Januar",
                CronExpressionDescriptor.getDescription("23 12 ? JAN *", GERMAN));
    }

    @Test
    public void testMonthNameRange2() throws Exception {
        Assert.assertEquals("Um 12:23, Januar bis Februar",
                CronExpressionDescriptor.getDescription("23 12 * JAN-FEB *", GERMAN));
    }

    @Test
    public void testMonthNameRange3() throws Exception {
        Assert.assertEquals("Um 12:23, Januar bis März",
                CronExpressionDescriptor.getDescription("23 12 * JAN-MAR *", GERMAN));
    }

    @Test
    public void testDayOfWeekName() throws Exception {
        Assert.assertEquals("Um 12:23, nur am Sonntag",
                CronExpressionDescriptor.getDescription("23 12 * * SUN", GERMAN));
    }

    @Test
    public void testDayOfWeekRange() throws Exception {
        Assert.assertEquals("Alle 5 Minuten, um 15:00, Montag bis Freitag",
                CronExpressionDescriptor.getDescription("*/5 15 * * MON-FRI", GERMAN));
        Assert.assertEquals("Alle 5 Minuten, um 15:00, Sonntag bis Samstag",
                CronExpressionDescriptor.getDescription("*/5 15 * * 0-6", GERMAN));
        Assert.assertEquals("Alle 5 Minuten, um 15:00, Samstag bis Sonntag",
                CronExpressionDescriptor.getDescription("*/5 15 * * 6-7", GERMAN));
    }

    @Test
    public void testDayOfWeekOnceInMonth() throws Exception {
        Assert.assertEquals("Jede Minute, am dritten Montag des Monats",
                CronExpressionDescriptor.getDescription("* * * * MON#3", GERMAN));
        Assert.assertEquals("Jede Minute, am dritten Sonntag des Monats",
                CronExpressionDescriptor.getDescription("* * * * 0#3", GERMAN));
    }

    @Test
    public void testLastDayOfTheWeekOfTheMonth() throws Exception {
        Assert.assertEquals("Jede Minute, am letzten Donnerstag des Monats",
                CronExpressionDescriptor.getDescription("* * * * 4L", GERMAN));
        Assert.assertEquals("Jede Minute, am letzten Sonntag des Monats",
                CronExpressionDescriptor.getDescription("* * * * 0L", GERMAN));
    }

    @Test
    public void testLastDayOfTheMonth() throws Exception {
        Assert.assertEquals("Alle 5 Minuten, am letzten Tag des Monats, nur im Januar",
                CronExpressionDescriptor.getDescription("*/5 * L JAN *", GERMAN));
    }

    @Test
    public void testTimeOfDayWithSeconds() throws Exception {
        Assert.assertEquals("Um 14:02:30",
                CronExpressionDescriptor.getDescription("30 02 14 * * *", GERMAN));
    }

    @Test
    public void testSecondInternvals() throws Exception {
        Assert.assertEquals("Sekunden 5 bis 10 nach der vollen Minute",
                CronExpressionDescriptor.getDescription("5-10 * * * * *", GERMAN));
    }

    @Test
    public void testSecondMinutesHoursIntervals() throws Exception {
        Assert.assertEquals(
                "Sekunden 5 bis 10 nach der vollen Minute, Minuten 30 bis 35 nach der vollen Stunde, zwischen 10:00 und 12:00",
                CronExpressionDescriptor.getDescription("5-10 30-35 10-12 * * *", GERMAN));
    }

    @Test
    public void testEvery5MinutesAt30Seconds() throws Exception {
        Assert.assertEquals("Um 30 Sekunden nach der vollen Minute, alle 5 Minuten",
                CronExpressionDescriptor.getDescription("30 */5 * * * *", GERMAN));
    }

    @Test
    public void testMinutesPastTheHourRange() throws Exception {
        Assert.assertEquals(
                "Um 30 Minuten nach der vollen Stunde, zwischen 10:00 und 13:00, nur am Mittwoch und Freitag",
                CronExpressionDescriptor.getDescription("0 30 10-13 ? * WED,FRI", GERMAN));
    }

    @Test
    public void testSecondsPastTheMinuteInterval() throws Exception {
        Assert.assertEquals("Um 10 Sekunden nach der vollen Minute, alle 5 Minuten",
                CronExpressionDescriptor.getDescription("10 0/5 * * * ?", GERMAN));
    }

    @Test
    public void testBetweenWithInterval() throws Exception {
        Assert.assertEquals(
                "Alle 3 Minuten, Minuten 02 bis 59 nach der vollen Stunde, um 1:00, 9:00, und 22:00, zwischen Tag 11 und 26 des Monats, Januar bis Juni",
                CronExpressionDescriptor.getDescription("2-59/3 1,9,22 11-26 1-6 ?", GERMAN));
    }

    @Test
    public void testRecurringFirstOfMonth() throws Exception {
        Assert.assertEquals("Um 6:00",
                CronExpressionDescriptor.getDescription("0 0 6 1/1 * ?", GERMAN));
    }

    @Test
    public void testMinutesPastTheHour() throws Exception {
        Assert.assertEquals("Um 05 Minuten nach der vollen Stunde",
                CronExpressionDescriptor.getDescription("0 5 0/1 * * ?", GERMAN));
    }

    /**
     * @since https://github.com/RedHogs/cron-parser/issues/2
     */
    @Test
    public void testEveryPastTheHour() throws Exception {
        Assert.assertEquals(
                "Um 00, 05, 10, 15, 20, 25, 30, 35, 40, 45, 50, und 55 Minuten nach der vollen Stunde",
                CronExpressionDescriptor.getDescription(
                        "0 0,5,10,15,20,25,30,35,40,45,50,55 * ? * *", GERMAN));
    }

    /**
     * @since https://github.com/RedHogs/cron-parser/issues/10
     */
    @Test
    public void testEveryXMinutePastTheHourWithInterval() throws Exception {
        Assert.assertEquals(
                "Alle 2 Minuten, Minuten 00 bis 30 nach der vollen Stunde, um 17:00, Montag bis Freitag",
                CronExpressionDescriptor.getDescription("0 0-30/2 17 ? * MON-FRI", GERMAN));
    }

    /**
     * @since https://github.com/RedHogs/cron-parser/issues/13
     */
    @Test
    public void testOneYearOnlyWithSeconds() throws Exception {
        Assert.assertEquals("Jede Sekunde, nur 2013",
                CronExpressionDescriptor.getDescription("* * * * * * 2013", GERMAN));
    }

    @Test
    public void testOneYearOnlyWithoutSeconds() throws Exception {
        Assert.assertEquals("Jede Minute, nur 2013",
                CronExpressionDescriptor.getDescription("* * * * * 2013", GERMAN));
    }

    @Test
    public void testTwoYearsOnly() throws Exception {
        Assert.assertEquals("Jede Minute, nur 2013 und 2014",
                CronExpressionDescriptor.getDescription("* * * * * 2013,2014", GERMAN));
    }

    @Test
    public void testYearRange2() throws Exception {
        Assert.assertEquals("Um 12:23, Januar bis Februar, 2013 bis 2014",
                CronExpressionDescriptor.getDescription("23 12 * JAN-FEB * 2013-2014", GERMAN));
    }

    @Test
    public void testYearRange3() throws Exception {
        Assert.assertEquals("Um 12:23, Januar bis März, 2013 bis 2015",
                CronExpressionDescriptor.getDescription("23 12 * JAN-MAR * 2013-2015", GERMAN));
    }

    @Test
    public void testIssue26() throws Exception {
        Assert.assertEquals("Um 05 und 10 Minuten nach der vollen Stunde",
                CronExpressionDescriptor.getDescription("5,10 0 * * *", GERMAN));
        Assert.assertEquals("Um 05 und 10 Minuten nach der vollen Stunde, am 2. des Monats",
                CronExpressionDescriptor.getDescription("5,10 0 2 * *", GERMAN));
        Assert.assertEquals("Alle 10 Minuten, am 2. des Monats",
                CronExpressionDescriptor.getDescription("5/10 0 2 * *", GERMAN));

        Assert.assertEquals("Um 5 und 6 Sekunden nach der vollen Minute",
                CronExpressionDescriptor.getDescription("5,6 0 0 * * *", GERMAN));
        Assert.assertEquals("Um 5 und 6 Sekunden nach der vollen Minute, um 1:00",
                CronExpressionDescriptor.getDescription("5,6 0 1 * * *", GERMAN));
        Assert.assertEquals("Um 5 und 6 Sekunden nach der vollen Minute, am 2. des Monats",
                CronExpressionDescriptor.getDescription("5,6 0 0 2 * *", GERMAN));
    }

}
