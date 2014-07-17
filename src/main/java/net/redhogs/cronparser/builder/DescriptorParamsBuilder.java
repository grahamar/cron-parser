package net.redhogs.cronparser.builder;

import net.redhogs.cronparser.*;

import java.util.Locale;

/**
 * Created by jose.rozanec on 7/16/14.
 */
public class DescriptorParamsBuilder {
    private CronType cronType;
    private DescriptionTypeEnum descriptionType;
    private Options options;
    private Locale locale;

    private DescriptorParamsBuilder(){
        cronType = CronType.UNIX;
        descriptionType = DescriptionTypeEnum.FULL;
        options = new Options();
        locale = I18nMessages.DEFAULT_LOCALE;
    }

    public DescriptorParamsBuilder forCronType(CronType cronType){
        this.cronType = cronType;
        return this;
    }

    public DescriptorParamsBuilder forDescription(DescriptionTypeEnum descriptionType){
        this.descriptionType = descriptionType;
        return this;
    }

    public DescriptorParamsBuilder withOptions(Options options){
        this.options = options;
        return this;
    }

    public DescriptorParamsBuilder withLocale(Locale locale){
        this.locale = locale;
        return this;
    }

    public CronExpressionDescriptor build(){
        return new CronExpressionDescriptor(cronType, descriptionType, options, locale);
    }

    public static DescriptorParamsBuilder createDescriptor(){
        return new DescriptorParamsBuilder();
    }
}
