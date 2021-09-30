package online.nasgar.skywars.api.game.matcher;

import online.nasgar.skywars.api.game.Game;
import online.nasgar.skywars.api.game.type.GameType;

import java.util.Optional;

/**
 * Represents the finder of games
 * if is the fullest game
 */
public interface GameFinderHandler {

    /**
     * Find a accessible game
     *
     * @param type The {@link Game}'s type
     * @return An optional {@link Game}, result
     * of the search
     */
    Optional<Game> findAccessibleGame(GameType type);
}
