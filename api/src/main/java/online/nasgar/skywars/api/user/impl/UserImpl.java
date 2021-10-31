package online.nasgar.skywars.api.user.impl;

import com.google.gson.annotations.Expose;
import online.nasgar.skywars.api.game.Game;
import online.nasgar.skywars.api.statistic.Statistic;
import online.nasgar.skywars.api.user.User;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class UserImpl implements User {

    private Player player;
    @Expose
    private final String id;
    @Expose
    private final Statistic<Long> coins;
    @Expose
    private final Statistic<Integer> kills;
    @Expose
    private final Statistic<Integer> level;
    @Expose
    private final Statistic<Integer> deaths;
    @Expose
    private final Statistic<Integer> wins;
    @Expose
    private final Statistic<Long> timePlayed;
    @Expose
    private final Statistic<Integer> blocksPlaced;
    @Expose
    private final Statistic<Integer> blocksMined;
    @Expose
    private final Statistic<Integer> blocksWalked;
    @Expose
    private final RankedStatistics rankedStatistics;
    private Game game;
    private final String name;
    private boolean alive;

    public UserImpl(Player player){
        this.player = player;
        this.name = player.getName() != null ? player.getName() : "unknown";
        this.id = player.getUniqueId() != null ? player.getUniqueId().toString() : "unknown";
        this.alive = true;
        this.coins = new Statistic<>(0L);
        this.kills = new Statistic<>(0);
        this.level = new Statistic<>(0);
        this.deaths = new Statistic<>(0);
        this.wins = new Statistic<>(0);
        this.timePlayed = new Statistic<>(0L);
        this.blocksPlaced = new Statistic<>(0);
        this.blocksMined = new Statistic<>(0);
        this.blocksWalked = new Statistic<>(0);
        this.rankedStatistics = new RankedStatistics();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Statistic<Long> getCoins() {
        return coins;
    }

    @Override
    public Statistic<Integer> getKills() {
        return kills;
    }

    @Override
    public Statistic<Integer> getLevel() {
        return level;
    }

    @Override
    public Statistic<Integer> getDeaths() {
        return deaths;
    }

    @Override
    public Statistic<Integer> getWins() {
        return wins;
    }

    @Override
    public Statistic<Long> getTimePlayed() {
        return timePlayed;
    }

    @Override
    public Statistic<Integer> getBlocksPlaced() {
        return blocksPlaced;
    }

    @Override
    public Statistic<Integer> getBlocksMined() {
        return blocksMined;
    }

    @Override
    public Statistic<Integer> getBlocksWalked() {
        return blocksWalked;
    }

    @Override
    public RankedStatistics getRankedStatistics() {
        return rankedStatistics;
    }

    @Override
    public Optional<Game> getGame() {
        return Optional.of(game);
    }

    @Override
    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public @Nullable Player getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
