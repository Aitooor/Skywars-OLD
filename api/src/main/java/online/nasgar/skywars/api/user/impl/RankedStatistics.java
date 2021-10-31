package online.nasgar.skywars.api.user.impl;

import com.google.gson.annotations.Expose;
import online.nasgar.skywars.api.statistic.Statistic;

public class RankedStatistics {

    @Expose
    private final Statistic<Integer> kills;
    @Expose
    private final Statistic<Integer> deaths;
    @Expose
    private final Statistic<Integer> wins;
    @Expose
    private final Statistic<Integer> elo;
    @Expose
    private final Statistic<Integer> level;

    public RankedStatistics(){
        this.kills = new Statistic<>(0);
        this.deaths = new Statistic<>(0);
        this.wins = new Statistic<>(0);
        this.elo = new Statistic<>(0);
        this.level = new Statistic<>(0);
    }

    public Statistic<Integer> getKills() {
        return kills;
    }

    public Statistic<Integer> getDeaths() {
        return deaths;
    }

    public Statistic<Integer> getWins() {
        return wins;
    }

    public Statistic<Integer> getElo() {
        return elo;
    }

    public Statistic<Integer> getLevel() {
        return level;
    }
}
