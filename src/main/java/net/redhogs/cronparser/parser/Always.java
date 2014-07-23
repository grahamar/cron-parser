package net.redhogs.cronparser.parser;

public class Always extends CronFieldExpression {
    private Every every;

    public Always(){
        this(null);
    }

    public Always(String every) {
        super();
        if(every != null){
            this.every = new Every(every);
        } else {
            this.every = new Every("1");
        }
    }
}
