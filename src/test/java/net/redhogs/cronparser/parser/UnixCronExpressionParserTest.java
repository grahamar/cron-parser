package net.redhogs.cronparser.parser;

import net.redhogs.cronparser.CronParameter;
import net.redhogs.cronparser.CronType;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class UnixCronExpressionParserTest {
    private UnixCronExpressionParser expressionParser;

    @Before
    public void setUp(){
        expressionParser = new UnixCronExpressionParser();
    }


    @Test(expected = IllegalArgumentException.class)
    public void testParseIllegalArgument() throws Exception {
        expressionParser.parse("");
    }

    @Test
    public void testParse() throws Exception {
        Map<CronParameter, String> params = expressionParser.parse("2 2 2 2 2");
        for(CronParameter param : CronParameter.values()){
            if(CronParameter.SECOND.equals(param)){
                params.get(param).equals("0");
            }else{
                params.get(param).equals("2");
            }
        }
    }

    @Test
    public void testGetCronType() throws Exception {
        assertEquals(CronType.UNIX, expressionParser.getCronType());
    }
}
