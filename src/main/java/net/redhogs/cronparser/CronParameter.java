package net.redhogs.cronparser;

public enum CronParameter {
    SECOND(0), MINUTE(1), HOUR(2), DAY_OF_MONTH(3), MONTH(4), DAY_OF_WEEK(5), YEAR(6);

    private int order;
    private CronParameter(int order){
        this.order = order;
    }

    public int getOrder() {
        return order;
    }
}
