package net.redhogs.cronparser.format;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import net.redhogs.cronparser.CasingTypeEnum;
import net.redhogs.cronparser.Options;

import org.junit.Assert;
import org.junit.Test;
import org.quartz.CronExpression;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

public class CronExpressionFormatterTest {

  @Test
  public void testPrinter() throws Exception {
    CronExpressionFormatter p = new CronExpressionFormatter();
    Assert.assertEquals("Every second", p.print(new CronExpression("* * * ? * *"), Locale.US));
  }

  @Test
  public void testPrinterOptions() throws Exception {
    Options o = new Options();
    o.setCasingType(CasingTypeEnum.LowerCase);
    CronExpressionFormatter p = new CronExpressionFormatter(o);
    Assert.assertEquals("every second", p.print(new CronExpression("* * * ? * *"), Locale.US));
  }

  @Test
  public void testConversionService() throws Exception {
    Set<Object> formatters = new HashSet<Object>();
    CronExpressionFormatter p = new CronExpressionFormatter();
    formatters.add(p);

    FormattingConversionServiceFactoryBean cf = new FormattingConversionServiceFactoryBean();
    cf.setFormatters(formatters);
    cf.afterPropertiesSet();

    Assert.assertTrue(cf.getObject().canConvert(CronExpression.class, String.class));
    Assert.assertEquals("Every second", cf.getObject().convert(new CronExpression("* * * ? * *"), String.class));
  }

}