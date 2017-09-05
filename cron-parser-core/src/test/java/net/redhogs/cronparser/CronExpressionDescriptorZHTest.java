package net.redhogs.cronparser;

import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

/**
 * @author vitctor wang
 * @since 02 SEP 2017 18:28:13
 */
public class CronExpressionDescriptorZHTest {
    private static final Locale CHINESE = new Locale("zh");
    private static final Options options = new Options();

    static {
        options.setTwentyFourHourTime(true);
        /** Chinese, Japanese, Korean and other East Asian languages have no spaces between words */
        options.setNeedSpaceBetweenWords(false);
    }

    @Test
    public void testEverySecond() throws Exception {
        Assert.assertEquals("每秒钟", CronExpressionDescriptor.getDescription("* * * * * *", options, CHINESE));
        Assert.assertEquals("每秒钟", CronExpressionDescriptor.getDescription("* * * * * *", options, CHINESE));
    }

    @Test
    public void testEvery45Seconds() throws Exception {
        Assert.assertEquals("每45秒", CronExpressionDescriptor.getDescription("*/45 * * * * *", options, CHINESE));
        Assert.assertEquals("每45秒", CronExpressionDescriptor.getDescription("*/45 * * * * *", options, CHINESE));
    }

    @Test
    public void testMinuteSpan() throws Exception {
        Assert.assertEquals("在上午11:00和上午11:10之间的每分钟", CronExpressionDescriptor.getDescription("0-10 11 * * *", options, CHINESE));
        Assert.assertEquals("每分钟, 在上午1:00", CronExpressionDescriptor.getDescription("* 1 * * *", options, CHINESE));
        Assert.assertEquals("每分钟, 在上午12:00", CronExpressionDescriptor.getDescription("* 0 * * *", options, CHINESE));
    }

    @Test
    public void testEveryMinute() throws Exception {
        Assert.assertEquals("每分钟", CronExpressionDescriptor.getDescription("* * * * *", CHINESE));
        Assert.assertEquals("每分钟", CronExpressionDescriptor.getDescription("*/1 * * * *", CHINESE));
        Assert.assertEquals("每分钟", CronExpressionDescriptor.getDescription("0 0/1 * * * ?", CHINESE));
    }

    @Test
    public void testEveryXMinutes() throws Exception {
        Assert.assertEquals("每5分钟", CronExpressionDescriptor.getDescription("*/5 * * * *", options, CHINESE));
        Assert.assertEquals("每5分钟", CronExpressionDescriptor.getDescription("0 */5 * * * *", options, CHINESE));
        Assert.assertEquals("每10分钟", CronExpressionDescriptor.getDescription("0 0/10 * * * ?", options, CHINESE));
    }

    @Test
    public void testEveryHour() throws Exception {
        Assert.assertEquals("每小时", CronExpressionDescriptor.getDescription("0 0 * * * ?", options, CHINESE));
        Assert.assertEquals("每小时", CronExpressionDescriptor.getDescription("0 0 0/1 * * ?", options, CHINESE));
        Assert.assertEquals("每小时", CronExpressionDescriptor.getDescription("0 * * * *", options, CHINESE));
    }

    @Test
    public void testDailyAtTime() throws Exception {
        Assert.assertEquals("在上午11:30", CronExpressionDescriptor.getDescription("30 11 * * *", options, CHINESE));
        Assert.assertEquals("在上午11:00", CronExpressionDescriptor.getDescription("0 11 * * *", options, CHINESE));
    }

    @Test
    public void testTimeOfDayCertainDaysOfWeek() throws Exception {
        Assert.assertEquals("在下午11:00, 星期一到星期五", CronExpressionDescriptor.getDescription("0 23 ? * MON-FRI", options, CHINESE));
        Assert.assertEquals("在下午11:00, 星期一到星期五", CronExpressionDescriptor.getDescription("0 23 ? * MON-FRI", options, CHINESE));
        Assert.assertEquals("在上午11:30, 星期一到星期五", CronExpressionDescriptor.getDescription("30 11 * * 1-5", options, CHINESE));
    }

    @Test
    public void testOneMonthOnly() throws Exception {
        Assert.assertEquals("每分钟, 仅在三月", CronExpressionDescriptor.getDescription("* * * 3 *", CHINESE));
    }

    @Test
    public void testTwoMonthsOnly() throws Exception {
        Assert.assertEquals("每分钟, 仅在三月和六月", CronExpressionDescriptor.getDescription("* * * 3,6 *", options, CHINESE));
    }

    @Test
    public void testTwoTimesEachAfternoon() throws Exception {
        Assert.assertEquals("在下午2:30和下午4:30", CronExpressionDescriptor.getDescription("30 14,16 * * *", options, CHINESE));
    }

    @Test
    public void testThreeTimesDaily() throws Exception {
        Assert.assertEquals("在上午6:30,下午2:30和下午4:30", CronExpressionDescriptor.getDescription("30 6,14,16 * * *", options, CHINESE));
    }

    @Test
    public void testOnceAWeek() throws Exception {
        Assert.assertEquals("在上午9:46, 仅在星期日", CronExpressionDescriptor.getDescription("46 9 * * 0", options, CHINESE));
        Assert.assertEquals("在上午9:46, 仅在星期日", CronExpressionDescriptor.getDescription("46 9 * * 7", options, CHINESE));
        Assert.assertEquals("在上午9:46, 仅在星期一", CronExpressionDescriptor.getDescription("46 9 * * 1", options, CHINESE));
        Assert.assertEquals("在上午9:46, 仅在星期六", CronExpressionDescriptor.getDescription("46 9 * * 6", options, CHINESE));
    }

    @Test
    public void testOnceAWeekNonZeroBased() throws Exception {
        Options options = new Options();
        options.setZeroBasedDayOfWeek(false);
        options.setNeedSpaceBetweenWords(false);
        options.setTwentyFourHourTime(true);
        Assert.assertEquals("在上午9:46, 仅在星期日", CronExpressionDescriptor.getDescription("46 9 * * 1", options, CHINESE));
        Assert.assertEquals("在上午9:46, 仅在星期一", CronExpressionDescriptor.getDescription("46 9 * * 2", options, CHINESE));
        Assert.assertEquals("在上午9:46, 仅在星期六", CronExpressionDescriptor.getDescription("46 9 * * 7", options, CHINESE));
    }

    @Test
    public void testTwiceAWeek() throws Exception {
        Assert.assertEquals("在上午9:46, 仅在星期一和星期二", CronExpressionDescriptor.getDescription("46 9 * * 1,2", options, CHINESE));
        Assert.assertEquals("在上午9:46, 仅在星期日和星期六", CronExpressionDescriptor.getDescription("46 9 * * 0,6", options, CHINESE));
        Assert.assertEquals("在上午9:46, 仅在星期六和星期日", CronExpressionDescriptor.getDescription("46 9 * * 6,7", options, CHINESE));
    }

    @Test
    public void testTwiceAWeekNonZeroBased() throws Exception {
        Options options = new Options();
        options.setZeroBasedDayOfWeek(false);
        options.setTwentyFourHourTime(true);
        options.setNeedSpaceBetweenWords(false);
        Assert.assertEquals("在上午9:46, 仅在星期日和星期一", CronExpressionDescriptor.getDescription("46 9 * * 1,2", options, CHINESE));
        Assert.assertEquals("在上午9:46, 仅在星期五和星期六", CronExpressionDescriptor.getDescription("46 9 * * 6,7", options, CHINESE));
    }

    @Test
    public void testDayOfMonth() throws Exception {
        Assert.assertEquals("在下午12:23, 在当月的15日", CronExpressionDescriptor.getDescription("23 12 15 * *", options, CHINESE));
    }

    @Test
    public void testMonthName() throws Exception {
        Assert.assertEquals("在下午12:23, 仅在一月", CronExpressionDescriptor.getDescription("23 12 * JAN *", options, CHINESE));
    }

    @Test
    public void testDayOfMonthWithQuestionMark() throws Exception {
        Assert.assertEquals("在下午12:23, 仅在一月", CronExpressionDescriptor.getDescription("23 12 ? JAN *", options, CHINESE));
    }

    @Test
    public void testMonthNameRange2() throws Exception {
        Assert.assertEquals("在下午12:23, 一月到二月", CronExpressionDescriptor.getDescription("23 12 * JAN-FEB *", options, CHINESE));
    }

    @Test
    public void testMonthNameRange3() throws Exception {
        Assert.assertEquals("在下午12:23, 一月到三月", CronExpressionDescriptor.getDescription("23 12 * JAN-MAR *", options, CHINESE));
    }

    @Test
    public void testMonthNameRanges() throws Exception {
        Assert.assertEquals("在上午3:00, 仅在一月到三月和五月到六月", CronExpressionDescriptor.getDescription("0 0 3 * 1-3,5-6 *", options, CHINESE));
    }

    @Test
    public void testDayOfWeekName() throws Exception {
        Assert.assertEquals("在下午12:23, 仅在星期日", CronExpressionDescriptor.getDescription("23 12 * * SUN", options, CHINESE));
    }

    @Test
    public void testDayOfWeekRange() throws Exception {
        Assert.assertEquals("每5分钟, 在下午3:00, 星期一到星期五", CronExpressionDescriptor.getDescription("*/5 15 * * MON-FRI", options, CHINESE));
        Assert.assertEquals("每5分钟, 在下午3:00, 星期日到星期六", CronExpressionDescriptor.getDescription("*/5 15 * * 0-6", options, CHINESE));
        Assert.assertEquals("每5分钟, 在下午3:00, 星期六到星期日", CronExpressionDescriptor.getDescription("*/5 15 * * 6-7", options, CHINESE));
    }

    @Test
    public void testDayOfWeekRanges() throws Exception {
        Assert.assertEquals("在上午3:00, 仅在星期日, 星期二到星期四和星期六", CronExpressionDescriptor.getDescription("0 0 3 * * 0,2-4,6", options, CHINESE));
    }

    @Test
    public void testDayOfWeekOnceInMonth() throws Exception {
        Assert.assertEquals("每分钟, 在当月的第3个星期一", CronExpressionDescriptor.getDescription("* * * * MON#3", CHINESE));
        Assert.assertEquals("每分钟, 在当月的第3个星期日", CronExpressionDescriptor.getDescription("* * * * 0#3", CHINESE));
    }

    @Test
    public void testLastDayOfTheWeekOfTheMonth() throws Exception {
        Assert.assertEquals("每分钟, 在当月的最后1个星期四", CronExpressionDescriptor.getDescription("* * * * 4L", CHINESE));
        Assert.assertEquals("每分钟, 在当月的最后1个星期日", CronExpressionDescriptor.getDescription("* * * * 0L", CHINESE));
    }

    @Test
    public void testLastDayOfTheMonth() throws Exception {
        Assert.assertEquals("每5分钟, 在当月的最后1天, 仅在一月", CronExpressionDescriptor.getDescription("*/5 * L JAN *", options, CHINESE));
    }

    @Test
    public void testTimeOfDayWithSeconds() throws Exception {
        Assert.assertEquals("在14:02:30", CronExpressionDescriptor.getDescription("30 02 14 * * *", options, CHINESE));
    }

    @Test
    public void testSecondInternvals() throws Exception {
        Assert.assertEquals("过整分钟后5到10秒", CronExpressionDescriptor.getDescription("5-10 * * * * *", CHINESE));
    }

    @Test
    public void testSecondMinutesHoursIntervals() throws Exception {
        Assert.assertEquals("过整分钟后5到10秒, 过整点后30到35分, 在上午10:00和下午12:00之间",
                CronExpressionDescriptor.getDescription("5-10 30-35 10-12 * * *", options, CHINESE));
    }

    @Test
    public void testEvery5MinutesAt30Seconds() throws Exception {
        Assert.assertEquals("过整分后第30秒, 每5分钟", CronExpressionDescriptor.getDescription("30 */5 * * * *", options, CHINESE));
    }

    @Test
    public void testMinutesPastTheHourRange() throws Exception {
        Assert.assertEquals("在30分钟，过整点后, 在上午10:00和下午1:00之间, 仅在星期三和星期五",
                CronExpressionDescriptor.getDescription("0 30 10-13 ? * WED,FRI", options, CHINESE));
    }

    @Test
    public void testSecondsPastTheMinuteInterval() throws Exception {
        Assert.assertEquals("过整分后第10秒, 每5分钟", CronExpressionDescriptor.getDescription("10 0/5 * * * ?", options, CHINESE));
    }

    @Test
    public void testBetweenWithInterval() throws Exception {
        Assert.assertEquals("每3分钟, 过整点后02到59分, 在上午1:00, 上午9:00和下午10:00, 在当月的11日和26日之间的每天, 一月到六月",
                CronExpressionDescriptor.getDescription("2-59/3 1,9,22 11-26 1-6 ?", options, CHINESE));
    }

    @Test
    public void testRecurringFirstOfMonth() throws Exception {
        Assert.assertEquals("在上午6:00", CronExpressionDescriptor.getDescription("0 0 6 1/1 * ?", options, CHINESE));
    }

    @Test
    public void testMinutesPastTheHour() throws Exception {
        Assert.assertEquals("在05分钟，过整点后", CronExpressionDescriptor.getDescription("0 5 0/1 * * ?", options, CHINESE));
    }

    /**
     * @since https://github.com/RedHogs/cron-parser/issues/2
     */
    @Test
    public void testEveryPastTheHour() throws Exception {
        Assert.assertEquals("在00, 05, 10, 15, 20, 25, 30, 35, 40, 45, 50和55分钟，过整点后", CronExpressionDescriptor.getDescription("0 0,5,10,15,20,25,30,35,40,45,50,55 * ? * *", options, CHINESE));
    }

    /**
     * @since https://github.com/RedHogs/cron-parser/issues/10
     */
    @Test
    public void testEveryXMinutePastTheHourWithInterval() throws Exception {
        Assert.assertEquals("每2分钟, 过整点后00到30分, 在下午5:00, 星期一到星期五", CronExpressionDescriptor.getDescription("0 0-30/2 17 ? * MON-FRI", options, CHINESE));
    }

    /**
     * @since https://github.com/RedHogs/cron-parser/issues/13
     */
    @Test
    public void testOneYearOnlyWithSeconds() throws Exception {
        Assert.assertEquals("每秒钟, 仅在2013年", CronExpressionDescriptor.getDescription("* * * * * * 2013", CHINESE));
    }

    @Test
    public void testOneYearOnlyWithoutSeconds() throws Exception {
        Assert.assertEquals("每分钟, 仅在2013年", CronExpressionDescriptor.getDescription("* * * * * 2013", CHINESE));
    }

    @Test
    public void testTwoYearsOnly() throws Exception {
        Assert.assertEquals("每分钟, 仅在2013和2014年", CronExpressionDescriptor.getDescription("* * * * * 2013,2014", options, CHINESE));
    }

    @Test
    public void testYearRange2() throws Exception {
        Assert.assertEquals("在下午12:23, 一月到二月, 2013到2014", CronExpressionDescriptor.getDescription("23 12 * JAN-FEB * 2013-2014", options, CHINESE));
    }

    @Test
    public void testYearRange3() throws Exception {
        Assert.assertEquals("在下午12:23, 一月到三月, 2013到2015", CronExpressionDescriptor.getDescription("23 12 * JAN-MAR * 2013-2015", options, CHINESE));
    }

    @Test
    public void testIssue26() throws Exception {
        Assert.assertEquals("在05和10分钟，过整点后", CronExpressionDescriptor.getDescription("5,10 * * * *", options, CHINESE));
        Assert.assertEquals("在05和10分钟，过整点后, 在上午12:00", CronExpressionDescriptor.getDescription("5,10 0 * * *", options, CHINESE));
        Assert.assertEquals("在05和10分钟，过整点后, 在当月的2日", CronExpressionDescriptor.getDescription("5,10 * 2 * *", options, CHINESE));
        Assert.assertEquals("每10分钟, 在当月的2日", CronExpressionDescriptor.getDescription("5/10 * 2 * *", options, CHINESE));

        Assert.assertEquals("过整分后第5和6秒", CronExpressionDescriptor.getDescription("5,6 0 * * * *", options, CHINESE));
        Assert.assertEquals("过整分后第5和6秒, 在上午1:00", CronExpressionDescriptor.getDescription("5,6 0 1 * * *", options, CHINESE));
        Assert.assertEquals("过整分后第5和6秒, 在当月的2日", CronExpressionDescriptor.getDescription("5,6 0 * 2 * *", options, CHINESE));
    }

}
