package online.nasgar.skywars.api.game.type;

import online.nasgar.skywars.api.game.Game;

/**
 * Represents the {@link Game}'s type.
 */
public interface GameType {

    /**
     * @see Game
     *
     * @return The {@link Game}'s team size.
     */
    Integer getSize();

    /**
     * @see #getSize()
     *
     * @param newSize The new team size of the
     * {@link Game}
     */
    void setSize(Integer newSize);

    /**
     * @see Types
     *
     * @return The {@link Game}'s type.
     */
    Types getType();

    /**
     * @see #getType()
     *
     * @param newType The new type of the
     * {@link Game}
     */
    void setType(Types newType);

    enum Types {
        UNRANKED, RANKED
    }
}
