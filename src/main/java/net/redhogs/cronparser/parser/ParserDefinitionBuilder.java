package net.redhogs.cronparser.parser;

import net.redhogs.cronparser.CronParameter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ParserDefinitionBuilder {
    private Map<CronParameter, CronField> fields;
    private boolean lastFieldOptional;

    private ParserDefinitionBuilder(){
        fields = new HashMap<CronParameter, CronField>();
        lastFieldOptional = false;
    }

    public static ParserDefinitionBuilder defineParser(){
        return new ParserDefinitionBuilder();
    }

    public ParserDefinitionBuilder withSeconds(){
        register(CronField.seconds());
        return this;
    }

    public ParserDefinitionBuilder withMinutes(){
        register(CronField.minutes());
        return this;
    }

    public ParserDefinitionBuilder withHours(){
        register(CronField.hours());
        return this;
    }

    public ParserDefinitionBuilder withDayOfMonth(){
        register(CronField.daysOfMonth());
        return this;
    }

    public ParserDefinitionBuilder withMonth(){
        register(CronField.months());
        return this;
    }

    public ParserDefinitionBuilder withDayOfWeek(){
        register(CronField.daysOfWeek());
        return this;
    }

    public ParserDefinitionBuilder withYear(){
        register(CronField.years());
        return this;
    }

    public ParserDefinitionBuilder andLastFieldOptional(){
        lastFieldOptional = true;
        return this;
    }

    private void register(CronField cronField){
        fields.put(cronField.getField(), cronField);
    }

    public CronParser instance(){
        return new CronParser(new HashSet<CronField>(fields.values()), lastFieldOptional);
    }

}
