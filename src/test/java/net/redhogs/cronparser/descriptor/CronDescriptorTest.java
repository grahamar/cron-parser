package net.redhogs.cronparser.descriptor;

import com.google.common.collect.Lists;
import net.redhogs.cronparser.CronParameter;
import net.redhogs.cronparser.parser.field.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class CronDescriptorTest {

    private CronDescriptor descriptor;

    @Before
    public void setUp() throws Exception {
        descriptor = CronDescriptor.instance(Locale.UK);
    }

    @Test
    public void testDescribeEveryXTimeUnits() throws Exception {
        int time = 3;
        Every expression = new Every(null, ""+time);
        assertEquals(String.format("every %s seconds", time), descriptor.describe(
                    Lists.asList(new CronFieldParseResult(CronParameter.SECOND, expression), new CronFieldParseResult[]{})
                )
        );
        assertEquals(String.format("every %s minutes", time), descriptor.describe(
                        Lists.asList(new CronFieldParseResult(CronParameter.MINUTE, expression), new CronFieldParseResult[]{})
                )
        );
        assertEquals(String.format("every %s hours", time), descriptor.describe(
                        Lists.asList(new CronFieldParseResult(CronParameter.HOUR, expression), new CronFieldParseResult[]{})
                )
        );
    }

    @Test
    public void testDescribeEveryXMinutesBetweenTime() throws Exception {
        int hour = 11;
        int start = 0;
        int end = 10;
        Between expression = new Between(null, ""+start, ""+end);
        List<CronFieldParseResult> results = Lists.newArrayList();
        results.add(new CronFieldParseResult(CronParameter.MINUTE, expression));
        results.add(new CronFieldParseResult(CronParameter.HOUR, new On(null, "" + hour)));
        assertEquals(String.format("every minute between %s:%02d and %s:%02d", hour, start, hour, end), descriptor.describe(results));
    }

    @Test
    public void testDescribeAtXTimeBetweenDaysOfWeek() throws Exception {
        int hour = 11;
        int minute = 30;
        int start = 2;
        int end = 6;
        Between expression = new Between(null, ""+start, ""+end);
        List<CronFieldParseResult> results = Lists.newArrayList();
        results.add(new CronFieldParseResult(CronParameter.HOUR, new On(null, ""+hour)));
        results.add(new CronFieldParseResult(CronParameter.MINUTE, new On(null, ""+minute)));
        results.add(new CronFieldParseResult(CronParameter.DAY_OF_WEEK, expression));
        assertEquals(String.format("At %s:%s every day between Tuesday and Saturday", hour, minute), descriptor.describe(results));
    }

    @Test
    public void testDescribeAtXHours() throws Exception {
        int hour = 11;
        List<CronFieldParseResult> results = Lists.newArrayList();
        results.add(new CronFieldParseResult(CronParameter.HOUR, new On(null, ""+hour)));
        results.add(new CronFieldParseResult(CronParameter.MINUTE, new Always(null)));
        results.add(new CronFieldParseResult(CronParameter.SECOND, new Always(null)));
        assertEquals(String.format("At %s:00", hour), descriptor.describe(results));
    }
}