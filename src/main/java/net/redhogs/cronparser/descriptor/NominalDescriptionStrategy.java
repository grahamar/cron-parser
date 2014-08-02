package net.redhogs.cronparser.descriptor;

import com.google.common.base.Function;
import com.google.common.collect.Sets;
import net.redhogs.cronparser.parser.field.Always;
import net.redhogs.cronparser.parser.field.CronFieldExpression;
import net.redhogs.cronparser.parser.field.FieldConstraints;

import java.util.ResourceBundle;
import java.util.Set;

class NominalDescriptionStrategy extends DescriptionStrategy {
    private CronFieldExpression expression;
    private Set<Function<CronFieldExpression, String>> descriptions;

    public NominalDescriptionStrategy(ResourceBundle bundle, Function<Integer, String> nominalValueFunction, CronFieldExpression expression){
        super(bundle);
        descriptions = Sets.newHashSet();
        if(nominalValueFunction!=null){
            this.nominalValueFunction = nominalValueFunction;
        }
        if(expression != null){
            this.expression = expression;
        } else {
            this.expression = new Always(FieldConstraints.nullConstraints());
        }
    }

    @Override
    public String describe() {
        for(Function<CronFieldExpression, String> function : descriptions){
            if(!"".equals(function.apply(expression))){
                return function.apply(expression);
            }
        }
        return describe(expression);
    }

    public NominalDescriptionStrategy addDescription(Function<CronFieldExpression, String> desc){
        descriptions.add(desc);
        return this;
    }
}
