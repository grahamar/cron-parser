package net.redhogs.cronparser;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

public class CronExpressionDescriptorROTest {

	private static final Locale ROMANIAN = new Locale("ro");
	
    @Test
    public void testEverySecond() throws Exception {
        Assert.assertEquals("În fiecare secundă", CronExpressionDescriptor.getDescription("* * * * * *", ROMANIAN));
    }

    @Test
    public void testEvery45Seconds() throws Exception {
        Assert.assertEquals("La fiecare 45 secunde", CronExpressionDescriptor.getDescription("*/45 * * * * *", ROMANIAN));
    }

    @Test
    public void testMinuteSpan() throws Exception {
        Assert.assertEquals("În fiecare minut între 11:00 AM și 11:10 AM", CronExpressionDescriptor.getDescription("0-10 11 * * *", ROMANIAN));
    }

    @Test
    public void testEveryMinute() throws Exception {
        Assert.assertEquals("În fiecare minut", CronExpressionDescriptor.getDescription("* * * * *", ROMANIAN));
        Assert.assertEquals("În fiecare minut", CronExpressionDescriptor.getDescription("*/1 * * * *", ROMANIAN));
        Assert.assertEquals("În fiecare minut", CronExpressionDescriptor.getDescription("0 0/1 * * * ?", ROMANIAN));
    }

    @Test
    public void testEveryXMinutes() throws Exception {
        Assert.assertEquals("La fiecare 5 minute", CronExpressionDescriptor.getDescription("*/5 * * * *", ROMANIAN));
        Assert.assertEquals("La fiecare 5 minute", CronExpressionDescriptor.getDescription("0 */5 * * * *", ROMANIAN));
        Assert.assertEquals("La fiecare 10 minute", CronExpressionDescriptor.getDescription("0 0/10 * * * ?", ROMANIAN));
    }

    @Test
    public void testEveryHour() throws Exception {
        Assert.assertEquals("În fiecare oră", CronExpressionDescriptor.getDescription("0 0 * * * ?", ROMANIAN));
        Assert.assertEquals("În fiecare oră", CronExpressionDescriptor.getDescription("0 0 0/1 * * ?", ROMANIAN));
    }

    @Test
    public void testDailyAtTime() throws Exception {
        Assert.assertEquals("La 11:30 AM", CronExpressionDescriptor.getDescription("30 11 * * *", ROMANIAN));
    }

    @Test
    public void testTimeOfDayCertainDaysOfWeek() throws Exception {
        Assert.assertEquals("La 11:00 PM, de luni până vineri", CronExpressionDescriptor.getDescription("0 23 ? * MON-FRI", ROMANIAN));
        Assert.assertEquals("La 11:30 AM, de luni până vineri", CronExpressionDescriptor.getDescription("30 11 * * 1-5", ROMANIAN));
    }

    @Test
    public void testOneMonthOnly() throws Exception {
        Assert.assertEquals("În fiecare minut, numai în martie", CronExpressionDescriptor.getDescription("* * * 3 *", ROMANIAN));
    }

    @Test
    public void testTwoMonthsOnly() throws Exception {
        Assert.assertEquals("În fiecare minut, numai în martie și iunie", CronExpressionDescriptor.getDescription("* * * 3,6 *", ROMANIAN));
    }

    @Test
    public void testTwoTimesEachAfternoon() throws Exception {
        Assert.assertEquals("La 2:30 PM și 4:30 PM", CronExpressionDescriptor.getDescription("30 14,16 * * *", ROMANIAN));
    }

    @Test
    public void testThreeTimesDaily() throws Exception {
        Assert.assertEquals("La 6:30 AM, 2:30 PM și 4:30 PM", CronExpressionDescriptor.getDescription("30 6,14,16 * * *", ROMANIAN));
    }

    @Test
    public void testOnceAWeek() throws Exception {
        Assert.assertEquals("La 9:46 AM, numai luni", CronExpressionDescriptor.getDescription("46 9 * * 1", ROMANIAN));
    }

    @Test
    public void testOnceAWeekNonZeroBased() throws Exception {
        Options options = new Options();
        options.setZeroBasedDayOfWeek(false);
        Assert.assertEquals("La 9:46 AM, numai duminică", CronExpressionDescriptor.getDescription("46 9 * * 1", options, ROMANIAN));
        Assert.assertEquals("La 9:46 AM, numai luni", CronExpressionDescriptor.getDescription("46 9 * * 2", options, ROMANIAN));
        Assert.assertEquals("La 9:46 AM, numai sâmbătă", CronExpressionDescriptor.getDescription("46 9 * * 7", options, ROMANIAN));
    }

    @Test
    public void testDayOfMonth() throws Exception {
        Assert.assertEquals("La 12:23 PM, în a 15-a zi a lunii", CronExpressionDescriptor.getDescription("23 12 15 * *", ROMANIAN));
    }

    @Test
    public void testMonthName() throws Exception {
        Assert.assertEquals("La 12:23 PM, numai în ianuarie", CronExpressionDescriptor.getDescription("23 12 * JAN *", ROMANIAN));
    }

    @Test
    public void testDayOfMonthWithQuestionMark() throws Exception {
        Assert.assertEquals("La 12:23 PM, numai în ianuarie", CronExpressionDescriptor.getDescription("23 12 ? JAN *", ROMANIAN));
    }

    @Test
    public void testMonthNameRange2() throws Exception {
        Assert.assertEquals("La 12:23 PM, din ianuarie până în februarie", CronExpressionDescriptor.getDescription("23 12 * JAN-FEB *", ROMANIAN));
    }

    @Test
    public void testMonthNameRange3() throws Exception {
        Assert.assertEquals("La 12:23 PM, din ianuarie până în martie", CronExpressionDescriptor.getDescription("23 12 * JAN-MAR *", ROMANIAN));
    }

    @Test
    public void testDayOfWeekName() throws Exception {
        Assert.assertEquals("La 12:23 PM, numai duminică", CronExpressionDescriptor.getDescription("23 12 * * SUN", ROMANIAN));
    }

    @Test
    public void testDayOfWeekRange() throws Exception {
        Assert.assertEquals("La fiecare 5 minute, la 3:00 PM, de luni până vineri", CronExpressionDescriptor.getDescription("*/5 15 * * MON-FRI", ROMANIAN));
    }

    @Test
    public void testDayOfWeekOnceInMonth() throws Exception {
        Assert.assertEquals("În fiecare minut, în a treia luni a lunii", CronExpressionDescriptor.getDescription("* * * * MON#3", ROMANIAN));
    }

    @Test
    public void testLastDayOfTheWeekOfTheMonth() throws Exception {
        Assert.assertEquals("În fiecare minut, în ultima joi din lună", CronExpressionDescriptor.getDescription("* * * * 4L", ROMANIAN));
    }

    @Test
    public void testLastDayOfTheMonth() throws Exception {
        Assert.assertEquals("La fiecare 5 minute, în ultima zi a lunii, numai în ianuarie", CronExpressionDescriptor.getDescription("*/5 * L JAN *", ROMANIAN));
    }

    @Test
    public void testTimeOfDayWithSeconds() throws Exception {
        Assert.assertEquals("La 2:02:30 PM", CronExpressionDescriptor.getDescription("30 02 14 * * *", ROMANIAN));
    }

    @Test
    public void testSecondInternvals() throws Exception {
        Assert.assertEquals("Între secundele 5 și 10", CronExpressionDescriptor.getDescription("5-10 * * * * *", ROMANIAN));
    }

    @Test
    public void testSecondMinutesHoursIntervals() throws Exception {
        Assert.assertEquals("Între secundele 5 și 10, între minutele 30 și 35, între 10:00 AM și 12:00 PM",
                CronExpressionDescriptor.getDescription("5-10 30-35 10-12 * * *", ROMANIAN));
    }

    @Test
    public void testEvery5MinutesAt30Seconds() throws Exception {
        Assert.assertEquals("La secunda 30, la fiecare 5 minute", CronExpressionDescriptor.getDescription("30 */5 * * * *", ROMANIAN));
    }

    @Test
    public void testMinutesPastTheHourRange() throws Exception {
        Assert.assertEquals("La 30 minute în fiecare oră, între 10:00 AM și 1:00 PM, numai miercuri și vineri",
                CronExpressionDescriptor.getDescription("0 30 10-13 ? * WED,FRI", ROMANIAN));
    }

    @Test
    public void testSecondsPastTheMinuteInterval() throws Exception {
        Assert.assertEquals("La secunda 10, la fiecare 5 minute", CronExpressionDescriptor.getDescription("10 0/5 * * * ?", ROMANIAN));
    }

    @Test
    public void testBetweenWithInterval() throws Exception {
        Assert.assertEquals("La fiecare 3 minute, între minutele 02 și 59, la 1:00 AM, 9:00 AM, și 10:00 PM, între a 11-a și a 26-a zi a lunii, din ianuarie până în iunie",
                CronExpressionDescriptor.getDescription("2-59/3 1,9,22 11-26 1-6 ?", ROMANIAN));
    }

    @Test
    public void testRecurringFirstOfMonth() throws Exception {
        Assert.assertEquals("La 6:00 AM", CronExpressionDescriptor.getDescription("0 0 6 1/1 * ?", ROMANIAN));
    }

    @Test
    public void testMinutesPastTheHour() throws Exception {
        Assert.assertEquals("La 05 minute în fiecare oră", CronExpressionDescriptor.getDescription("0 5 0/1 * * ?", ROMANIAN));
    }

    /**
     * @since https://github.com/RedHogs/cron-parser/issues/2
     */
    @Test
    public void testEveryPastTheHour() throws Exception {
        Assert.assertEquals("La 00, 05, 10, 15, 20, 25, 30, 35, 40, 45, 50, și 55 minute în fiecare oră", CronExpressionDescriptor.getDescription("0 0,5,10,15,20,25,30,35,40,45,50,55 * ? * *", ROMANIAN));
    }

    /**
     * @since https://github.com/RedHogs/cron-parser/issues/10
     */
    @Test
    public void testEveryXMinutePastTheHourWithInterval() throws Exception {
        Assert.assertEquals("La fiecare 2 minute, între minutele 00 și 30, la 5:00 PM, de luni până vineri", CronExpressionDescriptor.getDescription("0 0-30/2 17 ? * MON-FRI", ROMANIAN));
    }

    /**
     * @since https://github.com/RedHogs/cron-parser/issues/13
     */
    @Test
    public void testOneYearOnlyWithSeconds() throws Exception {
        Assert.assertEquals("În fiecare secundă, numai în 2013", CronExpressionDescriptor.getDescription("* * * * * * 2013", ROMANIAN));
    }

    @Test
    public void testOneYearOnlyWithoutSeconds() throws Exception {
        Assert.assertEquals("În fiecare minut, numai în 2013", CronExpressionDescriptor.getDescription("* * * * * 2013", ROMANIAN));
    }

    @Test
    public void testTwoYearsOnly() throws Exception {
        Assert.assertEquals("În fiecare minut, numai în 2013 și 2014", CronExpressionDescriptor.getDescription("* * * * * 2013,2014", ROMANIAN));
    }

    @Test
    public void testYearRange2() throws Exception {
        Assert.assertEquals("La 12:23 PM, din ianuarie până în februarie, din 2013 până în 2014", CronExpressionDescriptor.getDescription("23 12 * JAN-FEB * 2013-2014", ROMANIAN));
    }

    @Test
    public void testYearRange3() throws Exception {
        Assert.assertEquals("La 12:23 PM, din ianuarie până în martie, din 2013 până în 2015", CronExpressionDescriptor.getDescription("23 12 * JAN-MAR * 2013-2015", ROMANIAN));
    }

}
