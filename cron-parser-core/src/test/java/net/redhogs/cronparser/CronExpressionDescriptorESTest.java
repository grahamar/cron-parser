package net.redhogs.cronparser;

import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

public class CronExpressionDescriptorESTest {

    private static final Locale SPANISH = new Locale("es");

    @Test
    public void testEverySecond() throws Exception {
        Assert.assertEquals("Cada segundo", CronExpressionDescriptor.getDescription("* * * * * *", SPANISH));
    }

    @Test
    public void testEvery45Seconds() throws Exception {
        Assert.assertEquals("Cada 45 segundos", CronExpressionDescriptor.getDescription("*/45 * * * * *", SPANISH));
    }

    @Test
    public void testMinuteSpan() throws Exception {
        Assert.assertEquals("Cada minuto entre 11:00 AM y 11:10 AM", CronExpressionDescriptor.getDescription("0-10 11 * * *", SPANISH));
    }

    @Test
    public void testEveryMinute() throws Exception {
        Assert.assertEquals("Cada minuto", CronExpressionDescriptor.getDescription("* * * * *", SPANISH));
        Assert.assertEquals("Cada minuto", CronExpressionDescriptor.getDescription("*/1 * * * *", SPANISH));
        Assert.assertEquals("Cada minuto", CronExpressionDescriptor.getDescription("0 0/1 * * * ?", SPANISH));
    }

    @Test
    public void testEveryXMinutes() throws Exception {
        Assert.assertEquals("Cada 5 minutos", CronExpressionDescriptor.getDescription("*/5 * * * *", SPANISH));
        Assert.assertEquals("Cada 5 minutos", CronExpressionDescriptor.getDescription("0 */5 * * * *", SPANISH));
        Assert.assertEquals("Cada 10 minutos", CronExpressionDescriptor.getDescription("0 0/10 * * * ?", SPANISH));
    }

    @Test
    public void testEveryHour() throws Exception {
        Assert.assertEquals("Cada hora", CronExpressionDescriptor.getDescription("0 0 * * * ?", SPANISH));
        Assert.assertEquals("Cada hora", CronExpressionDescriptor.getDescription("0 0 0/1 * * ?", SPANISH));
    }

    @Test
    public void testDailyAtTime() throws Exception {
        Assert.assertEquals("En 11:30 AM", CronExpressionDescriptor.getDescription("30 11 * * *", SPANISH));
    }

    @Test
    public void testTimeOfDayCertainDaysOfWeek() throws Exception {
        Assert.assertEquals("En 11:00 PM, lunes hasta viernes", CronExpressionDescriptor.getDescription("0 23 ? * MON-FRI", SPANISH));
        Assert.assertEquals("En 11:30 AM, lunes hasta viernes", CronExpressionDescriptor.getDescription("30 11 * * 1-5", SPANISH));
    }

    @Test
    public void testOneMonthOnly() throws Exception {
        Assert.assertEquals("Cada minuto, sólo en marzo", CronExpressionDescriptor.getDescription("* * * 3 *", SPANISH));
    }

    @Test
    public void testTwoMonthsOnly() throws Exception {
        Assert.assertEquals("Cada minuto, sólo en marzo y junio", CronExpressionDescriptor.getDescription("* * * 3,6 *", SPANISH));
    }

    @Test
    public void testTwoTimesEachAfternoon() throws Exception {
        Assert.assertEquals("En 2:30 PM y 4:30 PM", CronExpressionDescriptor.getDescription("30 14,16 * * *", SPANISH));
    }

    @Test
    public void testThreeTimesDaily() throws Exception {
        Assert.assertEquals("En 6:30 AM, 2:30 PM y 4:30 PM", CronExpressionDescriptor.getDescription("30 6,14,16 * * *", SPANISH));
    }

    @Test
    public void testOnceAWeek() throws Exception {
        Assert.assertEquals("En 9:46 AM, sólo en domingo", CronExpressionDescriptor.getDescription("46 9 * * 0", SPANISH));
        Assert.assertEquals("En 9:46 AM, sólo en domingo", CronExpressionDescriptor.getDescription("46 9 * * 7", SPANISH));
        Assert.assertEquals("En 9:46 AM, sólo en lunes", CronExpressionDescriptor.getDescription("46 9 * * 1", SPANISH));
        Assert.assertEquals("En 9:46 AM, sólo en sábado", CronExpressionDescriptor.getDescription("46 9 * * 6", SPANISH));
    }

    @Test
    public void testOnceAWeekNonZeroBased() throws Exception {
        Options options = new Options();
        options.setZeroBasedDayOfWeek(false);
        Assert.assertEquals("En 9:46 AM, sólo en domingo", CronExpressionDescriptor.getDescription("46 9 * * 1", options, SPANISH));
        Assert.assertEquals("En 9:46 AM, sólo en lunes", CronExpressionDescriptor.getDescription("46 9 * * 2", options, SPANISH));
        Assert.assertEquals("En 9:46 AM, sólo en sábado", CronExpressionDescriptor.getDescription("46 9 * * 7", options, SPANISH));
    }

    @Test
    public void testTwiceAWeek() throws Exception {
        Assert.assertEquals("En 9:46 AM, sólo en lunes y martes", CronExpressionDescriptor.getDescription("46 9 * * 1,2", SPANISH));
        Assert.assertEquals("En 9:46 AM, sólo en domingo y sábado", CronExpressionDescriptor.getDescription("46 9 * * 0,6", SPANISH));
        Assert.assertEquals("En 9:46 AM, sólo en sábado y domingo", CronExpressionDescriptor.getDescription("46 9 * * 6,7", SPANISH));
    }

    @Test
    public void testTwiceAWeekNonZeroBased() throws Exception {
        Options options = new Options();
        options.setZeroBasedDayOfWeek(false);
        Assert.assertEquals("En 9:46 AM, sólo en domingo y lunes", CronExpressionDescriptor.getDescription("46 9 * * 1,2", options, SPANISH));
        Assert.assertEquals("En 9:46 AM, sólo en viernes y sábado", CronExpressionDescriptor.getDescription("46 9 * * 6,7", options, SPANISH));
    }

    @Test
    public void testDayOfMonth() throws Exception {
        Assert.assertEquals("En 12:23 PM, en el 15 día del mes", CronExpressionDescriptor.getDescription("23 12 15 * *", SPANISH));
    }

    @Test
    public void testMonthName() throws Exception {
        Assert.assertEquals("En 12:23 PM, sólo en enero", CronExpressionDescriptor.getDescription("23 12 * JAN *", SPANISH));
    }

    @Test
    public void testDayOfMonthWithQuestionMark() throws Exception {
        Assert.assertEquals("En 12:23 PM, sólo en enero", CronExpressionDescriptor.getDescription("23 12 ? JAN *", SPANISH));
    }

    @Test
    public void testMonthNameRange2() throws Exception {
        Assert.assertEquals("En 12:23 PM, enero hasta febrero", CronExpressionDescriptor.getDescription("23 12 * JAN-FEB *", SPANISH));
    }

    @Test
    public void testMonthNameRange3() throws Exception {
        Assert.assertEquals("En 12:23 PM, enero hasta marzo", CronExpressionDescriptor.getDescription("23 12 * JAN-MAR *", SPANISH));
    }

    @Test
    public void testMonthNameRanges() throws Exception {
        Assert.assertEquals("En 3:00 AM, sólo en enero hasta marzo y mayo hasta junio", CronExpressionDescriptor.getDescription("0 0 3 * 1-3,5-6 *", SPANISH));
    }

    @Test
    public void testDayOfWeekName() throws Exception {
        Assert.assertEquals("En 12:23 PM, sólo en domingo", CronExpressionDescriptor.getDescription("23 12 * * SUN", SPANISH));
    }

    @Test
    public void testDayOfWeekRange() throws Exception {
        Assert.assertEquals("Cada 5 minutos, 3:00 PM, lunes hasta viernes", CronExpressionDescriptor.getDescription("*/5 15 * * MON-FRI", SPANISH));
        Assert.assertEquals("Cada 5 minutos, 3:00 PM, domingo hasta sábado", CronExpressionDescriptor.getDescription("*/5 15 * * 0-6", SPANISH));
        Assert.assertEquals("Cada 5 minutos, 3:00 PM, sábado hasta domingo", CronExpressionDescriptor.getDescription("*/5 15 * * 6-7", SPANISH));
    }

    @Test
    public void testDayOfWeekRanges() throws Exception {
        Assert.assertEquals("En 3:00 AM, sólo en domingo, martes hasta jueves y sábado", CronExpressionDescriptor.getDescription("0 0 3 * * 0,2-4,6", SPANISH));
    }

    @Test
    public void testDayOfWeekOnceInMonth() throws Exception {
        Assert.assertEquals("Cada minuto, en el terzero lunes del mes", CronExpressionDescriptor.getDescription("* * * * MON#3", SPANISH));
        Assert.assertEquals("Cada minuto, en el terzero domingo del mes", CronExpressionDescriptor.getDescription("* * * * 0#3", SPANISH));
    }

    @Test
    public void testLastDayOfTheWeekOfTheMonth() throws Exception {
        Assert.assertEquals("Cada minuto, en el último jueves del mes", CronExpressionDescriptor.getDescription("* * * * 4L", SPANISH));
        Assert.assertEquals("Cada minuto, en el último domingo del mes", CronExpressionDescriptor.getDescription("* * * * 0L", SPANISH));
    }

    @Test
    public void testLastDayOfTheMonth() throws Exception {
        Assert.assertEquals("Cada 5 minutos, en el último día del mes, sólo en enero", CronExpressionDescriptor.getDescription("*/5 * L JAN *", SPANISH));
    }

    @Test
    public void testTimeOfDayWithSeconds() throws Exception {
        Assert.assertEquals("En 2:02:30 PM", CronExpressionDescriptor.getDescription("30 02 14 * * *", SPANISH));
    }

    @Test
    public void testSecondInternvals() throws Exception {
        //TODO Flip 5 & seconds
        Assert.assertEquals("5 segundos 10 después el minuto", CronExpressionDescriptor.getDescription("5-10 * * * * *", SPANISH));
    }

    @Test
    public void testSecondMinutesHoursIntervals() throws Exception {
        //TODO Flip 5 & seconds
        Assert.assertEquals("5 segundos 10 después el minuto, desde 30 hasta el 35 minuto después de la hora, entre 10:00 AM y 12:00 PM",
                CronExpressionDescriptor.getDescription("5-10 30-35 10-12 * * *", SPANISH));
    }

    @Test
    public void testEvery5MinutesAt30Seconds() throws Exception {
        Assert.assertEquals("30 segundos después el minuto, cada 5 minutos", CronExpressionDescriptor.getDescription("30 */5 * * * *", SPANISH));
    }

    @Test
    public void testMinutesPastTheHourRange() throws Exception {
        Assert.assertEquals("30 minutos pasada la hora, entre 10:00 AM y 1:00 PM, sólo en miércoles y viernes",
                CronExpressionDescriptor.getDescription("0 30 10-13 ? * WED,FRI", SPANISH));
    }

    @Test
    public void testSecondsPastTheMinuteInterval() throws Exception {
        Assert.assertEquals("10 segundos después el minuto, cada 5 minutos", CronExpressionDescriptor.getDescription("10 0/5 * * * ?", SPANISH));
    }

    @Test
    public void testBetweenWithInterval() throws Exception {
        Assert.assertEquals("Cada 3 minutos, desde 02 hasta el 59 minuto después de la hora, 1:00 AM, 9:00 AM y 10:00 PM, entre el 11 y el 26 del mes, enero hasta junio",
                CronExpressionDescriptor.getDescription("2-59/3 1,9,22 11-26 1-6 ?", SPANISH));
    }

    @Test
    public void testRecurringFirstOfMonth() throws Exception {
        Assert.assertEquals("En 6:00 AM", CronExpressionDescriptor.getDescription("0 0 6 1/1 * ?", SPANISH));
    }

    @Test
    public void testMinutesPastTheHour() throws Exception {
        Assert.assertEquals("05 minutos pasada la hora", CronExpressionDescriptor.getDescription("0 5 0/1 * * ?", SPANISH));
    }

    @Test
    public void testEveryXMinutePastTheHourWithInterval() throws Exception {
        Assert.assertEquals("Cada 2 minutos, desde 00 hasta el 30 minuto después de la hora, 5:00 PM, lunes hasta viernes", CronExpressionDescriptor.getDescription("0 0-30/2 17 ? * MON-FRI", SPANISH));
    }

    @Test
    public void testOneYearOnlyWithSeconds() throws Exception {
        Assert.assertEquals("Cada segundo, sólo en 2013", CronExpressionDescriptor.getDescription("* * * * * * 2013", SPANISH));
    }

    @Test
    public void testOneYearOnlyWithoutSeconds() throws Exception {
        Assert.assertEquals("Cada minuto, sólo en 2013", CronExpressionDescriptor.getDescription("* * * * * 2013", SPANISH));
    }

    @Test
    public void testTwoYearsOnly() throws Exception {
        Assert.assertEquals("Cada minuto, sólo en 2013 y 2014", CronExpressionDescriptor.getDescription("* * * * * 2013,2014", SPANISH));
    }

    @Test
    public void testYearRange2() throws Exception {
        Assert.assertEquals("En 12:23 PM, enero hasta febrero, 2013 hasta 2014", CronExpressionDescriptor.getDescription("23 12 * JAN-FEB * 2013-2014", SPANISH));
    }

    @Test
    public void testYearRange3() throws Exception {
        Assert.assertEquals("En 12:23 PM, enero hasta marzo, 2013 hasta 2015", CronExpressionDescriptor.getDescription("23 12 * JAN-MAR * 2013-2015", SPANISH));
    }

    @Test
    public void testIssue26() throws Exception {
        Assert.assertEquals("05 y 10 minutos pasada la hora", CronExpressionDescriptor.getDescription("5,10 0 * * *", SPANISH));
        Assert.assertEquals("05 y 10 minutos pasada la hora, en el 2 día del mes", CronExpressionDescriptor.getDescription("5,10 0 2 * *", SPANISH));
        Assert.assertEquals("Cada 10 minutos, en el 2 día del mes", CronExpressionDescriptor.getDescription("5/10 0 2 * *", SPANISH));

        Assert.assertEquals("5 y 6 segundos después el minuto", CronExpressionDescriptor.getDescription("5,6 0 0 * * *", SPANISH));
        Assert.assertEquals("5 y 6 segundos después el minuto, 1:00 AM", CronExpressionDescriptor.getDescription("5,6 0 1 * * *", SPANISH));
        Assert.assertEquals("5 y 6 segundos después el minuto, en el 2 día del mes", CronExpressionDescriptor.getDescription("5,6 0 0 2 * *", SPANISH));
    }

}
