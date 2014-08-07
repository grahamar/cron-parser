package net.redhogs.cronparser.parser;

import net.redhogs.cronparser.CronType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParserFactoryTest {
    private ParserFactory factory;
    @Before
    public void setUp() throws Exception {
        factory = ParserFactory.getInstance();
    }

    @Test
    public void testGetQuartzCronExpressionParser() throws Exception {
        assertEquals(QuartzCronExpressionParser.class, factory.getQuartzCronExpressionParser().getClass());
    }

    @Test
    public void testGetUnixCronExpressionParser() throws Exception {
        assertEquals(UnixCronExpressionParser.class, factory.getUnixCronExpressionParser().getClass());
    }

    @Test
    public void testRetrieveInstanceForTypeUnix() throws Exception {
        assertEquals(UnixCronExpressionParser.class, factory.retrieveInstanceForType(CronType.UNIX).getClass());
    }

    @Test
    public void testRetrieveInstanceForTypeQuartz() throws Exception {
        assertEquals(QuartzCronExpressionParser.class, factory.retrieveInstanceForType(CronType.QUARTZ).getClass());
    }

    @Test
    public void testGetInstance() throws Exception {
        assertEquals(ParserFactory.class, ParserFactory.getInstance().getClass());
    }
}
