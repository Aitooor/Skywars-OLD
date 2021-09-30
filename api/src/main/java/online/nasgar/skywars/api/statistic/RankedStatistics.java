package online.nasgar.skywars.api.statistic;

/**
 * Represents the Ranked statistics for
 * the players.
 */
public interface RankedStatistics {

    Statistic<Integer> getKills();

    Statistic<Integer> getDeaths();

    Statistic<Integer> getWins();

    Statistic<Integer> getElo();

    Statistic<Integer> getLevel();
}
