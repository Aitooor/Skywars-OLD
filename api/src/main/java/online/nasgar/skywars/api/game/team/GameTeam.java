package online.nasgar.skywars.api.game.team;

import online.nasgar.skywars.api.user.User;

import java.util.Set;

public class GameTeam {

    private final Set<User> players;
    private final int maxPlayers;

    public GameTeam(Set<User> players, int maxPlayers) {
        this.players = players;
        this.maxPlayers = maxPlayers;
    }

    public Set<User> getPlayers() {
        return players;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }
}
