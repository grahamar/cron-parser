package net.redhogs.cronparser;

import net.redhogs.cronparser.builder.CronExpressionDescriptor;
import net.redhogs.cronparser.builder.DescriptorParamsBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

public class CronExpressionDescriptorESTest {
    private static final Locale SPANISH = new Locale("es");

    private CronExpressionDescriptor descriptor;

    @Before
    public void setUp(){
        descriptor =
                DescriptorParamsBuilder.createDescriptor()
                    .withLocale(SPANISH)
                    .forCronType(CronType.UNIX)
                    .build();
    }

    @Test
    public void testEverySecond() throws Exception {
        Assert.assertEquals("Cada segundo",
                DescriptorParamsBuilder.createDescriptor()
                        .withLocale(SPANISH)
                        .forCronType(CronType.QUARTZ)
                        .build()
                        .getDescription("* * * * * *")
        );
    }

    @Test
    public void testEvery45Seconds() throws Exception {
        Assert.assertEquals("Cada 45 segundos",
                DescriptorParamsBuilder.createDescriptor()
                        .withLocale(SPANISH)
                        .forCronType(CronType.QUARTZ)
                        .build()
                        .getDescription("*/45 * * * * *")
        );
    }

    @Test
    public void testMinuteSpan() throws Exception {
        Assert.assertEquals("Cada minuto entre 11:00 AM y 11:10 AM",
                descriptor.getDescription("0-10 11 * * *")
        );
    }

    @Test
    public void testEveryMinute() throws Exception {
        Assert.assertEquals("Cada minuto", descriptor.getDescription("* * * * *"));
        Assert.assertEquals("Cada minuto", descriptor.getDescription("*/1 * * * *"));
        Assert.assertEquals("Cada minuto", descriptor.getDescription("0/1 * * * ?"));
    }

    @Test
    public void testEveryXMinutes() throws Exception {
        Assert.assertEquals("Cada 5 minutos", descriptor.getDescription("*/5 * * * *"));
        Assert.assertEquals("Cada 10 minutos", descriptor.getDescription("0/10 * * * ?"));
    }

    @Test
    public void testEveryHour() throws Exception {
        Assert.assertEquals("Cada hora", descriptor.getDescription("0 * * * ?"));
        Assert.assertEquals("Cada hora", descriptor.getDescription("0 0/1 * * ?"));
    }

    @Test
    public void testDailyAtTime() throws Exception {
        Assert.assertEquals("En 11:30:00 AM", descriptor.getDescription("30 11 * * *"));
    }

    @Test
    public void testTimeOfDayCertainDaysOfWeek() throws Exception {
        Assert.assertEquals("En 11:00:00 PM, lunes hasta viernes", descriptor.getDescription("0 23 ? * MON-FRI"));
        Assert.assertEquals("En 11:30:00 AM, lunes hasta viernes", descriptor.getDescription("30 11 * * 1-5"));
    }

    @Test
    public void testOneMonthOnly() throws Exception {
        Assert.assertEquals("Cada minuto, solo en marzo", descriptor.getDescription("* * * 3 *"));
    }

    @Test
    public void testTwoMonthsOnly() throws Exception {
        Assert.assertEquals("Cada minuto, solo en marzo y junio", descriptor.getDescription("* * * 3,6 *"));
    }

    @Test
    public void testTwoTimesEachAfternoon() throws Exception {
        Assert.assertEquals("En 2:30 PM y 4:30 PM", descriptor.getDescription("30 14,16 * * *"));
    }

    @Test
    public void testThreeTimesDaily() throws Exception {
        Assert.assertEquals("En 6:30 AM, 2:30 PM y 4:30 PM", descriptor.getDescription("30 6,14,16 * * *"));
    }

    @Test
    public void testOnceAWeek() throws Exception {
        Assert.assertEquals("En 9:46:00 AM, solo en lunes", descriptor.getDescription("46 9 * * 1"));
    }

    @Test
    public void testOnceAWeekNonZeroBased() throws Exception {
        Options options = new Options();
        options.setZeroBasedDayOfWeek(false);
        descriptor =
                DescriptorParamsBuilder.createDescriptor()
                        .withLocale(SPANISH)
                        .withOptions(options)
                        .forCronType(CronType.UNIX)
                        .build();
        Assert.assertEquals("En 9:46:00 AM, solo en domingo", descriptor.getDescription("46 9 * * 1"));
        Assert.assertEquals("En 9:46:00 AM, solo en lunes", descriptor.getDescription("46 9 * * 2"));
        Assert.assertEquals("En 9:46:00 AM, solo en sábado", descriptor.getDescription("46 9 * * 7"));
    }

    @Test
    public void testDayOfMonth() throws Exception {
        Assert.assertEquals("En 12:23:00 PM, en el 15 dia del mes", descriptor.getDescription("23 12 15 * *"));
    }

    @Test
    public void testMonthName() throws Exception {
        Assert.assertEquals("En 12:23:00 PM, solo en enero", descriptor.getDescription("23 12 * JAN *"));
    }

    @Test
    public void testDayOfMonthWithQuestionMark() throws Exception {
        Assert.assertEquals("En 12:23:00 PM, solo en enero", descriptor.getDescription("23 12 ? JAN *"));
    }

    @Test
    public void testMonthNameRange2() throws Exception {
        Assert.assertEquals("En 12:23:00 PM, enero hasta febrero", descriptor.getDescription("23 12 * JAN-FEB *"));
    }

    @Test
    public void testMonthNameRange3() throws Exception {
        Assert.assertEquals("En 12:23:00 PM, enero hasta marzo", descriptor.getDescription("23 12 * JAN-MAR *"));
    }

    @Test
    public void testDayOfWeekName() throws Exception {
        Assert.assertEquals("En 12:23:00 PM, solo en domingo", descriptor.getDescription("23 12 * * SUN"));
    }

    @Test
    public void testDayOfWeekRange() throws Exception {
        Assert.assertEquals("Cada 5 minutos, 3:00 PM, lunes hasta viernes", descriptor.getDescription("*/5 15 * * MON-FRI"));
    }

    @Test
    public void testDayOfWeekOnceInMonth() throws Exception {
        Assert.assertEquals("Cada minuto, en el tercer lunes del mes", descriptor.getDescription("* * * * MON#3"));
    }

    @Test
    public void testLastDayOfTheWeekOfTheMonth() throws Exception {
        Assert.assertEquals("Cada minuto, en el ultimo jueves del mes", descriptor.getDescription("* * * * 4L"));
    }

    @Test
    public void testLastDayOfTheMonth() throws Exception {
        Assert.assertEquals("Cada 5 minutos, en el ultimo dia del mes, solo en enero", descriptor.getDescription("*/5 * L JAN *"));
    }

    @Test
    public void testTimeOfDayWithSeconds() throws Exception {
        descriptor =
                DescriptorParamsBuilder.createDescriptor()
                        .withLocale(SPANISH)
                        .forCronType(CronType.QUARTZ)
                        .build();
        Assert.assertEquals("En 2:02:30 PM", descriptor.getDescription("30 02 14 * * *"));
    }

    @Test
    public void testSecondInternvals() throws Exception {
        //TODO Flip 5 & seconds
        descriptor =
                DescriptorParamsBuilder.createDescriptor()
                        .withLocale(SPANISH)
                        .forCronType(CronType.QUARTZ)
                        .build();
        Assert.assertEquals("5 segundos 10 despues el minuto", descriptor.getDescription("5-10 * * * * *"));
    }

    @Test
    public void testSecondMinutesHoursIntervals() throws Exception {
        //TODO Flip 5 & seconds
        descriptor =
                DescriptorParamsBuilder.createDescriptor()
                        .withLocale(SPANISH)
                        .forCronType(CronType.QUARTZ)
                        .build();
        Assert.assertEquals("5 segundos 10 despues el minuto, desde 30 hasta el 35 minuto despues de la hora, entre 10:00 AM y 12:00 PM",
                descriptor.getDescription("5-10 30-35 10-12 * * *"));
    }

    @Test
    public void testEvery5MinutesAt30Seconds() throws Exception {
        descriptor =
                DescriptorParamsBuilder.createDescriptor()
                        .withLocale(SPANISH)
                        .forCronType(CronType.QUARTZ)
                        .build();
        Assert.assertEquals("30 segundos despues el minuto, cada 5 minutos", descriptor.getDescription("30 */5 * * * *"));
    }

    @Test
    public void testMinutesPastTheHourRange() throws Exception {
        descriptor =
                    DescriptorParamsBuilder.createDescriptor()
                            .withLocale(SPANISH)
                            .forCronType(CronType.QUARTZ)
                            .build();
        Assert.assertEquals("30 minutos pasada la hora, entre 10:00 AM y 1:00 PM, solo en miércoles y viernes",
                descriptor.getDescription("0 30 10-13 ? * WED,FRI"));
    }

    @Test
    public void testSecondsPastTheMinuteInterval() throws Exception {
        descriptor =
                DescriptorParamsBuilder.createDescriptor()
                        .withLocale(SPANISH)
                        .forCronType(CronType.QUARTZ)
                        .build();
        Assert.assertEquals("10 segundos despues el minuto, cada 5 minutos", descriptor.getDescription("10 0/5 * * * ?"));
    }

    @Test
    public void testBetweenWithInterval() throws Exception {
        Assert.assertEquals("Cada 3 minutos, desde 02 hasta el 59 minuto despues de la hora, 1:00 AM, 9:00 AM, y 10:00 PM, entre el 11 y el 26 del mes, enero hasta junio",
                descriptor.getDescription("2-59/3 1,9,22 11-26 1-6 ?"));
    }

    @Test
    public void testRecurringFirstOfMonth() throws Exception {
        Assert.assertEquals("En 6:00:00 AM", descriptor.getDescription("0 6 1/1 * ?"));
    }

    @Test
    public void testMinutesPastTheHour() throws Exception {
        descriptor =
                DescriptorParamsBuilder.createDescriptor()
                        .withLocale(SPANISH)
                        .forCronType(CronType.QUARTZ)
                        .build();
        Assert.assertEquals("05 minutos pasada la hora", descriptor.getDescription("0 5 0/1 * * ?"));
    }

    @Test
    public void testMinutesPastTheHourM() throws Exception {
        Assert.assertEquals("05 minutos pasada la hora", descriptor.getDescription("5 0/1 * * ?"));
    }

}
