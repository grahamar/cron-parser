package net.redhogs.cronparser;

import net.redhogs.cronparser.builder.CronExpressionDescriptor;
import net.redhogs.cronparser.builder.DescriptorParamsBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

public class CronExpressionDescriptorITTest {

    private static final Locale ITALIAN = Locale.ITALIAN;


    private CronExpressionDescriptor descriptor;

    @Before
    public void setUp(){
        descriptor =
                DescriptorParamsBuilder.createDescriptor()
                        .withLocale(ITALIAN)
                        .forCronType(CronType.UNIX)
                        .build();
    }

    @Test
    public void testEverySecond() throws Exception {
        descriptor =
                DescriptorParamsBuilder.createDescriptor()
                        .withLocale(ITALIAN)
                        .forCronType(CronType.QUARTZ)
                        .build();
        Assert.assertEquals("Ogni secondo", descriptor.getDescription("* * * * * *"));
    }

    @Test
    public void testEvery45Seconds() throws Exception {
        descriptor =
                DescriptorParamsBuilder.createDescriptor()
                        .withLocale(ITALIAN)
                        .forCronType(CronType.QUARTZ)
                        .build();
        Assert.assertEquals("Ogni 45 secondi", descriptor.getDescription("*/45 * * * * *"));
    }

    @Test
    public void testMinuteSpan() throws Exception {
        Assert.assertEquals("Ogni minuto tra 11:00 AM e 11:10 AM", descriptor.getDescription("0-10 11 * * *"));
    }

    @Test
    public void testEveryMinute() throws Exception {
        Assert.assertEquals("Ogni minuto", descriptor.getDescription("* * * * *"));
        Assert.assertEquals("Ogni minuto", descriptor.getDescription("*/1 * * * *"));
        Assert.assertEquals("Ogni minuto", descriptor.getDescription("0/1 * * * ?"));
    }

    @Test
    public void testEveryXMinutes() throws Exception {
        Assert.assertEquals("Ogni 5 minuti", descriptor.getDescription("*/5 * * * *"));
        Assert.assertEquals("Ogni 5 minuti", descriptor.getDescription("*/5 * * * *"));
        Assert.assertEquals("Ogni 10 minuti", descriptor.getDescription("0/10 * * * ?"));
    }

    @Test
    public void testEveryHour() throws Exception {
        Assert.assertEquals("Ogni ora", descriptor.getDescription("0 * * * ?"));
        Assert.assertEquals("Ogni ora", descriptor.getDescription("0 0/1 * * ?"));
    }

    @Test
    public void testDailyAtTime() throws Exception {
        Assert.assertEquals("Alle 11:30:00 AM", descriptor.getDescription("30 11 * * *"));
    }

    @Test
    public void testTimeOfDayCertainDaysOfWeek() throws Exception {
        Assert.assertEquals("Alle 11:00:00 PM, lunedì fino a venerdì", descriptor.getDescription("0 23 ? * MON-FRI"));
        Assert.assertEquals("Alle 11:30:00 AM, lunedì fino a venerdì", descriptor.getDescription("30 11 * * 1-5"));
    }

    @Test
    public void testOneMonthOnly() throws Exception {
        Assert.assertEquals("Ogni minuto, solo in marzo", descriptor.getDescription("* * * 3 *"));
    }

    @Test
    public void testTwoMonthsOnly() throws Exception {
        Assert.assertEquals("Ogni minuto, solo in marzo e giugno", descriptor.getDescription("* * * 3,6 *"));
    }

    @Test
    public void testTwoTimesEachAfternoon() throws Exception {
        Assert.assertEquals("Alle 2:30 PM e 4:30 PM", descriptor.getDescription("30 14,16 * * *"));
    }

    @Test
    public void testThreeTimesDaily() throws Exception {
        Assert.assertEquals("Alle 6:30 AM, 2:30 PM e 4:30 PM", descriptor.getDescription("30 6,14,16 * * *"));
    }

    @Test
    public void testOnceAWeek() throws Exception {
        Assert.assertEquals("Alle 9:46:00 AM, solo di lunedì", descriptor.getDescription("46 9 * * 1"));
    }

    @Test
    public void testOnceAWeekNonZeroBased() throws Exception {
        Options options = new Options();
        options.setZeroBasedDayOfWeek(false);
        Assert.assertEquals("Alle 9:46:00 AM, solo di lunedì", descriptor.getDescription("46 9 * * 1"));
        Assert.assertEquals("Alle 9:46:00 AM, solo di martedì", descriptor.getDescription("46 9 * * 2"));
        Assert.assertEquals("Alle 9:46:00 AM, solo di domenica", descriptor.getDescription("46 9 * * 7"));
    }

    @Test
    public void testDayOfMonth() throws Exception {
        Assert.assertEquals("Alle 12:23:00 PM, nel 15 giorno del mese", descriptor.getDescription("23 12 15 * *"));
    }

    @Test
    public void testMonthName() throws Exception {
        Assert.assertEquals("Alle 12:23:00 PM, solo in gennaio", descriptor.getDescription("23 12 * JAN *"));
    }

    @Test
    public void testDayOfMonthWithQuestionMark() throws Exception {
        Assert.assertEquals("Alle 12:23:00 PM, solo in gennaio", descriptor.getDescription("23 12 ? JAN *"));
    }

    @Test
    public void testMonthNameRange2() throws Exception {
        Assert.assertEquals("Alle 12:23:00 PM, gennaio fino a febbraio", descriptor.getDescription("23 12 * JAN-FEB *"));
    }

    @Test
    public void testMonthNameRange3() throws Exception {
        Assert.assertEquals("Alle 12:23:00 PM, gennaio fino a marzo", descriptor.getDescription("23 12 * JAN-MAR *"));
    }

    @Test
    public void testDayOfWeekName() throws Exception {
        Assert.assertEquals("Alle 12:23:00 PM, solo di domenica", descriptor.getDescription("23 12 * * SUN"));
    }

    @Test
    public void testDayOfWeekRange() throws Exception {
        Assert.assertEquals("Ogni 5 minuti, 3:00 PM, lunedì fino a venerdì", descriptor.getDescription("*/5 15 * * MON-FRI"));
    }

    @Test
    public void testDayOfWeekOnceInMonth() throws Exception {
        Assert.assertEquals("Ogni minuto, nel terzo lunedì del mese", descriptor.getDescription("* * * * MON#3"));
    }

    @Test
    public void testLastDayOfTheWeekOfTheMonth() throws Exception {
        Assert.assertEquals("Ogni minuto, nell ultimo {0} del mese", descriptor.getDescription("* * * * 4L"));
    }

    @Test
    public void testLastDayOfTheMonth() throws Exception {
        Assert.assertEquals("Ogni 5 minuti, nell' ultimo giorno del mese, solo in gennaio", descriptor.getDescription("*/5 * L JAN *"));
    }

    @Test
    public void testTimeOfDayWithSeconds() throws Exception {
        descriptor =
                DescriptorParamsBuilder.createDescriptor()
                        .withLocale(ITALIAN)
                        .forCronType(CronType.QUARTZ)
                        .build();
        Assert.assertEquals("Alle 2:02:30 PM", descriptor.getDescription("30 02 14 * * *"));
    }

    @Test
    public void testSecondInternvals() throws Exception {
        //TODO Flip 5 & seconds
        descriptor =
                DescriptorParamsBuilder.createDescriptor()
                        .withLocale(ITALIAN)
                        .forCronType(CronType.QUARTZ)
                        .build();
        Assert.assertEquals("5 secondi fino a 10 dopo il minute", descriptor.getDescription("5-10 * * * * *"));
    }

    @Test
    public void testSecondMinutesHoursIntervals() throws Exception {
        //TODO Flip 5 & seconds
        descriptor =
                DescriptorParamsBuilder.createDescriptor()
                        .withLocale(ITALIAN)
                        .forCronType(CronType.QUARTZ)
                        .build();
        Assert.assertEquals("5 secondi fino a 10 dopo il minute, da 30 fino a 35 minute dopo l'ora, tra 10:00 AM e 12:00 PM",
                descriptor.getDescription("5-10 30-35 10-12 * * *"));
    }

    @Test
    public void testEvery5MinutesAt30Seconds() throws Exception {
        descriptor =
                DescriptorParamsBuilder.createDescriptor()
                        .withLocale(ITALIAN)
                        .forCronType(CronType.QUARTZ)
                        .build();
        Assert.assertEquals("30 secondi dopo il minuto, ogni 5 minuti", descriptor.getDescription("30 */5 * * * *"));
    }

    @Test
    public void testMinutesPastTheHourRange() throws Exception {
        Assert.assertEquals("30 minuti dopo l'ora, tra 10:00 AM e 1:00 PM, solo di mercoledì e venerdì",
                descriptor.getDescription("30 10-13 ? * WED,FRI"));
    }

    @Test
    public void testSecondsPastTheMinuteInterval() throws Exception {
        descriptor =
                DescriptorParamsBuilder.createDescriptor()
                        .withLocale(ITALIAN)
                        .forCronType(CronType.QUARTZ)
                        .build();
        Assert.assertEquals("10 secondi dopo il minuto, ogni 5 minuti", descriptor.getDescription("10 0/5 * * * ?"));
    }

    @Test
    public void testBetweenWithInterval() throws Exception {
        Assert.assertEquals("Ogni 3 minuti, da 02 fino a 59 minute dopo l'ora, 1:00 AM, 9:00 AM, e 10:00 PM, compreso tra i giorni 11 e 26 del mese, gennaio fino a giugno",
                descriptor.getDescription("2-59/3 1,9,22 11-26 1-6 ?"));
    }

    @Test
    public void testRecurringFirstOfMonth() throws Exception {
        Assert.assertEquals("Alle 6:00:00 AM", descriptor.getDescription("0 6 1/1 * ?"));
    }

    @Test
    public void testMinutesPastTheHour() throws Exception {
        Assert.assertEquals("05 minuti dopo l'ora", descriptor.getDescription("5 0/1 * * ?"));
    }

}
