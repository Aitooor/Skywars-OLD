package online.nasgar.skywars.api.user;

import online.nasgar.skywars.api.game.Game;
import online.nasgar.skywars.api.repository.key.annotation.InstanceDeserializer;
import online.nasgar.skywars.api.repository.key.annotation.RepositoryKey;
import online.nasgar.skywars.api.serializer.Serializable;
import online.nasgar.skywars.api.statistic.Statistic;
import online.nasgar.skywars.api.user.impl.RankedStatistics;
import online.nasgar.skywars.api.user.impl.UserImpl;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import java.util.Optional;


/**
 * Represents the object of the player
 * to get Statistics and another data.
 *
 * @see Serializable
 */
@RepositoryKey("users")
@InstanceDeserializer(UserImpl.class)
public interface User extends Serializable {

    Statistic<Long> getCoins();

    Statistic<Integer> getKills();

    Statistic<Integer> getLevel();

    Statistic<Integer> getDeaths();

    Statistic<Integer> getWins();

    Statistic<Long> getTimePlayed();

    Statistic<Integer> getBlocksPlaced();

    Statistic<Integer> getBlocksMined();

    Statistic<Integer> getBlocksWalked();

    RankedStatistics getRankedStatistics();

    Optional<Game> getGame();

    void setGame(Game game);

    String getName();

    @Nullable Player getPlayer();

    void setPlayer(Player player);

    boolean isAlive();

    void setAlive(boolean alive);
}
