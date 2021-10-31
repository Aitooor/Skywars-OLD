package online.nasgar.skywars.api.game.impl;

import com.google.gson.annotations.Expose;
import online.nasgar.skywars.api.game.Game;
import online.nasgar.skywars.api.game.state.GameState;
import online.nasgar.skywars.api.game.type.GameType;
import online.nasgar.skywars.api.user.User;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class GameImpl implements Game {

    @Expose
    private final String id;
    @Expose
    private final String name;
    @Expose
    private GameType type;
    @Expose
    private int maxPlayers;
    private final Set<User> players;
    private GameState state;
    @Expose
    private Boolean disabled;

    public GameImpl(String id, String name){
        this.id = id;
        this.name = name;
        this.type = new GameTypeImpl(1, GameType.Types.UNRANKED);
        this.state = GameState.WAITING;
        this.disabled = true;
        this.maxPlayers = 6;
        this.players = new HashSet<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getMaxPlayers() {
        return 6;
    }

    @Override
    public Set<User> getPlayers() {
        return players;
    }

    @Override
    public Set<User> getAlive() {
        return players.stream().filter(User::isAlive).collect(Collectors.toSet());
    }

    @Override
    public Set<User> getSpectators() {
        return players.stream().filter(user -> !user.isAlive()).collect(Collectors.toSet());
    }

    @Override
    public GameState getState() {
        return state;
    }

    @Override
    public void setState(GameState state) {
        this.state = state;
    }

    @Override
    public GameType getType() {
        return type;
    }

    @Override
    public void setType(GameType type) {
        this.type = type;
    }

    @Override
    public boolean isDisabled() {
        return disabled;
    }

    @Override
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    @Override
    public boolean isFull() {
        return getPlayers().size() >= getMaxPlayers();
    }

    @Override
    public String getId() {
        return id;
    }
}
