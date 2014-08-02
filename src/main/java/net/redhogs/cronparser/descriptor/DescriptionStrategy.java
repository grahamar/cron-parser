package net.redhogs.cronparser.descriptor;

import com.google.common.base.Function;
import net.redhogs.cronparser.parser.field.*;

import java.text.MessageFormat;
import java.util.List;
import java.util.ResourceBundle;

abstract class DescriptionStrategy {
    protected Function<Integer, String> nominalValueFunction;
    protected ResourceBundle bundle;

    public DescriptionStrategy(ResourceBundle bundle){
        this.bundle = bundle;
        nominalValueFunction = new Function<Integer, String>() {
            public String apply(Integer integer) {
                return ""+integer;
            }
        };
    }

    public abstract String describe();

    protected String describe(CronFieldExpression cronFieldExpression) {
        if(cronFieldExpression instanceof Always){
            return describe((Always)cronFieldExpression);
        }
        if(cronFieldExpression instanceof And){
            return describe((And)cronFieldExpression);
        }
        if(cronFieldExpression instanceof Between){
            return describe((Between)cronFieldExpression);
        }
        if(cronFieldExpression instanceof Every){
            return describe((Every)cronFieldExpression);
        }
        if(cronFieldExpression instanceof On){
            return describe((On)cronFieldExpression);
        }
        return "";
    }

    protected String nominalValue(int value){
        return nominalValueFunction.apply(value);
    }

    protected String describe(Always always){
        if(always.getEvery().getTime() <= 1){
            return "";
        }
        return describe(always.getEvery());
    }

    protected String describe(And and){
        List<CronFieldExpression> expressions = and.getExpressions();
        StringBuilder builder = new StringBuilder();
        builder.append(describe(expressions.get(0)));
        for(int j = 1;j < expressions.size(); j++){
            builder.append(String.format(" %s %s ", bundle.getString("and"), describe(expressions.get(j))));
        }
        return builder.toString();
    }

    protected String describe(Between between){
        return new StringBuilder()
                .append(describe(between.getEvery()))
                .append(MessageFormat.format(bundle.getString("between_x_and_y"), nominalValue(between.getFrom()), nominalValue(between.getTo())))
                .append(" ").toString();
    }

    protected String describe(Every every){
        if(every.getTime() > 1){
            return String.format("%s %s ", bundle.getString("every"), nominalValue(every.getTime())) + "%s";
        }
        return bundle.getString("every") + " %s ";
    }

    protected String describe(On on){
        return String.format("%s %s ", bundle.getString("at"), nominalValue(on.getTime())) + "%s";
    }
}
