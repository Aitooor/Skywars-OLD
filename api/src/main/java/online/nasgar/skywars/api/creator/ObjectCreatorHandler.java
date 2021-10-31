package online.nasgar.skywars.api.creator;

import online.nasgar.skywars.api.serializer.Serializable;
import online.nasgar.skywars.api.user.User;
import org.jetbrains.annotations.NotNull;

/**
 * Represents the creator and editor of objects.
 */
public interface ObjectCreatorHandler<O extends Serializable> {

    /**
     * Create or edit a existing object.
     *
     * @param user The player to be added to the setup.
     * @param id The {@link O}'s id.
     * @param name The {@link O}'s name.
     * @return An boolean result if the {@link O} was be
     * created or edited
     */
    boolean createOrEdit(
            @NotNull User user,
            String id,
            String name
    );

    /**
     * Save a object.
     *
     * @param user The player to be removed to the setup.
     * @param object The {@link O}.
     * @return An boolean result if the {@link O} was be
     * saved
     */
    boolean save(
            @NotNull User user,
            O object
    );


    /**
     * {@link User} is in Setup a
     *
     * @param user The player to be searched
     * @return An boolean result if the {@link User} is in setup
     */
    boolean isSetup(@NotNull User user);
}
