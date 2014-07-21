package net.redhogs.cronparser.parser;

import net.redhogs.cronparser.CronParameter;
import net.redhogs.cronparser.CronType;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class QuartzCronExpressionParserTest {

    private QuartzCronExpressionParser expressionParser;

    @Before
    public void setUp(){
        expressionParser = new QuartzCronExpressionParser();
    }


    @Test(expected = IllegalArgumentException.class)
    public void testParseIllegalArgument() throws Exception {
        expressionParser.parse("");
    }

    @Test
    public void testParseAllNumbers() throws Exception {
        Map<CronParameter, String> params = expressionParser.parse("2 2 2 2 2 2");
        for(CronParameter param : CronParameter.values()){
                params.get(param).equals("2");
        }
    }

    @Test(expected = ParseException.class)
    public void testParseBadLargeExpression() throws Exception {
        expressionParser.parse("2 2 2 2 2 2 2 2");
    }

    @Test(expected = ParseException.class)
    public void testParseBadShortExpression() throws Exception {
        expressionParser.parse("2 2 2 2");
    }

    @Test
    public void testGetCronType() throws Exception {
        assertEquals(CronType.QUARTZ, expressionParser.getCronType());
    }
}
