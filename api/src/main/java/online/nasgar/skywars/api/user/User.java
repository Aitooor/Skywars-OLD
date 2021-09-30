package online.nasgar.skywars.api.user;

import online.nasgar.skywars.api.game.Game;
import online.nasgar.skywars.api.serializer.Serializable;
import online.nasgar.skywars.api.statistic.RankedStatistics;
import online.nasgar.skywars.api.statistic.Statistic;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import java.util.Optional;


/**
 * Represents the object of the player
 * to get Statistics and another data.
 *
 * @see Serializable
 */
public interface User extends Serializable {

    Statistic<Long> getCoins();

    Statistic<Integer> getKills();

    Statistic<Integer> getLevel();

    Statistic<Integer> getDeaths();

    Statistic<Integer> getWins();

    Statistic<Integer> getTimePlayed();

    Statistic<Integer> getBlocksPlaced();

    Statistic<Integer> getBlocksMined();

    Statistic<Integer> getBlocksWalked();

    RankedStatistics getRankedStatistics();

    Optional<Game> getGame();

    void setGame(Game game);

    @Nullable Player getPlayer();
}
