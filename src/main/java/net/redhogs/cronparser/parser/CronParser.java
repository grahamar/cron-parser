package net.redhogs.cronparser.parser;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class CronParser {
    private Map<Integer, List<CronField>> expressions;

    public CronParser(Set<CronField> fieldsSet, boolean lastFieldOptional){
        expressions = new HashMap<Integer, List<CronField>>();
        buildPossibleExpressions(fieldsSet, lastFieldOptional);
    }

    private void buildPossibleExpressions(Set<CronField> fieldsSet, boolean lastFieldOptional){
        List<CronField> expression = new ArrayList<CronField>();
        expression.addAll(fieldsSet);
        Collections.sort(expression, CronField.createFieldTypeComparator());
        expressions.put(expression.size(), expression);

        if(lastFieldOptional){
            List<CronField> shortExpression = new ArrayList<CronField>();
            Collections.copy(shortExpression, expression);
            shortExpression.remove(shortExpression.size()-1);
            expressions.put(shortExpression.size(), shortExpression);
        }
    }

    public List<CronFieldParseResult> parse(String expression){
        if (StringUtils.isEmpty(expression)) {
            throw new IllegalArgumentException("Empty expression!");
        }
        String[] expressionParts = expression.split(" ");
        if (expressions.containsKey(expressionParts.length)) {
            List<CronFieldParseResult> results = new ArrayList<CronFieldParseResult>();
            List<CronField> fields = expressions.get(expressionParts.length);
            for(int j =0; j<fields.size(); j++){
                results.add(fields.get(j).parse(expressionParts[j]));
            }
            return results;
        } else {
            throw new IllegalArgumentException("Expressions size do not match registered options!");
        }
    }
}
