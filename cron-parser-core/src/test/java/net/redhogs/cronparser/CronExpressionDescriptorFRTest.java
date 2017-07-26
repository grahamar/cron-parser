package net.redhogs.cronparser;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author grhodes
 * @since 10 Dec 2012 14:46:13
 */
public class CronExpressionDescriptorFRTest {

   private static final Locale FRENCH = new Locale("fr");

    @Test
    public void testEverySecond() throws Exception {
        Assert.assertEquals("Toutes les secondes", CronExpressionDescriptor.getDescription("* * * * * *", FRENCH));
        Assert.assertEquals("Toutes les secondes", CronExpressionDescriptor.getDescription("* * * * * *", Options.twentyFourHour(), FRENCH));
    }

    @Test
    public void testEvery45Seconds() throws Exception {
        Assert.assertEquals("Tous les 45 secondes", CronExpressionDescriptor.getDescription("*/45 * * * * *", FRENCH));
        Assert.assertEquals("Tous les 45 secondes", CronExpressionDescriptor.getDescription("*/45 * * * * *", Options.twentyFourHour(), FRENCH));
    }

    @Test
    public void testMinuteSpan() throws Exception {
        Assert.assertEquals("Toutes les minutes entre 11:00 AM et 11:10 AM", CronExpressionDescriptor.getDescription("0-10 11 * * *", FRENCH));
        Assert.assertEquals("Toutes les minutes entre 11:00 et 11:10", CronExpressionDescriptor.getDescription("0-10 11 * * *", Options.twentyFourHour(), FRENCH));
    }

    @Test
    public void testEveryMinute() throws Exception {
        Assert.assertEquals("Toutes les minutes", CronExpressionDescriptor.getDescription("* * * * *", FRENCH));
        Assert.assertEquals("Toutes les minutes", CronExpressionDescriptor.getDescription("*/1 * * * *", FRENCH));
        Assert.assertEquals("Toutes les minutes", CronExpressionDescriptor.getDescription("0 0/1 * * * ?", FRENCH));
    }

    @Test
    public void testEveryXMinutes() throws Exception {
        Assert.assertEquals("Tous(tes) les 5 minutes", CronExpressionDescriptor.getDescription("*/5 * * * *", FRENCH));
        Assert.assertEquals("Tous(tes) les 5 minutes", CronExpressionDescriptor.getDescription("0 */5 * * * *", FRENCH));
        Assert.assertEquals("Tous(tes) les 10 minutes", CronExpressionDescriptor.getDescription("0 0/10 * * * ?", FRENCH));
    }

    @Test
    public void testEveryHour() throws Exception {
        Assert.assertEquals("Toutes les heures", CronExpressionDescriptor.getDescription("0 0 * * * ?", FRENCH));
        Assert.assertEquals("Toutes les heures", CronExpressionDescriptor.getDescription("0 0 0/1 * * ?", FRENCH));
    }

    @Test
    public void testDailyAtTime() throws Exception {
        Assert.assertEquals("À 11:30 AM", CronExpressionDescriptor.getDescription("30 11 * * *", FRENCH));
        Assert.assertEquals("À 11:30", CronExpressionDescriptor.getDescription("30 11 * * *", Options.twentyFourHour(), FRENCH));
    }

    @Test
    public void testTimeOfDayCertainDaysOfWeek() throws Exception {
        Assert.assertEquals("À 11:00 PM, du lundi au vendredi", CronExpressionDescriptor.getDescription("0 23 ? * MON-FRI", FRENCH));
        Assert.assertEquals("À 23:00, du lundi au vendredi", CronExpressionDescriptor.getDescription("0 23 ? * MON-FRI", Options.twentyFourHour(), FRENCH));
        Assert.assertEquals("À 11:30 AM, du lundi au vendredi", CronExpressionDescriptor.getDescription("30 11 * * 1-5", FRENCH));
    }

    @Test
    public void testOneMonthOnly() throws Exception {
        Assert.assertEquals("Toutes les minutes, seulement en mars", CronExpressionDescriptor.getDescription("* * * 3 *", FRENCH));
    }

    @Test
    public void testTwoMonthsOnly() throws Exception {
        Assert.assertEquals("Toutes les minutes, seulement en mars et juin", CronExpressionDescriptor.getDescription("* * * 3,6 *", FRENCH));
    }

    @Test
    public void testTwoTimesEachAfternoon() throws Exception {
        Assert.assertEquals("À 2:30 PM et 4:30 PM", CronExpressionDescriptor.getDescription("30 14,16 * * *", FRENCH));
        Assert.assertEquals("À 14:30 et 16:30", CronExpressionDescriptor.getDescription("30 14,16 * * *", Options.twentyFourHour(), FRENCH));
    }

    @Test
    public void testThreeTimesDaily() throws Exception {
        Assert.assertEquals("À 6:30 AM, 2:30 PM et 4:30 PM", CronExpressionDescriptor.getDescription("30 6,14,16 * * *", FRENCH));
        Assert.assertEquals("À 06:30, 14:30 et 16:30", CronExpressionDescriptor.getDescription("30 6,14,16 * * *", Options.twentyFourHour(), FRENCH));
    }

    @Test
    public void testOnceAWeek() throws Exception {
        Assert.assertEquals("À 9:46 AM, seulement le dimanche", CronExpressionDescriptor.getDescription("46 9 * * 0", FRENCH));
        Assert.assertEquals("À 9:46 AM, seulement le dimanche", CronExpressionDescriptor.getDescription("46 9 * * 7", FRENCH));
        Assert.assertEquals("À 9:46 AM, seulement le lundi", CronExpressionDescriptor.getDescription("46 9 * * 1", FRENCH));
        Assert.assertEquals("À 9:46 AM, seulement le samedi", CronExpressionDescriptor.getDescription("46 9 * * 6", FRENCH));
    }

    @Test
    public void testOnceAWeekNonZeroBased() throws Exception {
        Options options = new Options();
        options.setZeroBasedDayOfWeek(false);
        Assert.assertEquals("À 9:46 AM, seulement le dimanche", CronExpressionDescriptor.getDescription("46 9 * * 1", options, FRENCH));
        Assert.assertEquals("À 9:46 AM, seulement le lundi", CronExpressionDescriptor.getDescription("46 9 * * 2", options, FRENCH));
        Assert.assertEquals("À 9:46 AM, seulement le samedi", CronExpressionDescriptor.getDescription("46 9 * * 7", options, FRENCH));
    }

    @Test
    public void testTwiceAWeek() throws Exception {
        Assert.assertEquals("À 9:46 AM, seulement le lundi et mardi", CronExpressionDescriptor.getDescription("46 9 * * 1,2", FRENCH));
        Assert.assertEquals("À 9:46 AM, seulement le dimanche et samedi", CronExpressionDescriptor.getDescription("46 9 * * 0,6", FRENCH));
        Assert.assertEquals("À 9:46 AM, seulement le samedi et dimanche", CronExpressionDescriptor.getDescription("46 9 * * 6,7", FRENCH));
    }

    @Test
    public void testTwiceAWeekNonZeroBased() throws Exception {
        Options options = new Options();
        options.setZeroBasedDayOfWeek(false);
        Assert.assertEquals("À 9:46 AM, seulement le dimanche et lundi", CronExpressionDescriptor.getDescription("46 9 * * 1,2", options, FRENCH));
        Assert.assertEquals("À 9:46 AM, seulement le vendredi et samedi", CronExpressionDescriptor.getDescription("46 9 * * 6,7", options, FRENCH));
    }

    @Test
    public void testDayOfMonth() throws Exception {
        Assert.assertEquals("À 12:23 PM, le 15ème jour du mois", CronExpressionDescriptor.getDescription("23 12 15 * *", FRENCH));
        Assert.assertEquals("À 12:23, le 15ème jour du mois", CronExpressionDescriptor.getDescription("23 12 15 * *", Options.twentyFourHour(), FRENCH));
    }

    @Test
    public void testMonthName() throws Exception {
        Assert.assertEquals("À 12:23 PM, seulement en janvier", CronExpressionDescriptor.getDescription("23 12 * JAN *", FRENCH));
    }

    @Test
    public void testDayOfMonthWithQuestionMark() throws Exception {
        Assert.assertEquals("À 12:23 PM, seulement en janvier", CronExpressionDescriptor.getDescription("23 12 ? JAN *", FRENCH));
    }

    @Test
    public void testMonthNameRange2() throws Exception {
        Assert.assertEquals("À 12:23 PM, de janvier à février", CronExpressionDescriptor.getDescription("23 12 * JAN-FEB *", FRENCH));
    }

    @Test
    public void testMonthNameRange3() throws Exception {
        Assert.assertEquals("À 12:23 PM, de janvier à mars", CronExpressionDescriptor.getDescription("23 12 * JAN-MAR *", FRENCH));
    }

    @Test
    public void testDayOfWeekName() throws Exception {
        Assert.assertEquals("À 12:23 PM, seulement le dimanche", CronExpressionDescriptor.getDescription("23 12 * * SUN", FRENCH));
    }

    @Test
    public void testDayOfWeekRange() throws Exception {
        Assert.assertEquals("Tous(tes) les 5 minutes, à 3:00 PM, du lundi au vendredi", CronExpressionDescriptor.getDescription("*/5 15 * * MON-FRI", FRENCH));
        Assert.assertEquals("Tous(tes) les 5 minutes, à 3:00 PM, du dimanche au samedi", CronExpressionDescriptor.getDescription("*/5 15 * * 0-6", FRENCH));
        Assert.assertEquals("Tous(tes) les 5 minutes, à 3:00 PM, du samedi au dimanche", CronExpressionDescriptor.getDescription("*/5 15 * * 6-7", FRENCH));
    }

    @Test
    public void testDayOfWeekOnceInMonth() throws Exception {
        Assert.assertEquals("Toutes les minutes, le troisième lundi du mois", CronExpressionDescriptor.getDescription("* * * * MON#3", FRENCH));
        Assert.assertEquals("Toutes les minutes, le troisième dimanche du mois", CronExpressionDescriptor.getDescription("* * * * 0#3", FRENCH));
    }

    @Test
    public void testLastDayOfTheWeekOfTheMonth() throws Exception {
        Assert.assertEquals("Toutes les minutes, le dernier jeudi du mois", CronExpressionDescriptor.getDescription("* * * * 4L", FRENCH));
        Assert.assertEquals("Toutes les minutes, le dernier dimanche du mois", CronExpressionDescriptor.getDescription("* * * * 0L", FRENCH));
    }

    @Test
    public void testLastDayOfTheMonth() throws Exception {
        Assert.assertEquals("Tous(tes) les 5 minutes, le dernier jour du mois, seulement en janvier", CronExpressionDescriptor.getDescription("*/5 * L JAN *", FRENCH));
    }

    @Test
    public void testTimeOfDayWithSeconds() throws Exception {
        Assert.assertEquals("À 2:02:30 PM", CronExpressionDescriptor.getDescription("30 02 14 * * *", FRENCH));
    }

    @Test
    public void testSecondInternvals() throws Exception {
        Assert.assertEquals("De la 5ème à la 10ème seconde après la minute", CronExpressionDescriptor.getDescription("5-10 * * * * *", FRENCH));
    }

    @Test
    public void testSecondMinutesHoursIntervals() throws Exception {
        Assert.assertEquals("De la 5ème à la 10ème seconde après la minute, de la 30ème à la 35ème minute après l'heure, entre 10:00 AM et 12:00 PM",
                CronExpressionDescriptor.getDescription("5-10 30-35 10-12 * * *", FRENCH));
    }

    @Test
    public void testEvery5MinutesAt30Seconds() throws Exception {
        Assert.assertEquals("À 30 secondes après la minute, tous(tes) les 5 minutes", CronExpressionDescriptor.getDescription("30 */5 * * * *", FRENCH));
    }

    @Test
    public void testMinutesPastTheHourRange() throws Exception {
        Assert.assertEquals("À 30 minutes après l'heure, entre 10:00 AM et 1:00 PM, seulement le mercredi et vendredi",
                CronExpressionDescriptor.getDescription("0 30 10-13 ? * WED,FRI", FRENCH));
    }

    @Test
    public void testSecondsPastTheMinuteInterval() throws Exception {
        Assert.assertEquals("À 10 secondes après la minute, tous(tes) les 5 minutes", CronExpressionDescriptor.getDescription("10 0/5 * * * ?", FRENCH));
    }

    @Test
    public void testBetweenWithInterval() throws Exception {
        Assert.assertEquals("Tous(tes) les 3 minutes, de la 02ème à la 59ème minute après l'heure, à 1:00 AM, 9:00 AM et 10:00 PM, entre le 11ème et le 26ème jour du mois, de janvier à juin",
                CronExpressionDescriptor.getDescription("2-59/3 1,9,22 11-26 1-6 ?", FRENCH));
    }

    @Test
    public void testRecurringFirstOfMonth() throws Exception {
        Assert.assertEquals("À 6:00 AM", CronExpressionDescriptor.getDescription("0 0 6 1/1 * ?", FRENCH));
    }

    @Test
    public void testMinutesPastTheHour() throws Exception {
        Assert.assertEquals("À 05 minutes après l'heure", CronExpressionDescriptor.getDescription("0 5 0/1 * * ?", FRENCH));
    }

    /**
     * @since https://github.com/RedHogs/cron-parser/issues/2
     */
    @Test
    public void testEveryPastTheHour() throws Exception {
        Assert.assertEquals("À 00, 05, 10, 15, 20, 25, 30, 35, 40, 45, 50 et 55 minutes après l'heure", CronExpressionDescriptor.getDescription("0 0,5,10,15,20,25,30,35,40,45,50,55 * ? * *", FRENCH));
    }

    /**
     * @since https://github.com/RedHogs/cron-parser/issues/10
     */
    @Test
    public void testEveryXMinutePastTheHourWithInterval() throws Exception {
        Assert.assertEquals("Tous(tes) les 2 minutes, de la 00ème à la 30ème minute après l'heure, à 5:00 PM, du lundi au vendredi", CronExpressionDescriptor.getDescription("0 0-30/2 17 ? * MON-FRI", FRENCH));
    }

    /**
     * @since https://github.com/RedHogs/cron-parser/issues/13
     */
    @Test
    public void testOneYearOnlyWithSeconds() throws Exception {
        Assert.assertEquals("Toutes les secondes, seulement en 2013", CronExpressionDescriptor.getDescription("* * * * * * 2013", FRENCH));
    }

    @Test
    public void testOneYearOnlyWithoutSeconds() throws Exception {
        Assert.assertEquals("Toutes les minutes, seulement en 2013", CronExpressionDescriptor.getDescription("* * * * * 2013", FRENCH));
    }

    @Test
    public void testTwoYearsOnly() throws Exception {
        Assert.assertEquals("Toutes les minutes, seulement en 2013 et 2014", CronExpressionDescriptor.getDescription("* * * * * 2013,2014", FRENCH));
    }

    @Test
    public void testYearRange2() throws Exception {
        Assert.assertEquals("À 12:23 PM, de janvier à février, de 2013 à 2014", CronExpressionDescriptor.getDescription("23 12 * JAN-FEB * 2013-2014", FRENCH));
    }

    @Test
    public void testYearRange3() throws Exception {
        Assert.assertEquals("À 12:23 PM, de janvier à mars, de 2013 à 2015", CronExpressionDescriptor.getDescription("23 12 * JAN-MAR * 2013-2015", FRENCH));
    }

    @Test
    public void testIssue26() throws Exception {
        Assert.assertEquals("À 05 et 10 minutes après l'heure", CronExpressionDescriptor.getDescription("5,10 0 * * *", FRENCH));
        Assert.assertEquals("À 05 et 10 minutes après l'heure, le 2ème jour du mois", CronExpressionDescriptor.getDescription("5,10 0 2 * *", FRENCH));
        Assert.assertEquals("Tous(tes) les 10 minutes, le 2ème jour du mois", CronExpressionDescriptor.getDescription("5/10 0 2 * *", FRENCH));

        Assert.assertEquals("À 5 et 6 secondes après la minute", CronExpressionDescriptor.getDescription("5,6 0 0 * * *", FRENCH));
        Assert.assertEquals("À 5 et 6 secondes après la minute, à 1:00 AM", CronExpressionDescriptor.getDescription("5,6 0 1 * * *", FRENCH));
        Assert.assertEquals("À 5 et 6 secondes après la minute, le 2ème jour du mois", CronExpressionDescriptor.getDescription("5,6 0 0 2 * *", FRENCH));
    }

}
