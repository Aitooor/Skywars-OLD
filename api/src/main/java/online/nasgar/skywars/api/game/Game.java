package online.nasgar.skywars.api.game;

import online.nasgar.skywars.api.game.state.GameState;
import online.nasgar.skywars.api.game.type.GameType;
import online.nasgar.skywars.api.serializer.Serializable;
import online.nasgar.skywars.api.user.User;

import java.util.Set;

/**
 * Represents the model of a arena.
 *
 * @see Serializable
 */
public interface Game extends Serializable {

    Set<User> getPlayers();

    Set<User> getAlive();

    Set<User> getSpectators();

    GameState getState();

    GameType getType();

    Integer getTeamSize();
}
