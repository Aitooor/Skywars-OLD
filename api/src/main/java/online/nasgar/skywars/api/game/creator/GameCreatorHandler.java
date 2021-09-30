package online.nasgar.skywars.api.game.creator;

import online.nasgar.skywars.api.game.Game;
import online.nasgar.skywars.api.user.User;

/**
 * Represents the creator and editor of games
 * @see Game
 */
public interface GameCreatorHandler {

    /**
     * Create or edit a existing game
     *
     * @param user The player to be added to the setup
     * @param id The {@link Game}'s id
     * @param name The {@link Game}'s id
     * @return An boolean result if the {@link User} is not in setup
     */
    boolean createOrEdit(
            User user,
            String id,
            String name
    );

    /**
     * Save a game
     *
     * @param user The player to be removed to the setup
     * @param game The {@link Game}
     * @return An boolean result if the {@link User} is in setup
     */
    boolean save(
            User user,
            Game game
    );
}
