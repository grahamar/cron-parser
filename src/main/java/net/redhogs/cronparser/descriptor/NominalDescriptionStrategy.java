package net.redhogs.cronparser.descriptor;

import com.google.common.base.Function;
import net.redhogs.cronparser.parser.field.Always;
import net.redhogs.cronparser.parser.field.CronFieldExpression;
import net.redhogs.cronparser.parser.field.FieldConstraints;

import java.util.ResourceBundle;

class NominalDescriptionStrategy extends DescriptionStrategy {
    private CronFieldExpression expression;

    public NominalDescriptionStrategy(ResourceBundle bundle, Function<Integer, String> nominalValueFunction, CronFieldExpression expression){
        super(bundle);
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
        return describe(expression);
    }
}
