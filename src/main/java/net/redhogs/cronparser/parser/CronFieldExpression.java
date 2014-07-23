package net.redhogs.cronparser.parser;

import java.util.HashMap;
import java.util.Map;

public abstract class CronFieldExpression {

    private Map<String, Integer> stringMapping;
    private Map<Integer, Integer> intMapping;
    private int startRange;
    private int endRange;

    public CronFieldExpression(){
        this.stringMapping = new HashMap<String, Integer>();
        this.intMapping = new HashMap<Integer, Integer>();
    }

    public final void registerStringToIntMapping(Map<String, Integer> mapping) {
        this.stringMapping = mapping;
    }

    protected final void registerIntToIntMapping(Map<Integer, Integer> mapping) {
        this.intMapping = mapping;
    }

    protected final void setValidatetionRange(int start, int end) {
        this.startRange = start;
        this.endRange = end;
    }

    protected final int stringToInt(String exp) {
        if(stringMapping.containsKey(exp)) {
            return stringMapping.get(exp);
        } else {
            return Integer.parseInt(exp);
        }
    }

    protected final int intToInt(Integer exp){
        if(intMapping.containsKey(exp)){
            return intMapping.get(exp);
        }
        return exp;
    }

    protected final int validateInRange(int number){
        if(number >= startRange && number <= endRange) {
            return number;
        }
        throw new RuntimeException("Invalid range");
    }

    public And and(CronFieldExpression exp) {
        return new And().and(this).and(exp);
    }
}
