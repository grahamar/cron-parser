package net.redhogs.cronparser;

import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

public class CronExpressionDescriptorPTTest {

  private static final Locale PORTUGUESE = new Locale("pt", "BR");

  @Test
  public void testEverySecond() throws Exception {
    Assert.assertEquals("A cada segundo", CronExpressionDescriptor.getDescription("* * * * * *", Options.twentyFourHour(), PORTUGUESE));
  }

  @Test
  public void testEvery45Seconds() throws Exception {
    Assert.assertEquals("A cada 45 segundos", CronExpressionDescriptor.getDescription("*/45 * * * * *", Options.twentyFourHour(), PORTUGUESE));
  }

  @Test
  public void testMinuteSpan() throws Exception {
    Assert.assertEquals("A cada minuto entre 11:00 e 11:10", CronExpressionDescriptor.getDescription("0-10 11 * * *", Options.twentyFourHour(), PORTUGUESE));
  }

  @Test
  public void testEveryMinute() throws Exception {
    Assert.assertEquals("A cada minuto", CronExpressionDescriptor.getDescription("* * * * *", Options.twentyFourHour(), PORTUGUESE));
    Assert.assertEquals("A cada minuto", CronExpressionDescriptor.getDescription("*/1 * * * *", Options.twentyFourHour(), PORTUGUESE));
    Assert.assertEquals("A cada minuto", CronExpressionDescriptor.getDescription("0 0/1 * * * ?", Options.twentyFourHour(), PORTUGUESE));
  }

  @Test
  public void testEveryXMinutes() throws Exception {
    Assert.assertEquals("A cada 5 minutos", CronExpressionDescriptor.getDescription("*/5 * * * *", Options.twentyFourHour(), PORTUGUESE));
    Assert.assertEquals("A cada 5 minutos", CronExpressionDescriptor.getDescription("0 */5 * * * *", Options.twentyFourHour(), PORTUGUESE));
    Assert.assertEquals("A cada 10 minutos", CronExpressionDescriptor.getDescription("0 0/10 * * * ?", Options.twentyFourHour(), PORTUGUESE));
  }

  @Test
  public void testEveryHour() throws Exception {
    Assert.assertEquals("A cada hora", CronExpressionDescriptor.getDescription("0 0 * * * ?", Options.twentyFourHour(), PORTUGUESE));
    Assert.assertEquals("A cada hora", CronExpressionDescriptor.getDescription("0 0 0/1 * * ?", Options.twentyFourHour(), PORTUGUESE));
  }

  @Test
  public void testDaileAtTime() throws Exception {
    Assert.assertEquals("Às 11:30", CronExpressionDescriptor.getDescription("30 11 * * *", Options.twentyFourHour(), PORTUGUESE));
  }

  @Test
  public void testTimeOfDaeCertainDaesOfWeek() throws Exception {
    Assert.assertEquals("Às 23:00, Segunda-feira até Sexta-feira", CronExpressionDescriptor.getDescription("0 23 ? * MON-FRI", Options.twentyFourHour(), PORTUGUESE));
    Assert.assertEquals("Às 11:30, Segunda-feira até Sexta-feira", CronExpressionDescriptor.getDescription("30 11 * * 1-5", Options.twentyFourHour(), PORTUGUESE));
  }

  @Test
  public void testOneMonthOnle() throws Exception {
    Assert.assertEquals("A cada minuto, em Março", CronExpressionDescriptor.getDescription("* * * 3 *", Options.twentyFourHour(), PORTUGUESE));
  }

  @Test
  public void testTwoMonthsOnle() throws Exception {
    Assert.assertEquals("A cada minuto, em Março e Junho", CronExpressionDescriptor.getDescription("* * * 3,6 *", Options.twentyFourHour(), PORTUGUESE));
  }

  @Test
  public void testTwoTimêsEachAfternoon() throws Exception {
    Assert.assertEquals("Às 14:30 e 16:30", CronExpressionDescriptor.getDescription("30 14,16 * * *", Options.twentyFourHour(), PORTUGUESE));
  }

  @Test
  public void testThreeTimêsDaile() throws Exception {
    Assert.assertEquals("Às 06:30, 14:30 e 16:30", CronExpressionDescriptor.getDescription("30 6,14,16 * * *", Options.twentyFourHour(), PORTUGUESE));
  }

  @Test
  public void testOnceAWeek() throws Exception {
    Assert.assertEquals("Às 09:46, somente Domingo", CronExpressionDescriptor.getDescription("46 9 * * 0", Options.twentyFourHour(), PORTUGUESE));
    Assert.assertEquals("Às 09:46, somente Domingo", CronExpressionDescriptor.getDescription("46 9 * * 7", Options.twentyFourHour(), PORTUGUESE));
    Assert.assertEquals("Às 09:46, somente Segunda-feira", CronExpressionDescriptor.getDescription("46 9 * * 1", Options.twentyFourHour(), PORTUGUESE));
    Assert.assertEquals("Às 09:46, somente Sábado", CronExpressionDescriptor.getDescription("46 9 * * 6", Options.twentyFourHour(), PORTUGUESE));
  }

  @Test
  public void testOnceAWeekNonZeroBased() throws Exception {
    Options options = new Options();
    options.setZeroBasedDayOfWeek(false);
    options.setTwentyFourHourTime(true);
    Assert.assertEquals("Às 09:46, somente Domingo", CronExpressionDescriptor.getDescription("46 9 * * 1", options, PORTUGUESE));
    Assert.assertEquals("Às 09:46, somente Segunda-feira", CronExpressionDescriptor.getDescription("46 9 * * 2", options, PORTUGUESE));
    Assert.assertEquals("Às 09:46, somente Sábado", CronExpressionDescriptor.getDescription("46 9 * * 7", options, PORTUGUESE));
  }

  @Test
  public void testTwiceAWeek() throws Exception {
    Assert.assertEquals("Às 09:46, somente Segunda-feira e Terça-feira", CronExpressionDescriptor.getDescription("46 9 * * 1,2", Options.twentyFourHour(), PORTUGUESE));
    Assert.assertEquals("Às 09:46, somente Domingo e Sábado", CronExpressionDescriptor.getDescription("46 9 * * 0,6", Options.twentyFourHour(), PORTUGUESE));
    Assert.assertEquals("Às 09:46, somente Sábado e Domingo", CronExpressionDescriptor.getDescription("46 9 * * 6,7", Options.twentyFourHour(), PORTUGUESE));
  }

  @Test
  public void testTwiceAWeekNonZeroBased() throws Exception {
    Options options = new Options();
    options.setZeroBasedDayOfWeek(false);
    options.setTwentyFourHourTime(true);
    Assert.assertEquals("Às 09:46, somente Domingo e Segunda-feira", CronExpressionDescriptor.getDescription("46 9 * * 1,2", options, PORTUGUESE));
    Assert.assertEquals("Às 09:46, somente Sexta-feira e Sábado", CronExpressionDescriptor.getDescription("46 9 * * 6,7", options, PORTUGUESE));
  }

  @Test
  public void testDayOfMonth() throws Exception {
    Assert.assertEquals("Às 12:23, no(s) dia(s) 15 do mês", CronExpressionDescriptor.getDescription("23 12 15 * *", Options.twentyFourHour(), PORTUGUESE));
  }

  @Test
  public void testTwiceDayOfEveryMonth() throws Exception {
    Assert.assertEquals("A cada minuto, no(s) dia(s) 7 e 8 do mês", CronExpressionDescriptor.getDescription("0 * * 7,8 * ?", Options.twentyFourHour(), PORTUGUESE));
  }

  @Test
  public void testPeriodicallyEveryMonth() throws Exception {
    Assert.assertEquals("A cada minuto, no(s) dia(s) 7, 8, 9 e 10 do mês", CronExpressionDescriptor.getDescription("0 * * 7,8,9,10 * ?", Options.twentyFourHour(), PORTUGUESE));
  }

  @Test
  public void testMonthName() throws Exception {
    Assert.assertEquals("Às 12:23, em Janeiro", CronExpressionDescriptor.getDescription("23 12 * JAN *", Options.twentyFourHour(), PORTUGUESE));
  }

  @Test
  public void testDaeOfMonthWithQuestionMark() throws Exception {
    Assert.assertEquals("Às 12:23, em Janeiro", CronExpressionDescriptor.getDescription("23 12 ? JAN *", Options.twentyFourHour(), PORTUGUESE));
  }

  @Test
  public void testMonthNameRange2() throws Exception {
    Assert.assertEquals("Às 12:23, Janeiro até Fevereiro", CronExpressionDescriptor.getDescription("23 12 * JAN-FEB *", Options.twentyFourHour(), PORTUGUESE));
  }

  @Test
  public void testMonthNameRange3() throws Exception {
    Assert.assertEquals("Às 12:23, Janeiro até Março", CronExpressionDescriptor.getDescription("23 12 * JAN-MAR *", Options.twentyFourHour(), PORTUGUESE));
  }

  @Test
  public void testMonthNameRanges() throws Exception {
    Assert.assertEquals("Às 3:00 AM, em Janeiro até Março e Maio até Junho", CronExpressionDescriptor.getDescription("0 0 3 * 1-3,5-6 *", PORTUGUESE));
  }

  @Test
  public void testDaeOfWeekName() throws Exception {
    Assert.assertEquals("Às 12:23, somente Domingo", CronExpressionDescriptor.getDescription("23 12 * * SUN", Options.twentyFourHour(), PORTUGUESE));
  }

  @Test
  public void testDayOfWeekRange() throws Exception {
    Assert.assertEquals("A cada 5 minutos, 15:00, Segunda-feira até Sexta-feira", CronExpressionDescriptor.getDescription("*/5 15 * * MON-FRI", Options.twentyFourHour(), PORTUGUESE));
    Assert.assertEquals("A cada 5 minutos, 15:00, Domingo até Sábado", CronExpressionDescriptor.getDescription("*/5 15 * * 0-6", Options.twentyFourHour(), PORTUGUESE));
    Assert.assertEquals("A cada 5 minutos, 15:00, Sábado até Domingo", CronExpressionDescriptor.getDescription("*/5 15 * * 6-7", Options.twentyFourHour(), PORTUGUESE));
  }

  @Test
  public void testDayOfWeekRanges() throws Exception {
    Assert.assertEquals("Às 3:00 AM, somente Domingo, Terça-feira até Quinta-feira e Sábado", CronExpressionDescriptor.getDescription("0 0 3 * * 0,2-4,6", PORTUGUESE));
  }

  @Test
  public void testDaeOfWeekOnceInMonth() throws Exception {
    Assert.assertEquals("A cada minuto, no(a) terceiro(a) Segunda-feira do mês", CronExpressionDescriptor.getDescription("* * * * MON#3", Options.twentyFourHour(), PORTUGUESE));
    Assert.assertEquals("A cada minuto, no(a) terceiro(a) Domingo do mês", CronExpressionDescriptor.getDescription("* * * * 0#3", Options.twentyFourHour(), PORTUGUESE));
  }

  @Test
  public void testLastDaeOfTheWeekOfTheMonth() throws Exception {
    Assert.assertEquals("A cada minuto, no último Quinta-feira do mês", CronExpressionDescriptor.getDescription("* * * * 4L", Options.twentyFourHour(), PORTUGUESE));
    Assert.assertEquals("A cada minuto, no último Domingo do mês", CronExpressionDescriptor.getDescription("* * * * 0L", Options.twentyFourHour(), PORTUGUESE));
  }

  @Test
  public void testLastDaeOfTheMonth() throws Exception {
    Assert.assertEquals("A cada 5 minutos, no último dia do mês, em Janeiro", CronExpressionDescriptor.getDescription("*/5 * L JAN *", Options.twentyFourHour(), PORTUGUESE));
  }

  @Test
  public void testTimeOfDaeWithSeconds() throws Exception {
    Assert.assertEquals("Às 14:02:30", CronExpressionDescriptor.getDescription("30 02 14 * * *", Options.twentyFourHour(), PORTUGUESE));
  }

  @Test
  public void testSecondInternvals() throws Exception {
    //TODO Flip 5 & seconds
    Assert.assertEquals("5 segundos 10 depois do minuto", CronExpressionDescriptor.getDescription("5-10 * * * * *", Options.twentyFourHour(), PORTUGUESE));
  }

  @Test
  public void testSecondMinutesHoursIntervals() throws Exception {
    //TODO Flip 5 & seconds
    Assert.assertEquals("5 segundos 10 depois do minuto, desde 30 até o 35 minuto depois da hora, entre 10:00 e 12:00",
        CronExpressionDescriptor.getDescription("5-10 30-35 10-12 * * *", Options.twentyFourHour(), PORTUGUESE));
  }

  @Test
  public void testEvery5MinutesAt30Seconds() throws Exception {
    Assert.assertEquals("30 segundos depois do minuto, a cada 5 minutos", CronExpressionDescriptor.getDescription("30 */5 * * * *", Options.twentyFourHour(), PORTUGUESE));
  }

  @Test
  public void testMinutesPastTheHourRange() throws Exception {
    Assert.assertEquals("30 minutos hora depois, entre 10:00 e 13:00, somente Quarta-feira e Sexta-feira",
        CronExpressionDescriptor.getDescription("0 30 10-13 ? * WED,FRI", Options.twentyFourHour(), PORTUGUESE));
  }

  @Test
  public void testSecondsPastTheMinuteInterval() throws Exception {
    Assert.assertEquals("10 segundos depois do minuto, a cada 5 minutos", CronExpressionDescriptor.getDescription("10 0/5 * * * ?", Options.twentyFourHour(), PORTUGUESE));
  }

  @Test
  public void testBetweEmWithInterval() throws Exception {
    Assert.assertEquals("A cada 3 minutos, desde 02 até o 59 minuto depois da hora, 01:00, 09:00 e 22:00, entre o(a) 11 e o(a) 26 do mês, Janeiro até Junho",
        CronExpressionDescriptor.getDescription("2-59/3 1,9,22 11-26 1-6 ?", Options.twentyFourHour(), PORTUGUESE));
  }

  @Test
  public void testRecurringFirstOfMonth() throws Exception {
    Assert.assertEquals("Às 06:00", CronExpressionDescriptor.getDescription("0 0 6 1/1 * ?", Options.twentyFourHour(), PORTUGUESE));
  }

  @Test
  public void testMinutesPastTheHour() throws Exception {
    Assert.assertEquals("05 minutos hora depois", CronExpressionDescriptor.getDescription("0 5 0/1 * * ?", Options.twentyFourHour(), PORTUGUESE));
  }

  @Test
  public void testEveryXMinutePastTheHourWithInterval() throws Exception {
    Assert.assertEquals("A cada 2 minutos, desde 00 até o 30 minuto depois da hora, 17:00, Segunda-feira até Sexta-feira", CronExpressionDescriptor.getDescription("0 0-30/2 17 ? * MON-FRI", Options.twentyFourHour(), PORTUGUESE));
  }

  @Test
  public void testOneeearOnleWithSeconds() throws Exception {
    Assert.assertEquals("A cada segundo, em 2013", CronExpressionDescriptor.getDescription("* * * * * * 2013", Options.twentyFourHour(), PORTUGUESE));
  }

  @Test
  public void testOneeearOnleWithoutSeconds() throws Exception {
    Assert.assertEquals("A cada minuto, em 2013", CronExpressionDescriptor.getDescription("* * * * * 2013", Options.twentyFourHour(), PORTUGUESE));
  }

  @Test
  public void testTwoeearsOnle() throws Exception {
    Assert.assertEquals("A cada minuto, em 2013 e 2014", CronExpressionDescriptor.getDescription("* * * * * 2013,2014", Options.twentyFourHour(), PORTUGUESE));
  }

  @Test
  public void testeearRange2() throws Exception {
    Assert.assertEquals("Às 12:23, Janeiro até Fevereiro, 2013 até 2014", CronExpressionDescriptor.getDescription("23 12 * JAN-FEB * 2013-2014", Options.twentyFourHour(), PORTUGUESE));
  }

  @Test
  public void testeearRange3() throws Exception {
    Assert.assertEquals("Às 12:23, Janeiro até Março, 2013 até 2015", CronExpressionDescriptor.getDescription("23 12 * JAN-MAR * 2013-2015", Options.twentyFourHour(), PORTUGUESE));
  }

  @Test
  public void testIssue26() throws Exception {
    Assert.assertEquals("05 e 10 minutos hora depois", CronExpressionDescriptor.getDescription("5,10 0 * * *", Options.twentyFourHour(), PORTUGUESE));
    Assert.assertEquals("05 e 10 minutos hora depois, no(s) dia(s) 2 do mês", CronExpressionDescriptor.getDescription("5,10 0 2 * *", Options.twentyFourHour(), PORTUGUESE));
    Assert.assertEquals("A cada 10 minutos, no(s) dia(s) 2 do mês", CronExpressionDescriptor.getDescription("5/10 0 2 * *", Options.twentyFourHour(), PORTUGUESE));

    Assert.assertEquals("5 e 6 segundos depois do minuto", CronExpressionDescriptor.getDescription("5,6 0 0 * * *", Options.twentyFourHour(), PORTUGUESE));
    Assert.assertEquals("5 e 6 segundos depois do minuto, 01:00", CronExpressionDescriptor.getDescription("5,6 0 1 * * *", Options.twentyFourHour(), PORTUGUESE));
    Assert.assertEquals("5 e 6 segundos depois do minuto, no(s) dia(s) 2 do mês", CronExpressionDescriptor.getDescription("5,6 0 0 2 * *", Options.twentyFourHour(), PORTUGUESE));
  }

}
