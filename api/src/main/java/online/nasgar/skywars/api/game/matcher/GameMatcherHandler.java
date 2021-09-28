package online.nasgar.skywars.api.game.matcher;

import online.nasgar.skywars.api.game.Game;

import java.util.Optional;

/**
 * Represents the matcher of games
 * if is the fullest game
 */
public interface GameMatcherHandler {

    /**
     * Match
     * @return An optional {@link Game}, result
     * of the search
     */
    Optional<Game> matchAccessibleGame();
}
