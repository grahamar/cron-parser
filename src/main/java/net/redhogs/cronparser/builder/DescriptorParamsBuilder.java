package net.redhogs.cronparser.builder;

import net.redhogs.cronparser.*;

import java.util.Locale;

public class DescriptorParamsBuilder {
    protected CronType cronType;
    protected DescriptionTypeEnum descriptionType;
    protected Options options;
    protected Locale locale;

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

    public CronExpressionDescriptor instance(){
        return new CronExpressionDescriptor(cronType, descriptionType, options, locale);
    }

    public static DescriptorParamsBuilder createDescriptor(){
        return new DescriptorParamsBuilder();
    }
}
