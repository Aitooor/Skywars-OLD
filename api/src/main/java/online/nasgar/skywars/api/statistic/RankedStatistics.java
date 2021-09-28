package online.nasgar.skywars.api.statistic;

public interface RankedStatistics {

    Statistic<Integer> getKills();

    Statistic<Integer> getDeaths();

    Statistic<Integer> getWins();

    Statistic<Integer> getElo();

    Statistic<Integer> getLevel();
}
