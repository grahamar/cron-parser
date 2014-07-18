package net.redhogs.cronparser.builder;

import net.redhogs.cronparser.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class DescriptorParamsBuilderTest {

    private DescriptorParamsBuilder builder;

    @Before
    public void setUp(){
        builder = DescriptorParamsBuilder.createDescriptor();
    }

    @Test
    public void testDefaultSettings() throws Exception {
        assertEquals(CronType.UNIX, builder.cronType);
        assertEquals(I18nMessages.DEFAULT_LOCALE, builder.locale);
        assertEquals(DescriptionTypeEnum.FULL, builder.descriptionType);
    }


    @Test
    public void testForCronType() throws Exception {
        builder.forCronType(CronType.QUARTZ);
        assertEquals(CronType.QUARTZ, builder.cronType);
    }

    @Test
    public void testForDescriptionDayOfMonth() throws Exception {
        builder.forDescription(DescriptionTypeEnum.DAYOFMONTH);
        assertEquals(DescriptionTypeEnum.DAYOFMONTH, builder.descriptionType);
    }

    @Test
    public void testForDescriptionDayOfWeek() throws Exception {
        builder.forDescription(DescriptionTypeEnum.DAYOFWEEK);
        assertEquals(DescriptionTypeEnum.DAYOFWEEK, builder.descriptionType);
    }

    @Test
    public void testForDescriptionFull() throws Exception {
        builder.forDescription(DescriptionTypeEnum.FULL);
        assertEquals(DescriptionTypeEnum.FULL, builder.descriptionType);
    }

    @Test
    public void testForDescriptionHours() throws Exception {
        builder.forDescription(DescriptionTypeEnum.HOURS);
        assertEquals(DescriptionTypeEnum.HOURS, builder.descriptionType);
    }

    @Test
    public void testForDescriptionMinutes() throws Exception {
        builder.forDescription(DescriptionTypeEnum.MINUTES);
        assertEquals(DescriptionTypeEnum.MINUTES, builder.descriptionType);
    }

    @Test
    public void testForDescriptionMonth() throws Exception {
        builder.forDescription(DescriptionTypeEnum.MONTH);
        assertEquals(DescriptionTypeEnum.MONTH, builder.descriptionType);
    }

    @Test
    public void testForDescriptionSeconds() throws Exception {
        builder.forDescription(DescriptionTypeEnum.SECONDS);
        assertEquals(DescriptionTypeEnum.SECONDS, builder.descriptionType);
    }

    @Test
    public void testForDescriptionTimeofday() throws Exception {
        builder.forDescription(DescriptionTypeEnum.TIMEOFDAY);
        assertEquals(DescriptionTypeEnum.TIMEOFDAY, builder.descriptionType);
    }

    @Test
    public void testWithOptions() throws Exception {
        Options options = new Options();
        options.setCasingType(CasingTypeEnum.LowerCase);
        builder.withOptions(options);
        assertEquals(options, builder.options);
    }

    @Test
    public void testWithLocale() throws Exception {
        builder.withLocale(Locale.JAPAN);
        assertEquals(Locale.JAPAN, builder.locale);
    }
}
