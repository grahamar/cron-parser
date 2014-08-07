package net.redhogs.cronparser.parser.field;

public abstract class CronFieldExpression {

    protected FieldConstraints constraints;

    public CronFieldExpression(FieldConstraints constraints){
        if(constraints != null){
            this.constraints = constraints;
        }else{
            this.constraints = FieldConstraints.nullConstraints();
        }
    }

    public And and(CronFieldExpression exp) {
        return new And().and(this).and(exp);
    }

    protected FieldConstraints getConstraints(){
        return constraints;
    }
}
