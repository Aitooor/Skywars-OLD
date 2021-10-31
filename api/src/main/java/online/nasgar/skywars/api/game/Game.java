package online.nasgar.skywars.api.game;

import online.nasgar.skywars.api.game.impl.GameImpl;
import online.nasgar.skywars.api.game.state.GameState;
import online.nasgar.skywars.api.game.type.GameType;
import online.nasgar.skywars.api.repository.key.annotation.InstanceDeserializer;
import online.nasgar.skywars.api.repository.key.annotation.RepositoryKey;
import online.nasgar.skywars.api.serializer.Serializable;
import online.nasgar.skywars.api.user.User;

import java.util.Set;

/**
 * Represents the model of a arena.
 *
 * @see Serializable
 */
@RepositoryKey("games")
@InstanceDeserializer(GameImpl.class)
public interface Game extends Serializable {

    /**
     * @return The name of the {@link Game}
     */
    String getName();

    /**
     * @return The maximum count of players in the {@link Game}
     */
    int getMaxPlayers();

    /**
     * @see User
     *
     * @return The players in the {@link Game}
     */
    Set<User> getPlayers();

    /**
     * @see User
     *
     * @return The alive players in the
     * {@link Game}
     */
    Set<User> getAlive();

    /**
     * @see User
     *
     * @return The death players in the
     * {@link Game}
     */
    Set<User> getSpectators();

    /**
     * @see GameState
     *
     * @return The {@link Game}'s state
     */
    GameState getState();

    /**
     * @see #getState()
     *
     * @param state The new state of the
     * {@link Game}
     */
    void setState(GameState state);

    /**
     * @see GameType
     *
     * @return The {@link Game}'s type
     */
    GameType getType();

    /**
     * @see #getType()
     *
     * @param type The new type of the
     * {@link Game}
     */
    void setType(GameType type);

    /**
     * @see #setDisabled(boolean)
     *
     * @return If the {@link Game} is disabled
     */
    boolean isDisabled();

    /**
     * @see #isDisabled()
     *
     * @param disabled The new disabled boolean
     * to the {@link Game}
     */
    void setDisabled(boolean disabled);

    /**
     * @return A boolean if the game is full
     */
    boolean isFull();
}
