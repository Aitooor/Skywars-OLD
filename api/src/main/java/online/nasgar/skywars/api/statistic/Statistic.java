package online.nasgar.skywars.api.statistic;

/**
 * Represents a object to use objects like statistics
 *
 * @param <O> The statistic object to be used
 */
public class Statistic<O> {

    private O statistic;

    public Statistic(O statistic){
        this.statistic = statistic;
    }

    public O getStatistic() {
        return statistic;
    }

    public void setStatistic(O statistic) {
        this.statistic = statistic;
    }
}
