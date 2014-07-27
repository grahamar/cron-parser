package net.redhogs.cronparser.parser.field;

import net.redhogs.cronparser.CronParameter;

import java.util.HashMap;
import java.util.Map;

public class FieldConstraints {
    private Map<String, Integer> stringMapping;
    private Map<Integer, Integer> intMapping;
    private int startRange;
    private int endRange;

    public FieldConstraints(){
        startRange = 0;//no negatives!
        endRange = Integer.MAX_VALUE;
    }

    public FieldConstraints registerStringToIntMapping(Map<String, Integer> mapping) {
        this.stringMapping = mapping;
        return this;
    }

    public FieldConstraints registerIntToIntMapping(Map<Integer, Integer> mapping) {
        this.intMapping = mapping;
        return this;
    }

    public FieldConstraints setValidationRange(int start, int end) {
        this.startRange = start;
        this.endRange = end;
        return this;
    }

    public int stringToInt(String exp) {
        if(stringMapping.containsKey(exp)) {
            return stringMapping.get(exp);
        } else {
            return Integer.parseInt(exp);
        }
    }

    public int intToInt(Integer exp){
        if(intMapping.containsKey(exp)){
            return intMapping.get(exp);
        }
        return exp;
    }

    public int validateInRange(int number){
        if(number >= startRange && number <= endRange) {
            return number;
        }
        throw new RuntimeException("Invalid range");
    }

    public static FieldConstraints forField(CronParameter field){
        switch (field){
            case SECOND:
            case MINUTE:
                return new FieldConstraints().setValidationRange(0,59);
            case HOUR:
                return new FieldConstraints().setValidationRange(0,23);
            case DAY_OF_WEEK:
                Map<Integer, Integer> intMapping = new HashMap<Integer, Integer>();
                intMapping.put(7,0);
                return new FieldConstraints().registerIntToIntMapping(intMapping).setValidationRange(0, 6);
            case DAY_OF_MONTH:
                return new FieldConstraints().setValidationRange(1,31);
            case MONTH:
                return new FieldConstraints().setValidationRange(1,12);
            default:
                return nullConstraints();
        }
    }

    public static FieldConstraints nullConstraints(){
        return new FieldConstraints();
    }
}
